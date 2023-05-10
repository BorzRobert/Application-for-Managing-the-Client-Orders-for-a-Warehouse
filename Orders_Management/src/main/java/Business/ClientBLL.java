package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import DAO.ClientDAO;
import Model.Client;

public class ClientBLL {
	private ClientDAO clientDAO;

	public ClientBLL() {
		clientDAO = new ClientDAO();
	}

	public Client insertClient(Client client){
		Client st=clientDAO.insert(client);
		return st;
	}
	public Client updateClientById(Client client){
		Client st=clientDAO.updateById(client,client.getClientId(),"clientId");
		return st;
	}
	public Client findClientById(int id) {
		Client st = clientDAO.findById(id,"clientId");
		if (st == null) {
			throw new NoSuchElementException("The client with id =" + id + " was not found!");
		}
		return st;
	}
	public void deleteClientById(int id) {
		clientDAO.deleteById(id,"clientId");
	}

	public List<Client> findAllClients() {
		List<Client> clientsList =new ArrayList<>();
		clientsList=clientDAO.findAll();
		if (clientsList == null) {
			throw new NoSuchElementException("The clients list was not found or is empty!");
		}
		return clientsList;
	}
}
