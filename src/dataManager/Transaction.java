/**
 * 
 */
package dataManager;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Encapsulate data about transactions.
 * 
 * @author Matthew Duncan 561630
 *
 */
public class Transaction implements Serializable {

	/** Serializable version for Transaction. */
	private static final long serialVersionUID = 1L;
	/** The ID for the transaction. */
	private int transactionID;
	/** The ID for the client. */
	private int clientID;
	/** The ID for the stock. */
	private int stockID;
	/** The quantity of stock involved in the transaction. */
	private int quantity;
	/** The date of the transaction. */
	private LocalDate transactionDate;

	/**
	 * Construct a valid Transaction object.
	 * 
	 * @param transactionID The ID for the transaction.
	 * @param clientID      The ID for the client.
	 * @param stockID       The ID for the stock.
	 * @param quantity      The quantity of stock involved in the transaction.
	 */
	public Transaction(int transactionID, int clientID, int stockID, int quantity) {

		this.transactionID = transactionID;
		this.clientID = clientID;
		this.stockID = stockID;
		this.quantity = quantity;
		transactionDate = LocalDate.now();
	}

	/**
	 * @return the ID of the transaction.
	 */
	public int getTransactionID() {
		return transactionID;
	}

	/**
	 * @return the ID of the client in the transaction.
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * @return the ID of the stock in the transaction.
	 */
	public int getStockID() {
		return stockID;
	}

	/**
	 * @return the quantity of stock in the transaction.
	 */
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {

		return String.format("Transaction:[transactionID=%d, clientID=%d, StockID=%d, quantity=%d]", transactionID,
				clientID, stockID, quantity);

	}

}
