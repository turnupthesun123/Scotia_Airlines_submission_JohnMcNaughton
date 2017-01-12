package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Flight {
	
	//attributes
	private String flightNumber;
	private String departure;
	private String arrival;
	private Date date;
	private int freeSeats;
	private int bookedSeats;
	private int reservedSeats;
	private int columns;
	private int rows;
	private boolean isFull;
	private boolean checkingIn;
	private boolean closed;
	private boolean boarding;
	private String statusMessage;
	private float totalFlightTakings;
	private HashMap<String,Seat> seats;
	
	//getters
	public String getDeparture() {
		return departure;
	}
	public String getArrival() {
		return arrival;
	}
	public Date getDate() {
		return date;
	}
	public int getFreeSeats() {
		return freeSeats;
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public int getReservedSeats() {
		return reservedSeats;
	}
	public boolean isFull() {
		return isFull;
	}
	public boolean isCheckingIn() {
		return checkingIn;
	}
	public boolean isClosed() {
		return closed;
	}
	public boolean isBoarding() {
		return boarding;
	}
	
	public HashMap<String, Seat> getSeats() {
		return seats;
	}
	
	public String getFlightNumber(){
		return flightNumber;
	}
	
	public Seat getSeatNo(String seatNumber)
	{
	
		if (seats.containsKey(seatNumber))
		{
			return seats.get(seatNumber);
		}
		else
		{
			Seat aNewSeat = new Seat();
			seats.put(aNewSeat.getSeatNumber(), aNewSeat);
			return aNewSeat;
		}
		
		
	}
	
	public float getTotalFlightTakings() {
		return totalFlightTakings;
	}
	
	//setters
	public void setSeats(HashMap<String, Seat> seats) {
		this.seats = seats;
	}

	
	public void setFlightDetails(String flightNumberIn, String departureIn, String arrivalIn)
	{
		
		flightNumber = flightNumberIn;
		departure = departureIn;
		arrival = arrivalIn;
		
	}
	
	
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
	public void setBoarding(boolean boardingIn) {
		boarding = boardingIn;
	}
	
		
	public void setClosed(boolean closedIn) {
		closed = closedIn;
	}
	
	public void setCheckingIn(boolean isCheckingIn) {
		checkingIn = isCheckingIn;
	}
	
	
	//constructor
	//2 params
	public Flight(int columnsIn, int rowsIn)
	{
		flightNumber = "";
		departure = "";
		arrival = "";
		date = new Date();
		freeSeats = columnsIn * rowsIn;
		columns = columnsIn;
		rows = rowsIn;
		isFull = false;
		checkingIn = false;
		closed = false;
		boarding = false;
		statusMessage = "Seats Available";
		seats =  new HashMap<String,Seat>();
		totalFlightTakings = 0.0f;
		
	}
	
	//constructor
	//overloaded
	public Flight (String flightNoIn, String departureIn, String arrivalIn, int rowsIn, int columnsIn)
	{
		
		flightNumber = flightNoIn;
		departure = departureIn;
		arrival = arrivalIn;
		date = new Date();
		freeSeats = columnsIn * rowsIn;
		columns = columnsIn;
		rows = rowsIn;
		isFull = false;
		checkingIn = false;
		closed = false;
		boarding = false;
		statusMessage = "Seats Available";
		seats =  new HashMap<String,Seat>();
		totalFlightTakings = 0.0f;
	
	}
	
	//gets seat from hashmap
	public Seat getSeats(String seatNo)
	{
		if(seats.containsKey(seatNo))
		{
			return seats.get(seatNo);
			
			
		}else{
			
			Seat newSeat = new Seat();
			seats.put(newSeat.getSeatNumber(), newSeat);
			return newSeat;
			
		}
			
	}
	
	//change flight status
	public void setFlightStatus(int statusCode)
	{
		switch(statusCode)
		{
		case 1:
			checkingIn = true;
			statusMessage = "Checking In";
			break;
			
		case 2:
			boarding = true;
			statusMessage = "Boarding";
			break;
			
		case 3:
			
			closed = true;
			statusMessage = "Flight Closed";
			
		case 4:
			
			isFull = true;
			statusMessage = "Full";
			break;
			
		default:
			statusMessage = "Seats Available";
			break;
		
		}
		
	}//end of set flight status
	
	public String getStatusMessage()
	{
		return statusMessage;
	}
	
	//updates seat status based on booking choice
	public void updateSeat(int bookingChoice)
	{
		switch(bookingChoice)
		{
		
		// cancel a reserved seat
		case 1:
			freeSeats += 1;
			reservedSeats -=1;
			if(boarding == false && closed == false)
			{
				statusMessage = "Seats Available";
				isFull = false;
			}
			
			break;
		//cancel a booked seat
		case 2:
			freeSeats += 1;
			bookedSeats = (bookedSeats - 1);
			if(boarding == false && closed == false)
			{
				statusMessage = "Seats Available";
				isFull = false;
			}
			
			break;
		//reserve a seat	
		case 3:
			reservedSeats += 1;
			freeSeats -=1;
			break;
		//book a seat	
		case 4:
			bookedSeats += 1;
			freeSeats -=1;
			break;
		//book a reserved seat	
		case 5:
			bookedSeats += 1;
			reservedSeats -=1;
			break;
		//default	
		case -1:
			break;
			
		}//end switch
		
		if(freeSeats == 0)
		{
			isFull = true;
			statusMessage = "Flight full";
		}
	}
	
	//method to add new flight to database
	public void addFlightToDB()
	
	{
		try
		{
			Connection conn = 
					DriverManager.getConnection("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Flight(FlightID, Departure, Arrival, Rows, Columns) VALUES('" +  flightNumber + "','" + departure + "','"	+ arrival + "','" + rows + "','" + columns + "')");
			
			

		}catch(Exception ex)
		{
			
		}
	}
	
	public float CalculateTotalFlightTakings()
	{
		totalFlightTakings = 0.0f;
		
		//goes through all map entries in courses hash map in the courses map
		//.entrySet lets complier know courses is iterative
		for(Map.Entry<String, Seat> tempSeat : seats.entrySet())
		{
			
			
			//current course is just a reference to location of particular course
			//in map. Need to use getValue() to access contents
			totalFlightTakings+=tempSeat.getValue().getSeatTakings();
			
			
		}
		
		
		return totalFlightTakings;
	}
	
	//method to format flight info for display
	public String DisplayFlightInfo()
	{
		String output = "";
			
		output = "<html> Flight No: " + flightNumber + "<br /> Arrival Airport: "
				+ arrival + "<br /> Departure Airport: " + departure
				+ "<br /> Number Of Free Seats: " + freeSeats + " <br /> Number Of Reserved Seats: " + reservedSeats + "<br /> Number Of Booked Seats: " + bookedSeats+
				"<br /> Status Message: " + statusMessage + "<br /> Total Flight Takings: " + totalFlightTakings;
				
				
				output = output+"</html>";
				
				return output;
	}
	
	//method to check seat numbers are in valid format
	public boolean IsValidSeatNumber(String seatNo)
	{
		String number = "";
		String letter = "";
		int checkIfNum;
		int element = -1;
		boolean shouldLeaveLoop = false;
		
		
		for(char c: seatNo.toCharArray())
		{
			if(shouldLeaveLoop == false)
			{
				try
				{
					//checks if first value is numeric, if not set should leave loop to true
					String character = "";
					character = String.valueOf(c);
					checkIfNum = Integer.parseInt(character);
					number = number + c;
					element ++;
					
					
				}catch(Exception e)
				{
					
					shouldLeaveLoop = true;
					
				}
			}
		}
		
		//checking second character is a letter
		boolean lastPartIsCharacter = true;
		letter = seatNo.substring(element + 1); 
		
		if(letter.length() == 1)
		{
			char letterChar = letter.charAt(0);
			
			if(!Character.isLetter(letterChar))
			{
				lastPartIsCharacter = false;
			}
		}else
		{
			lastPartIsCharacter = false;
		}
		
		//making sure seat number falls within total number of seats on flight
		try
		{
			if(Integer.parseInt(number) > columns || number.equalsIgnoreCase("") || letter.length() != 1
					|| lastPartIsCharacter == false)
			{
				return false;
			}else
			{
				return true;
			}
		}catch(Exception e)
		{
			return false;
		}
		
		
	}//end of isValid
	
	
	
	
	
}
