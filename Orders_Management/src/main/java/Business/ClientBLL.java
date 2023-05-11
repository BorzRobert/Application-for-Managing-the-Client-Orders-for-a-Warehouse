package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DAO.ClientDAO;
import Model.Client;
/**
 * This class is used in order to implement the business logic for Client objects
 */
public class ClientBLL {
	private ClientDAO clientDAO;

	public ClientBLL() {
		clientDAO = new ClientDAO();
	}

	/**
	 * Function used to insert a Client object in the database
	 * @param client
	 * @return The inserted Client object
	 */
	public Client insertClient(Client client){
		Client st=clientDAO.insert(client);
		return st;
	}

	/**
	 * Function used in order to update a Client object from the database
	 * @param client
	 * @return The updated Client object
	 */
	public Client updateClientById(Client client){
		Client st=clientDAO.updateById(client,client.getClientId(),"clientId");
		return st;
	}

	/**
	 * Function that returns the Client object that corresponds with the given id
	 * @param id
	 * @return The desired Client object
	 */
	public Client findClientById(int id) {
		Client st = clientDAO.findById(id,"clientId");
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}

	/**
	 * Function used in order to delete a Client object from de database with respect to the given id
	 * @param id
	 */
	public void deleteClientById(int id) {
		clientDAO.deleteById(id,"clientId");
	}

	/**
	 * Function used to retrieve in a list all the Client objects from the database
	 * @return A list of Client objects
	 */
	public List<Client> findAllClients() {
		List<Client> clientsList =new ArrayList<>();
		clientsList=clientDAO.findAll();
		if (clientsList == null) {
			throw new NoSuchElementException("The clients list was not found or is empty!");
		}
		return clientsList;
	}
}
