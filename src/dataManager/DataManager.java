/**
 * 
 */
package dataManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 * Manage a persistent collection of Clients, Stock and Transactions in seperate
 * files, each object has its own file location specified when the DataManager
 * is created.
 * 
 * @author Matthew Duncan 561630
 *
 */
public class DataManager {

	/** The list of Client objects. */
	LinkedList<Client> clientMap = new LinkedList<Client>();
	/** The list of Stock objects. */
	LinkedList<Stock> stockMap = new LinkedList<Stock>();
	/** The list of Transaction objects. */
	LinkedList<Transaction> transactionMap = new LinkedList<Transaction>();
	/**
	 * The location of the Stock file, this file is automatically loaded and is
	 * saved each time a new Stock object is added.
	 */
	private File storageLocationStock;
	/**
	 * The location of the Client file, this file is automatically loaded and is
	 * saved each time a new Client object is added.
	 */
	private File storageLocationClient;
	/**
	 * The location of the Transaction file, this file is automatically loaded and
	 * is saved each time a new Transaction object is added.
	 */
	private File storageLocationTransaction;

	/**
	 * Construct a new data manager, initializing the storage and loading any
	 * records from the given files. If the files do not yet exist new files are
	 * created, if the file is empty nothing is loaded but new filed are not created
	 * either.
	 * 
	 * @param storageLocationStock       the location for persistent storage of
	 *                                   Stock objects.
	 * @param storageLocationClient      the location for persistent storage of
	 *                                   Client objects.
	 * @param storageLocationTransaction the location for the persistent storage of
	 *                                   Transaction objects.
	 * 
	 * @throws ClassNotFoundException if there is a problem finding one of the Class
	 *                                definitions.
	 * @throws FileNotFoundException  if there is a problem finding one of the
	 *                                files.
	 * @throws IOException            if there is a problem reading or writing to
	 *                                one of the files.
	 */
	public DataManager(File storageLocationStock, File storageLocationClient, File storageLocationTransaction)
			throws ClassNotFoundException, FileNotFoundException, IOException {

		this.storageLocationStock = storageLocationStock;
		this.storageLocationClient = storageLocationClient;
		this.storageLocationTransaction = storageLocationTransaction;

		loadStock();
		loadClient();
		loadTransaction();
	}

	/**
	 * Add a new client to the system, generating a new unique ID for them.
	 * 
	 * @param firstName   the first name of the client.
	 * @param lastName    the last name of the client.
	 * @param phoneNumber the phone number of the client.
	 * @param email       the email of the client.
	 * 
	 * @throws FileNotFoundException    if there is a problem finding the location
	 *                                  of one or more of the files.
	 * @throws IOException              if there is a problem reading or writing to
	 *                                  the files.
	 * @throws NumberFormatException    if phoneNumber is not formatted as a 11
	 *                                  character String using numbers 0-9.
	 * @throws IllegalArgumentException if firstName, lastName or email is left
	 *                                  blank.
	 */
	public void addClient(String firstName, String lastName, String phoneNumber, String email)
			throws FileNotFoundException, IOException, NumberFormatException, IllegalArgumentException {

		int clientID;

		if (clientMap.isEmpty()) {
			clientID = 1;
		} else {

			clientID = (clientMap.getLast().getClientID() + 1);
		}

		if (firstName.equals(null) || firstName.equals("") || lastName.equals(null) || lastName.equals("")
				|| email.equals(null) || email.equals("")) {

			throw new IllegalArgumentException();

		}

		if (phoneNumber.equals(null) || phoneNumber.equals("")) {
			Client client = new Client(clientID, firstName, lastName, email);
			clientMap.addLast(client);
		} else {

			if (!phoneNumber.matches("[0-9]{11}")) {

				throw new NumberFormatException();

			}

			Client client = new Client(clientID, firstName, lastName, phoneNumber, email);
			clientMap.addLast(client);
		}
		saveClient();
	}

	/**
	 * Gets the Client object associated with the clientID.
	 * 
	 * @param clientID the ID of the client.
	 * 
	 * @return the Client object associated with the clientID.
	 */
	public Client getClient(int clientID) {
		return clientMap.get(clientID - 1);
	}

	/**
	 * Add a new Transaction object to the system.
	 * 
	 * @param clientID the ID of the client involved with the transaction.
	 * @param stockID  the ID of the stock that is being purchased.
	 * @param quantity the quantity of stock that is being purchased.
	 * 
	 * @throws FileNotFoundException     if there is a problem finding one or more
	 *                                   of the files.
	 * @throws IOException               if there is a problem reading or writing to
	 *                                   one of the files.
	 * @throws NullPointerException      if one of the pointers provided is null and
	 *                                   if the IndexOutOfBoundsException is not
	 *                                   caught.
	 * @throws IndexOutOfBoundsException if the clientID or stockID does not exist.
	 */
	public void addTransaction(int clientID, int stockID, int quantity)
			throws FileNotFoundException, IOException, NullPointerException, IndexOutOfBoundsException {

		if (!Integer.toString(clientID).matches("\\d") || !Integer.toString(stockID).matches("\\d")) {

			throw new NumberFormatException();

		}

		if (!clientMap.contains(clientMap.get(clientID - 1)) || (!stockMap.contains(stockMap.get(stockID - 1)))) {

			throw new IndexOutOfBoundsException();

		}

		int transactionID;

		if (transactionMap.isEmpty()) {
			transactionID = 1;
		} else {

			transactionID = (transactionMap.getLast().getTransactionID() + 1);
		}
		Transaction transaction = new Transaction(transactionID, clientID, stockID, quantity);
		transactionMap.addLast(transaction);
		saveTransaction();
	}

	/**
	 * Generates an invoice for the Transaction that is associated with the
	 * transactionID.
	 * 
	 * @param transactionID the ID for the transaction.
	 * 
	 * @return the Transaction object associated with the transactionID.
	 * 
	 * @throws IndexOutOfBounds Exception if there is not Transaction object
	 *                          associated with the transactionID.
	 */
	public Transaction generateInvoice(int transactionID) {

		return transactionMap.get((transactionID - 1));

	}

	/**
	 * Add a new Stock object to the system.
	 * 
	 * @param name        the name of the Stock.
	 * @param description the description of the stock.
	 * @param price       the price of the stock.
	 * @param quantity    the quantity of the stock.
	 * 
	 * @throws FileNotFoundException if there is a problem finding one of the files.
	 * @throws IOException           if there is a problem reading or writing to one
	 *                               of the files.
	 */
	public void addStock(String name, String description, double price, int quantity)
			throws FileNotFoundException, IOException {

		int stockID;

		if (stockMap.isEmpty()) {
			stockID = 1;
		} else {

			stockID = (stockMap.getLast().getStockID() + 1);
		}

		if (name.equals(null) || name.equals("") || description.equals(null) || description.equals("")) {

			throw new IllegalArgumentException();

		}

		Stock stock = new Stock(stockID, name, description, price, quantity);
		stockMap.addLast(stock);
		saveStock();
	}

	/**
	 * Get the Stock object associated with the stockID.
	 * 
	 * @param stockID the ID of the Stock.
	 * 
	 * @return the Stock object associated with the stockID.
	 */
	public Stock getStock(int stockID) {
		return stockMap.get(stockID - 1);
	}

	/**
	 * Save the stock to the File given by the storageLocationStock.
	 * 
	 * @throws FileNotFoundException if there is a problem finding one of the files.
	 * @throws IOException           if there is an issue reading or writing to one
	 *                               of the files.
	 */
	private void saveStock() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageLocationStock))) {
			oos.writeObject(stockMap);

		}
	}

	/**
	 * Save the client to the File given by the storageLocationClient.
	 * 
	 * @throws FileNotFoundException if there is a problem finding one of the files.
	 * @throws IOException           if there is an issue reading or writing to one
	 *                               of the files.
	 */
	private void saveClient() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageLocationClient))) {
			oos.writeObject(clientMap);

		}
	}

	/**
	 * Save the transaction to the File given by the storageLocationTransaction.
	 * 
	 * @throws FileNotFoundException if there is a problem finding one of the files.
	 * @throws IOException           if there is an issue reading or writing to one
	 *                               of the files.
	 */
	private void saveTransaction() throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storageLocationTransaction))) {
			oos.writeObject(transactionMap);

		}
	}

	/**
	 * Load the stock from the File given by the storageLocationStock
	 * 
	 * @throws FileNotFoundException  if there is a problem finding one of the
	 *                                files.
	 * @throws IOException            if there is an issue reading or writing to one
	 *                                of the files.
	 * @throws ClassNotFoundException if there is a problem finding the class
	 *                                definition.
	 */
	@SuppressWarnings("unchecked")
	private void loadStock() throws FileNotFoundException, IOException, ClassNotFoundException {
		// read the storage, if it exists
		if (storageLocationStock.exists() && storageLocationStock.length() > 0) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageLocationStock))) {
				stockMap = (LinkedList<Stock>) ois.readObject();
			}

		} else {
			storageLocationStock.createNewFile();
		}

	}

	/**
	 * Load the client from the File given by the storageLocationClient
	 * 
	 * @throws FileNotFoundException  if there is a problem finding one of the
	 *                                files.
	 * @throws IOException            if there is an issue reading or writing to one
	 *                                of the files.
	 * @throws ClassNotFoundException if there is a problem finding the class
	 *                                definition.
	 */
	@SuppressWarnings("unchecked")
	private void loadClient() throws FileNotFoundException, IOException, ClassNotFoundException {
		// read the storage, if it exists
		if (storageLocationClient.exists() && storageLocationClient.length() > 0) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageLocationClient))) {
				clientMap = (LinkedList<Client>) ois.readObject();
			}
		} else {
			storageLocationClient.createNewFile();
		}

	}

	/**
	 * Load the transaction from the File given by the storageLocationTransaction
	 * 
	 * @throws FileNotFoundException  if there is a problem finding one of the
	 *                                files.
	 * @throws IOException            if there is an issue reading or writing to one
	 *                                of the files.
	 * @throws ClassNotFoundException if there is a problem finding the class
	 *                                definition.
	 */
	@SuppressWarnings("unchecked")
	private void loadTransaction() throws FileNotFoundException, IOException, ClassNotFoundException {
		// read the storage, if it exists
		if (storageLocationTransaction.exists() && storageLocationTransaction.length() > 0) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storageLocationTransaction))) {
				transactionMap = (LinkedList<Transaction>) ois.readObject();
			}

		} else {
			storageLocationTransaction.createNewFile();
		}

	}

}
