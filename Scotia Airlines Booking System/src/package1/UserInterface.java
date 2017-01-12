package package1;


import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//User interface class
//Used to generate ui;
public class UserInterface 
{
	//instance variables
	private int buttonCounter = 2;
	
	private  Airline myAirline;
	
	
	//Constructor
	public UserInterface(Airline aAirline)
	
	{
	
		myAirline =  aAirline;
	}
	
	//generic output method
	//used to display various messages within system
	public  void genericOutput(String inputMessage)
	{
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		myFrame.setLocationRelativeTo(null);
		
		
		
		JPanel myPanel = new JPanel();
	
		
		Container c1 = new Container();
		Container c2 = new Container();
		
		c1.setLayout(new GridLayout(1,0));
		c2.setLayout(new GridLayout(1,0));
		
		JLabel message = new JLabel(inputMessage);
		
		JButton backBtn = new JButton("Main Menu");
		
		c1.add(message);
		c2.add(backBtn);
		
		myPanel.add(c1);
		myPanel.add(c2);
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		backBtn.addActionListener(new ActionListener()
		{
				public void actionPerformed(ActionEvent e)
				{
					myFrame.dispose();
					mainMenu();
				}
		});
		
	}

	//main menu screen
	public void mainMenu()
	{
		//create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		//sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);
		
		
		
		//need atleast one panel on a window, default will be full size of frame
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(6, 1));
		
		
		JLabel title = new JLabel("Scotia Airlines - Main Menu", SwingConstants.CENTER);
		
		JButton flightAdminBtn = new JButton("Flight Administration");
		JButton bookingMenuBtn =  new JButton("Booking Menu");
		JButton exitBtn = new JButton("Exit");
		JButton displaySeatDetailsBtn = new JButton("Display Seat Details");
		JButton displayFlightDetailsBtn = new JButton("Display Flight Details");


		
		//add components to panel then panel to frame
		//order which items are added is the order that they will appear
		myPanel.add(title);
		myPanel.add(flightAdminBtn);
		myPanel.add(bookingMenuBtn);
		myPanel.add(displaySeatDetailsBtn);
		myPanel.add(displayFlightDetailsBtn);
		myPanel.add(exitBtn);
		myFrame.add(myPanel);
		
		myFrame.setVisible(true);
		
		
		flightAdminBtn.addActionListener(new ActionListener() {
			
			//default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				FlightAdminHomepage();
				
				
			}
		});
		

		//go to select flight screen for booking menu
		bookingMenuBtn.addActionListener(new ActionListener() {
			
			//default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				selectFlight("Booking");

				
				
			}
		});
		

		//go to select flight screen for display flight details
		displayFlightDetailsBtn.addActionListener(new ActionListener() {
			
			//default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				selectFlight("DisplayFlightDetails");
				
				
			}
		});
		
		//go to select seat screen for display seat details
		displaySeatDetailsBtn.addActionListener(new ActionListener() {
			
			//default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				selectFlight("DisplaySeatDetails");
				
				
			}
		});
		
		exitBtn.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myAirline.EmptyDB();
				myAirline.SaveSeatsToDB();
				myAirline.SavePassengersToDB();
				

				myFrame.dispose();
				System.exit(0);

			}
		});
	
	}
	
	//screen for flight admin page
	public void FlightAdminHomepage()
	{
		// create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		// sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);

		// need atleast one panel on a window, default will be full size of
		// frame
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(5, 1));

		JLabel title = new JLabel("Flight Administration", SwingConstants.CENTER);
		
		

		JButton changeFlightStatusBtn = new JButton("Change Flight Status");
		JButton addNewFlight = new JButton("Add New Flight");
		JButton backBtn = new JButton("Return To Main Menu");

		// add components to panel then panel to frame
		// order which items are added is the order that they will appear
		myPanel.add(title);
		myPanel.add(changeFlightStatusBtn);
		myPanel.add(addNewFlight);
		myPanel.add(backBtn);
		myFrame.add(myPanel);

		myFrame.setVisible(true);

		backBtn.addActionListener(new ActionListener() {
			
			//default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				mainMenu();
				
				
			}
		});
		
		addNewFlight.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				addNewFlight();
			}
		});
		
		changeFlightStatusBtn.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				selectFlight("ChangeFlightStatus");
			}
		});
		
	}
	
	//screen for adding a new flight
	public void addNewFlight()
	{
		// create frame object and size
				JFrame myFrame = new JFrame();
				myFrame.setSize(300,350);
				// sets frame in middle of the screen when null value is set
				myFrame.setLocationRelativeTo(null);

				// need atleast one panel on a window, default will be full size of
				// frame
				JPanel myPanel = new JPanel();
			
				
				Container c = new Container();
				Container c1 = new Container();
				Container c2 = new Container();
				
				c.setLayout(new GridLayout(1,0));
				c1.setLayout(new GridLayout(5,1));
				c2.setLayout(new GridLayout(3,1));
				
				JLabel title = new JLabel("Add New Flight", SwingConstants.CENTER);
				
			
				
				JLabel flightID = new JLabel("Flight ID: ");
				//how many characters wide, scrolls
				JTextField txtFlightID = new JTextField(10);
				
				JLabel departure = new JLabel("Departure: ");
				//how many characters wide, scrolls
				JTextField txtDeparture = new JTextField(10);
				
				JLabel arrival = new JLabel("Arrival: ");
				//how many characters wide, scrolls
				JTextField txtArrival = new JTextField(10);
				
				JLabel rows = new JLabel("Rows: ");
				//how many characters wide, scrolls
				JTextField txtRows = new JTextField(10);
				
				JLabel columns = new JLabel("Columns: ");
				//how many characters wide, scrolls
				JTextField txtColumns = new JTextField(10);
				
				
				
				JButton submitBtn = new JButton("Submit");
				JButton clearBtn = new JButton("Clear");
				JButton backBtn = new JButton("Back To Admin Options");
				
				
				c.add(title);
				c1.add(flightID);
				c1.add(txtFlightID);
				c1.add(departure);
				c1.add(txtDeparture);
				c1.add(arrival);
				c1.add(txtArrival);
				c1.add(rows);
				c1.add(txtRows);
				c1.add(columns);
				c1.add(txtColumns);
				c2.add(submitBtn);
				c2.add(clearBtn);
				c2.add(backBtn);
				
				myPanel.add(c);
				myPanel.add(c1);
				myPanel.add(c2);
				
				myFrame.add(myPanel);
				myFrame.setVisible(true);

		backBtn.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				FlightAdminHomepage();

			}
		});
		
		
		
		clearBtn.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				
				myFrame.dispose();
				addNewFlight();
			}
		});
		
		
		//submits the entered flight details
		submitBtn.addActionListener(new ActionListener() 
		{

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				
				String flightNo = txtFlightID.getText();
				String departure = txtDeparture.getText();
				String arrival = txtArrival.getText();
				int rows = Integer.parseInt(txtRows.getText());
				int columns = Integer.parseInt(txtColumns.getText());
				
				Flight newFlight = new Flight(flightNo, departure, arrival, rows, columns);
				newFlight.addFlightToDB();
				myAirline.addFlight(newFlight);
				
				
				myFrame.dispose();
				
				//display added flight
				genericOutput("Flight: " + flightNo + " added");
				
			}
		});
		
		
		
		
		
	}//end of add new Flight
	
	//select flight method, displays a list of flights
	//clicking on specified flight will take you to appropriate screen depending on status which is passed in
	public void selectFlight(String status)
	{
		
		buttonCounter = 2;
		//create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		//sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);
		
		//need atleast one panel on a window, defualt will be full size of frame
		JPanel myPanel = new JPanel();
		
		
		JLabel title = new JLabel("Select Flight", SwingConstants.CENTER);
		
		myPanel.add(title);
		
		//displaying all flights in hashmap
		for(Map.Entry<String, Flight> currentFlight: myAirline.getFlights().entrySet())
		{
			
			JButton tempButton = new JButton("No: " + currentFlight.getValue().getFlightNumber() + " Depature: " 
		+ currentFlight.getValue().getDeparture() + " Arrival: " + currentFlight.getValue().getArrival());
			
			
			tempButton.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					if(status.equalsIgnoreCase("Booking")){
						myFrame.dispose();
						DisplayBookingMenu(e.getActionCommand());
					}else if(status.equalsIgnoreCase("DisplayFlightDetails"))
					{
						Flight tempFlight = currentFlight.getValue();
						
						genericOutput(tempFlight.DisplayFlightInfo());
					}else if(status.equalsIgnoreCase("DisplaySeatDetails"))
							{
								getSeatno(currentFlight.getValue().getFlightNumber(), 4);
							}else if (status.equalsIgnoreCase("ChangeFlightStatus")){
								myFrame.dispose();
								DisplayChangeStatusMenu(e.getActionCommand());
							}
					
				}
			});
			
			buttonCounter +=1;
			myPanel.add(tempButton);
			
		}
		
		myPanel.setLayout(new GridLayout(buttonCounter, 1));
		
		JButton backBtn = new JButton("Return To Main Menu");
		
		
		
		
		myPanel.add(backBtn);
		myFrame.add(myPanel);
		
		
		
		myFrame.setVisible(true);

		backBtn.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				mainMenu();

			}
		});
		
	}
	
	//screen for changing flight status
	public void DisplayChangeStatusMenu(String FlightInfo)
	{
		//create frame object and size
				JFrame myFrame = new JFrame();
				myFrame.setSize(300, 350);
				//sets frame in middle of the screen when null value is set
				myFrame.setLocationRelativeTo(null);
				
				
				
				//need at least one panel on a window, default will be full size of frame
				JPanel myPanel = new JPanel();
				
				myPanel.setLayout(new GridLayout(6, 1));
				
				
				JLabel title = new JLabel("Change Flight Status", SwingConstants.CENTER);
				
				JButton btnSeatsAvailable = new JButton("Seats Available");
				JButton btnCheckingIn =  new JButton("Checking In");
				JButton btnBoarding = new JButton("Boarding");
				JButton btnFlightClosed = new JButton("Closed");
				JButton back = new JButton("Back To Flight Selection");
				
				//add components to panel then panel to frame
				//order which items are added is the order that they will appear
				myPanel.add(title);
				myPanel.add(btnSeatsAvailable);
				myPanel.add(btnCheckingIn);
				myPanel.add(btnBoarding);
				myPanel.add(btnFlightClosed);
				myPanel.add(back);
				myFrame.add(myPanel);
				
				myFrame.setVisible(true);
				
				
		////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		

				back.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						selectFlight("ChangeFlightStatus");

					}
				});
				
				btnSeatsAvailable.addActionListener(new ActionListener() {

					//splits by space
					String [] parsedFlightInfo = FlightInfo.split("\\s+");
					
					Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
					
					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						
						boolean boarding = chosenFlight.isBoarding();
						boolean closed = chosenFlight.isClosed();
						boolean checkingIn = chosenFlight.isCheckingIn();
						
						boarding = false;
						closed = false;
						checkingIn = false;
						
						if(chosenFlight.isFull() == false)
						{
							chosenFlight.setBoarding(boarding);
							chosenFlight.setCheckingIn(closed);
							chosenFlight.setClosed(checkingIn);
							chosenFlight.setStatusMessage(parsedFlightInfo[1] + " Seats Available");
							
							genericOutput("Seats now available on flight " + parsedFlightInfo[1]);
						}else{
							genericOutput("Error, flight " + parsedFlightInfo[1] + " is full");
						}

					}
				});
				btnCheckingIn.addActionListener(new ActionListener() {

					//splits by space
					String [] parsedFlightInfo = FlightInfo.split("\\s+");
					
					Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
					
					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						boolean boarding = chosenFlight.isBoarding();
						boolean closed = chosenFlight.isClosed();
						boolean checkingIn = chosenFlight.isCheckingIn();
						
						boarding = false;
						closed = false;
						checkingIn = true;
						
						
							chosenFlight.setBoarding(boarding);
							chosenFlight.setCheckingIn(checkingIn);
							chosenFlight.setClosed(closed);
							chosenFlight.setStatusMessage("Flight: "+ parsedFlightInfo[1] + " is Checking In");
							
							genericOutput (parsedFlightInfo[1] + " is now checking in");
						

					}
				});
				btnFlightClosed.addActionListener(new ActionListener() {

					//splits by space
					String [] parsedFlightInfo = FlightInfo.split("\\s+");
					
					Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
					
					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						boolean boarding = chosenFlight.isBoarding();
						boolean closed = chosenFlight.isClosed();
						boolean checkingIn = chosenFlight.isCheckingIn();
						
						boarding = false;
						closed = true;
						checkingIn = false;
						
						
							chosenFlight.setBoarding(boarding);
							chosenFlight.setCheckingIn(checkingIn);
							chosenFlight.setClosed(closed);
							chosenFlight.setStatusMessage("Flight: "+ parsedFlightInfo[1] + " is Closed");
							
							genericOutput(parsedFlightInfo[1] + " is now closed");
						

					}
				});
				
				btnBoarding.addActionListener(new ActionListener() {

					//splits by space
					String [] parsedFlightInfo = FlightInfo.split("\\s+");
					
					Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
					
					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						boolean boarding = chosenFlight.isBoarding();
						boolean closed = chosenFlight.isClosed();
						boolean checkingIn = chosenFlight.isCheckingIn();
						
						boarding = true;
						closed = true;
						checkingIn = false;
						
							chosenFlight.setBoarding(boarding);
							chosenFlight.setCheckingIn(checkingIn);
							chosenFlight.setClosed(closed);
							chosenFlight.setStatusMessage("Flight: "+ parsedFlightInfo[1] + " Flight is Boarding");
							
							genericOutput(parsedFlightInfo[1] + " is now boarding");
						

					}
				});
				
				back.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						selectFlight("ChangeFlightStatus");

					}
				});
				
	}
	
	//displays booking menu
	public void DisplayBookingMenu(String FlightInfo)
	{
		//create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		//sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);
		
		
		
		//need at least one panel on a window, default will be full size of frame
		JPanel myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(5, 1));
		
		
		JLabel title = new JLabel("Flight Information", SwingConstants.CENTER);
		
		JButton cancel = new JButton("Cancel Booking/Reservation");
		JButton reserve =  new JButton("Reserve A Seat");
		JButton booking = new JButton("Book A Seat");
		JButton back = new JButton("Back To Flight Selection");
		
		
		
		
////////////////////////////////////////Action Listeners///////////////////////////////////////////////////////////////		

		back.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				selectFlight("Booking");

			}
		});
		
		cancel.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//splits by space
				String [] parsedFlightInfo = FlightInfo.split("\\s+");
				
				Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
				
				boolean boarding = chosenFlight.isBoarding();
				boolean closed = chosenFlight.isClosed();
				
				if(boarding == true  || closed == true)
				{
					genericOutput("Cancellations not available. " + chosenFlight.getStatusMessage());
					
					return;
				}
				
				getSeatno(parsedFlightInfo[1], 1);
				myFrame.dispose();
				
			}
		});
		
		reserve.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//splits by space
				String [] parsedFlightInfo = FlightInfo.split("\\s+");
				
				Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
				
				boolean boarding = chosenFlight.isBoarding();
				boolean closed = chosenFlight.isClosed();
				boolean full = chosenFlight.isFull();
				boolean checkingIn = chosenFlight.isCheckingIn();
				
				
				if(boarding == true  || closed == true || full == true  || checkingIn == true )
				{
					genericOutput("Reservations not available. " + chosenFlight.getStatusMessage());
					
					return;
				}
				
				getSeatno(parsedFlightInfo[1], 2);
				myFrame.dispose();
				

			}
		});
		
		booking.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				//splits by space
				//use to get the flight number from flight info
				String [] parsedFlightInfo = FlightInfo.split("\\s+");
				
				Flight chosenFlight = myAirline.getFlights().get(parsedFlightInfo[1]);
				
				boolean boarding = chosenFlight.isBoarding();
				boolean closed = chosenFlight.isClosed();
				boolean full = chosenFlight.isFull();
				
				
				
				if(boarding == true  || closed == true || full == true)
				{
					
					genericOutput("Bookings not available. " + chosenFlight.getStatusMessage());
					
					return;
				}
				
				getSeatno(parsedFlightInfo[1], 3);
				myFrame.dispose();
				

			}
		});
		
		
		
		//add components to panel then panel to frame
		//order which items are added is the order that they will appear
		myPanel.add(title);
		myPanel.add(cancel);
		myPanel.add(reserve);
		myPanel.add(booking);
		myPanel.add(back);
		myFrame.add(myPanel);
		
		myFrame.setVisible(true);
	
	
	}
	
	//get seatNo method
	//takes in seatNo
	public void getSeatno(final String flightNo, final int choice)
	{
		
		String labelDisplay = "";
		String buttonDisplay = "";
		
		
		// create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		// sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);
				
		// need at least one panel on a window, default will be full size of
		// frame
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(4,1));

		
		//tailors text in frame dependent on requested action
		switch(choice)
		{
		
		case 1:
			labelDisplay = "To Cancel";
			buttonDisplay = "Cancellation";
			break;
			
		case 2:
			labelDisplay = "To Reserve";
			buttonDisplay = "Reservation";
			break;
			
			
		case 3:
			labelDisplay = "To Book";
			buttonDisplay = "Booking";
			break;
			
		case 4:
			labelDisplay = "To View";
			buttonDisplay = " ";
			break;
			
		case -1:
			break;
			
		}//end switch
		
		
		JLabel heading = new JLabel("Enter Seat No " + labelDisplay, SwingConstants.CENTER);
		
		JTextField inputText = new JTextField();
		
		JButton btnSubmit = new JButton("Submit " + buttonDisplay);
		JButton btnBack = new JButton("Back to Flight Selection");
		
		myPanel.add(heading);
		myPanel.add(inputText);
		myPanel.add(btnSubmit);
		myPanel.add(btnBack);
		
		
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		
//////////////////////////////////////////////////Action Listeners////////////////////////////////////////////////////////	
		
		
		btnSubmit.addActionListener(new ActionListener() {
			
			
			
			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String seatNo = inputText.getText().toString();
				
				
				
				Flight currentFlight = myAirline.getFlights(flightNo);
				
				if(currentFlight.IsValidSeatNumber(seatNo) == false)
				{
					genericOutput(seatNo + " is not a valid seat number");
				}
				
				else
				{
				//determine status of seat after receiving passenger choice
				if(choice == 1)
				{
					int updateCounters = -1;
					Seat tempSeat = myAirline.getSeat(flightNo, seatNo);
					
					if(tempSeat.getCurrentStatus() == 1)
					{
						updateCounters = -1; //update seat counters
						tempSeat.setCurrentStatus(1);
						currentFlight.updateSeat(updateCounters);
						
						currentFlight.CalculateTotalFlightTakings(); //recalculate total flight takings
						
						genericOutput("Error " + tempSeat.getSeatNumber() +"Seat Is Already Free");
						
						
					}else if(tempSeat.getCurrentStatus() == 2)
					{
						updateCounters = 1;
						tempSeat.setCurrentStatus(1);
						currentFlight.updateSeat(updateCounters);
						tempSeat.setaPassenger(null);
						currentFlight.CalculateTotalFlightTakings();
						
						genericOutput(tempSeat.getSeatNumber() + " Has Been Cancelled");
					}else if(tempSeat.getCurrentStatus() == 3)
					{
					
						updateCounters = 2;
						tempSeat.setCurrentStatus(1);
						currentFlight.updateSeat(updateCounters);
						tempSeat.setaPassenger(null);
						currentFlight.CalculateTotalFlightTakings();
						
						genericOutput(tempSeat.getSeatNumber() + " Has Been Cancelled - No Refund");
						
					}//end of nested if
					
				}else if (choice == 2 || choice == 3)
				{
					getPassengerDetails(flightNo, choice, seatNo);
				}else if(choice == 4)
				{
					Seat tempSeat = myAirline.getSeat(flightNo, seatNo);
					
					genericOutput(tempSeat.DisplaySeatDetails());
				}
				
				myFrame.dispose();
				

			}
				
			}
		});
		
		
		btnBack.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				mainMenu();

			}
		});
		
		
	}
	
	//screen which gets passengers details
	public void getPassengerDetails(String flightNumber, int bookingChoice, String seatNo)
	
	{
		
		//create frame object and size
				JFrame myFrame = new JFrame();
				myFrame.setSize(300, 350);
				//sets frame in middle of the screen when null value is set
				myFrame.setLocationRelativeTo(null);
				
				
				
				//need at least one panel on a window, default will be full size of frame
				JPanel myPanel = new JPanel();
				
				myPanel.setLayout(new GridLayout(6,1));
				
				JLabel enterPassengerName = new JLabel("Enter Passenger Name", SwingConstants.CENTER);
				JTextField txtPassengerName = new JTextField();
				
				JButton btnLocal = new JButton("Local");
				JButton btnBusiness = new JButton("Business");
				JButton btnOrdinary = new JButton("Ordinary");
				JButton btnBack = new JButton("Back To Seat Selection");
				
				myPanel.add(enterPassengerName);
				myPanel.add(txtPassengerName);
				myPanel.add(btnLocal);
				myPanel.add(btnBusiness);
				myPanel.add(btnOrdinary);
				myPanel.add(btnBack);
				
				myFrame.add(myPanel);
				myFrame.setVisible(true);
				
			
				
				
//////////////////////////////Action Listeners//////////////////////////////////////////////
				btnBack.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						getSeatno(flightNumber, bookingChoice);

					}
				});
				
				btnBusiness.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						String passengerName = txtPassengerName.getText().toString();
						getCompany(flightNumber, bookingChoice, seatNo, passengerName);

					}
				});
				
				btnLocal.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						String passengerName = txtPassengerName.getText().toString();
						getResidence(flightNumber, bookingChoice, seatNo, passengerName);

					}
				});
				
				
				btnOrdinary.addActionListener(new ActionListener() {

					// default action performed is a click
					@Override
					public void actionPerformed(ActionEvent e) {

						myFrame.dispose();
						String passengerName = txtPassengerName.getText().toString();
						getPromo(flightNumber, bookingChoice, seatNo, passengerName);
						
					}
				});
				
				
				
		
		
	}
	
	
	//intakes island of residence and applies it to current passenger
	public void getResidence(String flightNumber, int bookingChoice, String seatNo, String passengerName)
	{

		// create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		// sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);

		// need at least one panel on a window, default will be full size of
		// frame
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(4,1));

		JLabel enterIslandOfResidence = new JLabel("Enter Island of Residence", SwingConstants.CENTER);
		JTextField txtIslandOfResidence = new JTextField();

		JButton btnSubmit = new JButton("Submit");
		JButton btnBack = new JButton("Back to Passenger Name");

		myPanel.add(enterIslandOfResidence);
		myPanel.add(txtIslandOfResidence);
		myPanel.add(btnSubmit);
		myPanel.add(btnBack);

		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		
////////////////Action Listeners/////////////////////////////////////////////////////////////////////////////
		btnBack.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				getPassengerDetails(flightNumber, bookingChoice, seatNo);

			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				
				myFrame.dispose();
				String island = txtIslandOfResidence.getText().toString();
				IslandResident newPassenger = new IslandResident(passengerName, island);
				Flight currentFlight = myAirline.getFlights(flightNumber);
				Seat passengerSeat = new Seat();
				passengerSeat = myAirline.getSeat(flightNumber, seatNo);
				int fullSeatStatus = passengerSeat.changeSeatStatus(myAirline, bookingChoice, newPassenger, currentFlight);
				currentFlight.updateSeat(fullSeatStatus);
				currentFlight.CalculateTotalFlightTakings();
				
				

			}
		});
		
		
	}
	//intakes Company if business passenger and applies it to current passenger
	public void getCompany(String flightNumber, int bookingChoice, String seatNo, String passengerName)

	{

		// create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		// sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);

		// need at least one panel on a window, default will be full size of
		// frame
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(4,1));

		JLabel enterCompanyName = new JLabel("Enter Company Name", SwingConstants.CENTER);
		JTextField txtCompanyName = new JTextField();

		JButton btnSubmit = new JButton("Submit");
		JButton btnBack = new JButton("Back to Passenger Name");

		myPanel.add(enterCompanyName);
		myPanel.add(txtCompanyName);
		myPanel.add(btnSubmit);
		myPanel.add(btnBack);

		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		
////////////////Action Listeners//////////////////////////////////////////////
		btnBack.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				getPassengerDetails(flightNumber, bookingChoice, seatNo);

			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				String company = txtCompanyName.getText().toString();
				BusinessTraveller newPassenger = new BusinessTraveller(passengerName, company);
				Flight currentFlight = myAirline.getFlights(flightNumber);
				Seat passengerSeat = new Seat();
				passengerSeat = myAirline.getSeat(flightNumber, seatNo);
				int fullSeatStatus = passengerSeat.changeSeatStatus(myAirline, bookingChoice, newPassenger, currentFlight);
				currentFlight.updateSeat(fullSeatStatus);
				currentFlight.CalculateTotalFlightTakings();
				myFrame.dispose();
				

			}
		});
	}
	
	//intakes whether or not an ordinary passenger has promo and applies it to current passenger
	public void getPromo(String flightNumber, int bookingChoice, String seatNo, String passengerName)

	{

		// create frame object and size
		JFrame myFrame = new JFrame();
		myFrame.setSize(300, 350);
		// sets frame in middle of the screen when null value is set
		myFrame.setLocationRelativeTo(null);

		// need at least one panel on a window, default will be full size of
		// frame
		JPanel myPanel = new JPanel();
		myPanel.setLayout(new GridLayout(4,1));

		JLabel isPromo = new JLabel("Is This Part Of A Promotion? (y/n)", SwingConstants.CENTER);
		JTextField txtPromo = new JTextField();

		JButton btnSubmit = new JButton("Submit");
		JButton btnBack = new JButton("Back to Passenger Name");

		myPanel.add(isPromo);
		myPanel.add(txtPromo);
		myPanel.add(btnSubmit);
		myPanel.add(btnBack);

		myFrame.add(myPanel);
		myFrame.setVisible(true);
		
		////////////////Action Listeners//////////////////////////////////////////////
		
		btnBack.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {

				myFrame.dispose();
				getPassengerDetails(flightNumber, bookingChoice, seatNo);

			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {

			// default action performed is a click
			@Override
			public void actionPerformed(ActionEvent e) {
				String promo = txtPromo.getText().toString();
				OrdinaryPassenger newPassenger = new OrdinaryPassenger(passengerName, promo.charAt(0));
				Flight currentFlight = myAirline.getFlights(flightNumber);
				Seat passengerSeat = new Seat();
				passengerSeat = myAirline.getSeat(flightNumber, seatNo);
				int fullSeatStatus = passengerSeat.changeSeatStatus(myAirline, bookingChoice, newPassenger, currentFlight);
				currentFlight.updateSeat(fullSeatStatus);
				currentFlight.CalculateTotalFlightTakings();
				myFrame.dispose();
				

			}
		});
	}
	
	
	
	
	
}//end of class
