package Model;

public class Client {
	private int clientId;
	private String clientFirstName;
	private String clientLastName;
	private String clientAddress;
	private String clientEmailAddress;
	private String clientPhoneNumber;
	private int clientAge;

	public Client() {
	}

	public Client(int id, String firstName,String lastName, String address, String email,String phoneNumber, int age) {
		super();
		this.clientId = id;
		this.clientFirstName=firstName;
		this.clientLastName=lastName;
		this.clientAddress = address;
		this.clientEmailAddress = email;
		this.clientPhoneNumber=phoneNumber;
		this.clientAge = age;
	}

	public Client(String firstName,String lastName, String address, String email,String phoneNumber, int age) {
		super();
		this.clientFirstName=firstName;
		this.clientLastName=lastName;
		this.clientAddress = address;
		this.clientEmailAddress = email;
		this.clientPhoneNumber=phoneNumber;
		this.clientAge = age;
	}

	public int getClientId() {
		return clientId;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public String getClientEmailAddress() {
		return clientEmailAddress;
	}

	public String getClientPhoneNumber() {
		return clientPhoneNumber;
	}

	public int getClientAge() {
		return clientAge;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public void setClientEmailAddress(String clientEmailAddress) {
		this.clientEmailAddress = clientEmailAddress;
	}

	public void setClientPhoneNumber(String clientPhoneNumber) {
		this.clientPhoneNumber = clientPhoneNumber;
	}

	public void setClientAge(int clientAge) {
		this.clientAge = clientAge;
	}

	@Override
	public String toString() {
		return "Client [id=" + clientId + ", lastName=" + clientLastName+" ]";
	}

}
