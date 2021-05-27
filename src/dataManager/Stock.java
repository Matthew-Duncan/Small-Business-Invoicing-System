/**
 * 
 */
package dataManager;

import java.io.Serializable;

/**
 * Encapsulate data about stock.
 * 
 * @author Matthew Duncan 561630
 *
 */
public class Stock implements Serializable {

	/** Serializable version for Stock. */
	private static final long serialVersionUID = 1L;
	/** The ID for the stock.*/
	private int stockID;
	/** The name of the stock.*/
	private String name;
	/** The description of the stock.*/
	private String description;
	/** The price of the stock.*/
	private double price;
	/** The quantity of the stock.*/
	private int quantity;

	/**
	 * Construct a valid Stock object.
	 * 
	 * @param stockID The ID for the stock.
	 * @param name The name of the stock.
	 * @param description The description of the stock.
	 * @param price The price of the stock.
	 * @param quantity The quantity of the stock.
	 */
	public Stock(int stockID, String name, String description, double price, int quantity) {

		this.stockID = stockID;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;

	}

	/**
	 * @return the id of the stock.
	 */
	public int getStockID() {
		return stockID;
	}

	/**
	 * @return the name of the stock.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description of the stock.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the price of the stock.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the quantity of the stock.
	 */
	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {

		return String.format("Stock:[stockID=%d, name=%s, description=%s, price=%d, quantity=%d]", stockID, name,
				description, price, quantity);

	}

}
