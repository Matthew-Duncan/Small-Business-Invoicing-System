package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import dataManager.Client;
import dataManager.DataManager;
import dataManager.Stock;
import dataManager.Transaction;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A javaFX based user interface for the invoice system.
 * 
 * @author Matthew Duncan 561630
 */

public class Driver extends Application {

	/** File name for stock client and transaction files. */
	String fileNameStock = ("systemStockData.bin");
	String fileNameClient = ("systemClientData.bin");
	String fileNameTransaction = ("systemTransactionData.bin");

	/** File objects for stock client and transaction files. */
	File fileStockLocation = new File(fileNameStock);
	File fileClientLocation = new File(fileNameClient);
	File fileTransactionLocation = new File(fileNameTransaction);

	/** Data manager for the system. */
	private DataManager dataManager;

	/**
	 * Runs the user interface.
	 * 
	 * TODO Clean up if you have time.
	 * 
	 */
	@Override
	public void start(Stage primaryStage) {

		try {
			/** Creating the DataManager passing in the 3 file loactions. */
			dataManager = new DataManager(fileStockLocation, fileClientLocation, fileTransactionLocation);
		} catch (ClassNotFoundException e) {

			Alert classError = new Alert(AlertType.ERROR);
			classError.setContentText("ClassNotFoundException encountered. Program has encountered a criticle error.");
			classError.show();

		} catch (FileNotFoundException e) {

			Alert fileError = new Alert(AlertType.ERROR);
			fileError.setContentText("FileNotFoundException encountered. The file(s) cannot be found.");
			fileError.show();

		} catch (IOException e) {

			Alert ioError = new Alert(AlertType.ERROR);
			ioError.setContentText(
					"IOException encountered. There is a problem with the input or output. There may be a problem with the file(s).");
			ioError.show();

		} finally {

			try {

				primaryStage.setTitle("Candyman Invoicing");

				FileInputStream input = new FileInputStream("src/logo.png");
				Image logo = new Image(input);

				/** Creates the image object used in the main menu. */
				ImageView imageMainMenu = new ImageView(logo);
				imageMainMenu.setFitHeight(200);
				imageMainMenu.setFitWidth(200);
				imageMainMenu.setX(500);

				/** Creates the image object used in the stock menu. */
				ImageView imageStockMenu = new ImageView(logo);
				imageStockMenu.setFitHeight(200);
				imageStockMenu.setFitWidth(200);
				imageStockMenu.setX(500);

				/** Creates the image object used in the client menu. */
				ImageView imageClientMenu = new ImageView(logo);
				imageClientMenu.setFitHeight(200);
				imageClientMenu.setFitWidth(200);
				imageClientMenu.setX(500);

				/** Creates the image object used in the invoice menu. */
				ImageView imageInvoiceMenu = new ImageView(logo);
				imageInvoiceMenu.setFitHeight(200);
				imageInvoiceMenu.setFitWidth(200);
				imageInvoiceMenu.setX(500);

				/** Create the image object used in the report menu. */
				ImageView imageReportMenu = new ImageView(logo);
				imageReportMenu.setFitHeight(200);
				imageReportMenu.setFitWidth(200);
				imageReportMenu.setX(500);

				/** Create the image object used in the stock menu. */
				ImageView imageAddStock = new ImageView(logo);
				imageAddStock.setFitHeight(200);
				imageAddStock.setFitWidth(200);
				imageAddStock.setX(500);

				/** Create the image object used in the add client menu. */
				ImageView imageAddClient = new ImageView(logo);
				imageAddClient.setFitHeight(200);
				imageAddClient.setFitWidth(200);
				imageAddClient.setX(500);

				/** Create the image object used in the add transaction menu. */
				ImageView imageAddTransaction = new ImageView(logo);
				imageAddTransaction.setFitHeight(200);
				imageAddTransaction.setFitWidth(200);
				imageAddTransaction.setX(500);

				/** Create all the buttons used in the main menu. */
				Button btnManageStock = new Button("Manage Stock");
				Button btnManageClients = new Button("Manage Clients");
				Button btnGenerateReport = new Button("Generate Report");
				Button btnGenerateInvoice = new Button("Generate Invoice");
				Button btnNewTransaction = new Button("New Transaction");

				/** Create all the buttons used in the stock menu. */
				Button btnAddStock = new Button("Add Stock");
				Button btnEditStock = new Button("Edit Stock");
				Button btnDeleteStock = new Button("Delete Stock");
				Button btnMainMenuStock = new Button("Main Menu");

				/** Create all the buttons used int the client menu. */
				Button btnAddClient = new Button("Add Client");
				Button btnUpdateClient = new Button("Update Client");
				Button btnRemoveClient = new Button("Remove Client");
				Button btnMainMenuClient = new Button("Main Menu");

				/** Create all the buttons and text used in the invoice menu. */
				Button btnGenerateInvoiceProcess = new Button("Generate Invoice");
				Button btnMainMenuInvoice = new Button("Main Menu");
				TextField txtFieldInvoiceTransactionID = new TextField();
				Text txtTransactionIDInvoice = new Text((" Transaction ID: "));

				/** Create the string of options used in the comboBox xmboxReportType. */
				String reportTopics[] = { "Customer Report", "Stock Report", "Custom Date Range Report" };

				/** Create all the buttons, text and date pickers used in the report menu. */
				@SuppressWarnings({ "unchecked", "rawtypes" })
				ComboBox cmboxReportType = new ComboBox(FXCollections.observableArrayList(reportTopics));
				Text txtGenerateReport = new Text((" Generate Report: "));
				Text txtStartDate = new Text((" Start Date: "));
				Text txtEndDate = new Text((" End Date: "));
				DatePicker dtStartDate = new DatePicker();
				DatePicker dtEndDate = new DatePicker();
				Button btnMainMenuReport = new Button("Main Menu");
				Button btnGenerateReportProcess = new Button("Generate Report");

				/** Create all the text, textFields and buttons used in the ass stock menu. */
				Text txtAddStock = new Text(" Add stock: ");
				TextField txtFieldName = new TextField("Name");
				TextField txtFieldQuantity = new TextField("Quantity");
				TextField txtFieldPrice = new TextField("Price");
				TextField txtFieldDescription = new TextField("Description");
				Button btnAddStockProcess = new Button("Add Stock");
				Button btnMainMenuAddStock = new Button("Main Menu");

				/** Create all the text, text fields and buttons used in the add client menu. */
				Text txtAddClient = new Text(" Add client: ");
				TextField txtFieldFirstName = new TextField("First Name");
				TextField txtFieldLastName = new TextField("Last Name");
				TextField txtFieldPhoneNumber = new TextField("Phone Number");
				TextField txtFieldEmail = new TextField("Email");
				Button btnAddClientProcess = new Button("Add Client");
				Button btnMainMenuAddClient = new Button("Main Menu");

				/**
				 * Create all the text, text fields and buttons used in the add transaction
				 * menu.
				 */
				Text txtAddTransaction = new Text(" Add transaction: ");
				TextField txtFieldTransactionClientID = new TextField("ClientID");
				TextField txtFieldTransactionStockID = new TextField("StockID");
				TextField txtFieldTransactionQuantity = new TextField("Quantity");
				Button btnAddTransactionProcess = new Button("Add Transaction");
				Button btnMainMenuAddTransaction = new Button("Main Menu");

				/** Create all the text and buttons used in the generated invoice. */
				Text txtInvoiceFirstName = new Text("    First name: ");
				Text txtInvoiceFirstNameDisplay = new Text();
				Text txtInvoiceLastName = new Text("    Last name: ");
				Text txtInvoiceLastNameDisplay = new Text();
				Text txtInvoiceStock = new Text("    Stock purchased: ");
				Text txtInvoiceStockDisplay = new Text();
				Text txtInvoiceQuantity = new Text("    Quantity: ");
				Text txtInvoiceQuantityDisplay = new Text();
				Text txtInvoicePrice = new Text("    Price:    £");
				Text txtInvoicePriceDisplay = new Text();
				Text txtInvoiceTotal = new Text("    Total:    £");
				Text txtInvoiceTotalDisplay = new Text();
				Button btnMainMenuInvoiceForm = new Button(" Main Menu ");

				/** Creating all of the hboxes used to display objects in scenes. */
				HBox hboxMain = new HBox(imageMainMenu, btnManageStock, btnManageClients, btnGenerateReport,
						btnGenerateInvoice, btnNewTransaction);
				HBox hboxStock = new HBox(imageStockMenu, btnAddStock, btnEditStock, btnDeleteStock, btnMainMenuStock);
				HBox hboxClients = new HBox(imageClientMenu, btnAddClient, btnUpdateClient, btnRemoveClient,
						btnMainMenuClient);
				HBox hboxInvoice = new HBox(imageInvoiceMenu, txtTransactionIDInvoice, txtFieldInvoiceTransactionID,
						btnGenerateInvoiceProcess, btnMainMenuInvoice);
				HBox hboxReport = new HBox(imageReportMenu, txtGenerateReport, cmboxReportType, txtStartDate,
						dtStartDate, txtEndDate, dtEndDate, btnGenerateReportProcess, btnMainMenuReport);
				HBox hboxAddStock = new HBox(imageAddStock, txtAddStock, txtFieldName, txtFieldQuantity, txtFieldPrice,
						txtFieldDescription, btnAddStockProcess, btnMainMenuAddStock);
				HBox hboxAddClient = new HBox(imageAddClient, txtAddClient, txtFieldFirstName, txtFieldLastName,
						txtFieldPhoneNumber, txtFieldEmail, btnAddClientProcess, btnMainMenuAddClient);
				HBox hboxAddTransaction = new HBox(imageAddTransaction, txtAddTransaction, txtFieldTransactionClientID,
						txtFieldTransactionStockID, txtFieldTransactionQuantity, btnAddTransactionProcess,
						btnMainMenuAddTransaction);
				HBox hboxGeneratedInvoice = new HBox(txtInvoiceFirstName, txtInvoiceFirstNameDisplay,
						txtInvoiceLastName, txtInvoiceLastNameDisplay, txtInvoiceStock, txtInvoiceStockDisplay,
						txtInvoiceQuantity, txtInvoiceQuantityDisplay, txtInvoicePrice, txtInvoicePriceDisplay,
						txtInvoiceTotal, txtInvoiceTotalDisplay, btnMainMenuInvoiceForm);
				hboxMain.setAlignment(Pos.TOP_CENTER);

				/** Used to create scenes that will display the hboxes. */
				Scene mainMenu = new Scene(hboxMain, 1000, 800);
				Scene stockMenu = new Scene(hboxStock, 1000, 800);
				Scene clientMenu = new Scene(hboxClients, 1000, 800);
				Scene invoiceMenu = new Scene(hboxInvoice, 1000, 800);
				Scene reportMenu = new Scene(hboxReport, 1000, 800);
				Scene addStock = new Scene(hboxAddStock, 1000, 800);
				Scene addClient = new Scene(hboxAddClient, 1000, 800);
				Scene addTransaction = new Scene(hboxAddTransaction, 1000, 800);
				Scene generatedInvoice = new Scene(hboxGeneratedInvoice, 1000, 800);

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the stock menu.
				 */
				btnManageStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(stockMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the client menu.
				 */
				btnManageClients.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(clientMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the invoice menu.
				 */
				btnGenerateInvoice.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(invoiceMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the report menu.
				 */
				btnGenerateReport.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(reportMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the add stock menu.
				 */
				btnAddStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(addStock);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the add client menu.
				 */
				btnAddClient.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(addClient);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button takes
				 * the user to the add transaction menu.
				 */
				btnNewTransaction.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(addTransaction);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuClient.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuInvoice.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuReport.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuAddStock.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuAddClient.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuAddTransaction.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * Activates the action event when the button is clicked on. This button returns
				 * the user to the main menu.
				 */
				btnMainMenuInvoiceForm.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						primaryStage.setScene(mainMenu);

					}
				});

				/**
				 * This button gets the data entered into the text fields and tries to create a
				 * Stock object, all text fields are cleared and the user is notified if
				 * successful, otherwise the appropriate exception message will notify the user
				 * that something went wrong.
				 */
				btnAddStockProcess.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						try {
							dataManager.addStock(txtFieldName.getText(), txtFieldDescription.getText(),
									Double.parseDouble(txtFieldPrice.getText()),
									Integer.parseInt(txtFieldQuantity.getText()));
							Alert stockSuccess = new Alert(AlertType.INFORMATION);
							stockSuccess.setContentText("Stock added successfully");
							txtFieldName.clear();
							txtFieldDescription.clear();
							txtFieldPrice.clear();
							txtFieldQuantity.clear();
							stockSuccess.show();
						} catch (NumberFormatException e) {

							Alert numberError = new Alert(AlertType.ERROR);
							numberError.setContentText(
									"NumberFormatException encountered. Price has to be a number in the format 0.00 and quantity must be a whole number.");
							numberError.show();

						} catch (FileNotFoundException e) {

							Alert fileError = new Alert(AlertType.ERROR);
							fileError.setContentText("FileNotFoundException encountered. The file(s) cannot be found.");
							fileError.show();

						} catch (IOException e) {

							Alert ioError = new Alert(AlertType.ERROR);
							ioError.setContentText(
									"IOException encountered. There is a problem with the input or output. There may be a problem with the file(s).");
							ioError.show();

						} catch (IllegalArgumentException e) {

							Alert argumentError = new Alert(AlertType.ERROR);
							argumentError.setContentText(
									"IllegalArgumentException encountered. Only phone number may be left blank, all other fields must be filled out.");
							argumentError.show();

						}

					}
				});
				/**
				 * This button gets the data entered into the text fields and tries to create a
				 * Client object, all text fields are cleared and the user is notified if
				 * successful, otherwise the appropriate exception message will notify the user
				 * that something went wrong.
				 */
				btnAddClientProcess.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						try {
							dataManager.addClient(txtFieldFirstName.getText(), txtFieldLastName.getText(),
									txtFieldPhoneNumber.getText(), txtFieldEmail.getText());
							Alert clientSuccess = new Alert(AlertType.INFORMATION);
							clientSuccess.setContentText("Client added successfully");
							txtFieldFirstName.clear();
							txtFieldLastName.clear();
							txtFieldPhoneNumber.clear();
							txtFieldEmail.clear();
							clientSuccess.show();
						} catch (IOException e) {

							Alert ioError = new Alert(AlertType.ERROR);
							ioError.setContentText(
									"IOException encountered. There is a problem with the input or output. There may be a problem with the file(s).");
							ioError.show();

						} catch (NumberFormatException e) {

							Alert numberError = new Alert(AlertType.ERROR);
							numberError.setContentText(
									"NumberFormatException encountered. The phone number must be 11 characters long and only numbers may be used.");
							numberError.show();

						} catch (IllegalArgumentException e) {

							Alert argumentError = new Alert(AlertType.ERROR);
							argumentError.setContentText(
									"IllegalArgumentException encountered. Only phone number may be left blank, all other fields must be filled out.");
							argumentError.show();

						}

					}
				});

				/**
				 * This button gets the data entered into the text fields and tries to create a
				 * Transaction object, all text fields are cleared and the user is notified if
				 * successful, otherwise the appropriate exception message will notify the user
				 * that something went wrong.
				 */
				btnAddTransactionProcess.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						try {
							dataManager.addTransaction(Integer.parseInt(txtFieldTransactionClientID.getText()),
									Integer.parseInt(txtFieldTransactionStockID.getText()),
									Integer.parseInt(txtFieldTransactionQuantity.getText()));
							Alert transactionSuccess = new Alert(AlertType.INFORMATION);
							transactionSuccess.setContentText("Transaction added successfully");
							txtFieldTransactionClientID.clear();
							txtFieldTransactionStockID.clear();
							txtFieldTransactionQuantity.clear();
							transactionSuccess.show();
						} catch (NumberFormatException e) {

							Alert numberError = new Alert(AlertType.ERROR);
							numberError.setContentText(
									"NumberFormatException encountered. ClientID, StockID and quantity must be whole numbers.");
							numberError.show();

						} catch (FileNotFoundException e) {

							Alert fileError = new Alert(AlertType.ERROR);
							fileError.setContentText("FileNotFoundException encountered. The file(s) cannot be found.");
							fileError.show();

						} catch (IOException e) {

							Alert ioError = new Alert(AlertType.ERROR);
							ioError.setContentText(
									"IOException encountered. There is a problem with the input or output. There may be a problem with the file(s).");
							ioError.show();

						} catch (IndexOutOfBoundsException e) {

							Alert indexError = new Alert(AlertType.ERROR);
							indexError.setContentText(
									"IndexOutOfBoundsException encountered. One of the IDs that were entered do not exist.");
							indexError.show();

						} catch (NullPointerException e) {

							Alert indexError = new Alert(AlertType.ERROR);
							indexError.setContentText(
									"NullPointerException encountered. One of the IDs that were entered do not exist.");
							indexError.show();

						}
					}
				});
				/**
				 * This button gets the Transaction object associated with the transactionID, it
				 * then pulls the clientID and the stockID and gets the relevant information to
				 * be displayed for the invoice, the total is calculated using the Stock price
				 * multiplied by the quantity in the Transaction object.
				 */
				btnGenerateInvoiceProcess.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						try {
							Transaction invoice = dataManager
									.generateInvoice(Integer.parseInt(txtFieldInvoiceTransactionID.getText()));
							Client invoiceClient = dataManager.getClient(invoice.getClientID());
							Stock invoiceStock = dataManager.getStock(invoice.getStockID());

							txtInvoiceFirstNameDisplay.setText(invoiceClient.getFirstName());
							txtInvoiceLastNameDisplay.setText(invoiceClient.getLastName());
							txtInvoiceStockDisplay.setText(invoiceStock.getName());
							txtInvoiceQuantityDisplay.setText(Integer.toString(invoice.getQuantity()));
							txtInvoicePriceDisplay.setText(Double.toString(invoiceStock.getPrice()));
							txtInvoiceTotalDisplay
									.setText(Double.toString(invoiceStock.getPrice() * (invoice.getQuantity())));

							primaryStage.setScene(generatedInvoice);

							Alert invoiceSuccess = new Alert(AlertType.INFORMATION);
							invoiceSuccess.setContentText("Invoice generated successfully");
							invoiceSuccess.show();
						} catch (IndexOutOfBoundsException e) {

							Alert indexError = new Alert(AlertType.ERROR);
							indexError.setContentText(
									"IndexOutOfBoundsException encountered. One of the IDs that were entered do not exist.");
							indexError.show();

						}
					}
				});

				primaryStage.setScene(mainMenu);
				primaryStage.show();

				primaryStage.show();
			} catch (Exception e) {

				Alert error = new Alert(AlertType.ERROR);
				error.setContentText(
						"Critical error. Please restart the program, if the error persists please contact the developer.");
				error.show();

			}
		}

	}

	/**
	 * The entry point for the system.
	 * 
	 * @param args ignored.
	 */
	public static void main(String[] args) {

		launch(args);

	}

}
