/**
 * 
 */
package dataManager;

import java.io.Serializable;

/**
 * Encapsulate data about clients.
 * 
 * @author Matthew Duncan 561630
 *
 */
public class Client implements Serializable {

	/** Serializable version for Client. */
	private static final long serialVersionUID = 1L;
	/** The ID for the Client. */
	private int clientID;
	/** The first name of the Client. */
	private String firstName;
	/** The last name of the Client. */
	private String lastName;
	/**
	 * The phone number of the Client. This can either be a 11 digit number stored
	 * as a String, or if no phone number is provided will be "No phone number".
	 */
	private String phoneNumber;
	/** the email of the Client. */
	private String email;

	/**
	 * Construct a valid client when a phone number is given.
	 * 
	 * @param clientID    the ID for the Client object.
	 * @param firstName   the first name of the client.
	 * @param lastName    the last name of the client.
	 * @param phoneNumber the phone number of the client.
	 * @param email       the email of the client.
	 * 
	 */
	public Client(int clientID, String firstName, String lastName, String phoneNumber, String email) {

		this.clientID = clientID;
		setFirstName(firstName);
		setLastName(lastName);
		setPhoneNumber(phoneNumber);
		setEmail(email);

	}

	/**
	 * Construct a valid client when no phone number is given.
	 * 
	 * @param clientID    the ID for the Client object.
	 * @param firstName   the first name of the client.
	 * @param lastName    the last name of the client.
	 * @param email       the email of the client.
	 */
	public Client(int clientID, String firstName, String lastName, String email) {

		this.clientID = clientID;
		setFirstName(firstName);
		setLastName(lastName);
		phoneNumber = "No phonenumber";
		setEmail(email);

	}

	/**
	 * @return the ID of the client.
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * @return the first name of the client.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name of the client to this value.
	 * 
	 * @param firstName the name that will be assigned as the client's firstName.
	 */
	private void setFirstName(String firstName) {

		this.firstName = firstName;
	}

	/**
	 * @return the lastName of the client.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name of the client to this value.
	 * 
	 * @param lastName the name that will be assigned as the client's lastName.
	 */
	private void setLastName(String lastName) {

		this.lastName = lastName;
	}

	/**
	 * @return the phoneNumber of the client.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number of the client to this value.
	 * 
	 * @param phoneNumber the String that will be assigned as the client's
	 *                    phoneNumber.
	 */
	private void setPhoneNumber(String phoneNumber) {

		this.phoneNumber = phoneNumber;

	}

	/**
	 * @return the email of the client.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of the client to this value.
	 * 
	 * @param email the email that will be assigned as the client's email.
	 */
	private void setEmail(String email) {

		this.email = email;
	}

	@Override
	public String toString() {

		return String.format("Client:[clientID=%d, firstName=%s, lastName=%s, phoneNumber=%s, email=%s]", clientID,
				firstName, lastName, phoneNumber, email);

	}

}
