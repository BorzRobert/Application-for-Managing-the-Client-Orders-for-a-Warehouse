package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * Generic class used in order to access the mySQL database and create queries
 * @param <T>
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	/**
	 * Function used in order to create a general SELECT query
	 * @param field
	 * @return A String that represents the desired query
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	/**
	 * Function used in order to create a general DELETE query
	 * @param field
	 * @param value
	 * @return A String that represents the desired query
	 */
	private String createDeleteQuery(String field, String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " ="+value);
		return sb.toString();
	}

	/**
	 * Function used in order to retrieve in a list all the Object of type T from the database
	 * @return The list of objects of type T
	 */
	public List<T> findAll() {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query="SELECT * FROM "+"myprojectdb."+type.getSimpleName();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Function used in order to find an object of type T in the database with respect to a given id
	 * @param id
	 * @param idFieldName
	 * @return The desired object of type T
	 */
	public T findById(int id, String idFieldName) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(idFieldName);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	/**
	 * Function used in order to delete from database an object of type T with respect to a given id
	 * @param id
	 * @param idFieldName
	 */
	public void deleteById(int id, String idFieldName) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createDeleteQuery(idFieldName,Integer.toString(id));
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int deleted = statement.executeUpdate(query);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	}

	/**
	 * Function that creates a list of objects of type T using reflection methods
	 * @param resultSet
	 * @return The desired list of objects of type T
	 */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T)ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Function used in order to insert an object of type T in the database
	 * @param t
	 * @return The inserted object of type T
	 */
	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> columns=new ArrayList<>();
		List<String> values=new ArrayList<>();
		String query="INSERT INTO "+"myprojectdb."+type.getSimpleName()+" ";
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(t);
				columns.add(field.getName());
				values.add(value.toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		String c="(",v="(";
		for(int i=0; i<columns.size(); i++){
			 if(i!=columns.size()-1){
				 c=c+columns.get(i)+", ";
				 v=v+"'"+values.get(i)+"', ";
			}else{
				 c=c+columns.get(i)+")";
				 v=v+"'"+values.get(i)+"')";
			}
		}
		query=query+c+" VALUES "+v+";";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int inserted = statement.executeUpdate(query);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}

	/**
	 * Function used in order to update an object of type T already existent in the database
	 * @param t
	 * @param id
	 * @param idFieldName
	 * @return The updated object of type T
	 */
	public T updateById(T t, int id, String idFieldName) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<String> columns=new ArrayList<>();
		List<String> values=new ArrayList<>();
		String query="UPDATE "+type.getSimpleName()+" SET ";
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value;
			try {
				value = field.get(t);
				columns.add(field.getName());
				values.add(value.toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		for(int i=0; i<columns.size(); i++){
			if(i!=columns.size()-1){
				query=query+columns.get(i)+" = '"+values.get(i)+"', ";

			}else{
				query=query+columns.get(i)+" = '"+values.get(i)+"' ";
			}
		}
		query=query+" WHERE "+idFieldName+" = "+id+";";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int inserted = statement.executeUpdate(query);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return t;
	}
}
