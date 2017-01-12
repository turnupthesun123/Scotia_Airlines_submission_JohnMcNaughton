package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class Airline {

	//attributes
	private HashMap<String,Flight> flights;

	//getter for hashmap of flights
	public HashMap<String,Flight> getFlights() { return flights; }
	
	//constructor
	public Airline()
	{
		flights =  new HashMap<String,Flight>();
		
		
	}
	
//getter for specific flight
	public Flight getFlights(String flightNo){
		
		if (flights.containsKey(flightNo))
		{
			return flights.get(flightNo);
		}
		else
		{
			Flight aNewFlight = new Flight(0,0);
			flights.put(aNewFlight.getFlightNumber(), aNewFlight);
			return aNewFlight;
		}
		
	}
	
	//adds flight to hashmap
	public void addFlight(Flight aFlight)
	{
		flights.put(aFlight.getFlightNumber(), aFlight);
	}
	
	
	//loads flights from database to hashmap
	public void loadFlightsFromDB()
	{
		try
		{
			Connection conn = 
					DriverManager.getConnection("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Flight");
			
			while(rs.next())
			{
				String departure = rs.getString(1);
				String arrival = rs.getString(2);
				int rows = rs.getInt(3);
				int columns = rs.getInt(4);
				String flightNo = rs.getString(5);
				
				
				Flight aNewFlight = new Flight(flightNo,departure,arrival,rows, columns);
				addFlight(aNewFlight);
			}
		}
		catch(Exception ex)
		{
			
		}
	}
	
	//loads seats from database to hashmap
	public void loadSeatsFromDB()
	{
		try
		{
			Connection conn = 
					DriverManager.getConnection("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SEAT");
			
			while(rs.next())
			{
				String seatNo = rs.getString(1);
				int status = rs.getInt(2);
				float takings = rs.getFloat(3);
				String flightNo = rs.getString(4);
				
				
				
				Seat passengerSeat = getSeat(flightNo, seatNo);
				passengerSeat.setSeatTakings(takings);
				
				loadPassengersFromDB(flightNo, passengerSeat, status, takings);
			}
		}
		catch(Exception ex)
		{
			
		}
	}
	
	//loads passengers from database to hashmap
	public void loadPassengersFromDB(String flightNo, Seat passengerSeat, int status, float takings)
	{
		try
		{
			Connection conn = 
					DriverManager.getConnection("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Passenger");
			
			while(rs.next())
			{
				String passengerName = rs.getString(3);
				String seatNo = rs.getString(4);
				char passengerType = rs.getString(1).charAt(0);
				String passengerInfo = rs.getString(2);
				String flightNumber = rs.getString(5);
				
				if(seatNo.equalsIgnoreCase(passengerSeat.getSeatNumber()) && flightNumber.equalsIgnoreCase(flightNo))
				{
					
					Flight passengersFlight = flights.get(flightNumber);
					int choice = passengerSeat.changeSeatStatus(status, takings, passengerName, passengerType, passengerInfo);
					passengersFlight.updateSeat(choice);
					passengersFlight.CalculateTotalFlightTakings();
					
				}
				
				
			}
		}
		catch(Exception ex)
		{
			
		}
	}
	
	//empties seat table in db
	//used to clear db before writing new values out
	public void EmptyDB()
	{
		UserInterface ui = new UserInterface(this);
		
		try
		{
			Connection conn = 
					DriverManager.getConnection
					("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			String sql = "DELETE * FROM Seat";
			int rs = stmt.executeUpdate(sql); //returns number of rows from update statment
			
			if(rs > 0)
			{
				System.out.println("Seat Tables Emptied");
				
			}
		}
		catch(Exception e){
			
			
			
			ui.genericOutput("Seat table cannot be emptied");
		}
		
		
		try
		{
			Connection conn = 
					DriverManager.getConnection
					("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			Statement stmt = conn.createStatement();
			String sql = "DELETE * FROM Passenger";
			int rs = stmt.executeUpdate(sql);//returns number of rows from update statment
			
			if(rs > 0)
			{
				System.out.println("Passengers Emptied");
				
			}
		}
		catch(Exception e){
			
			
			
			ui.genericOutput("Passenger table cannot be emptied");
		}
	}
	
	//saves updated seat info to db
	public void SaveSeatsToDB()
	{
		UserInterface ui = new UserInterface(this);
		
		try
		{
			Connection conn = 
					DriverManager.getConnection
					("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			//for each flight
			for(Map.Entry<String, Flight>currentFlight: flights.entrySet())
			{
				//for each seat in current flight
				for(Map.Entry<String, Seat> currentSeat: currentFlight.getValue().getSeats().entrySet())
				{
					//get values for current seat
					String seatNo = currentSeat.getValue().getSeatNumber();
					String seatTakings = String.valueOf(currentSeat.getValue().getSeatTakings());
					String seatStatus = String.valueOf(currentSeat.getValue().getCurrentStatus());
					String flightNo = currentFlight.getValue().getFlightNumber();
					
					
					Statement stmt = conn.createStatement();
					int rs = stmt.executeUpdate("INSERT INTO Seat (SeatNo, Status, Takings, FlightID) VALUES('" +  seatNo + "','" + seatStatus + "','"	+ seatTakings + "','" + flightNo + "')");
					
					
					if(rs > 0)
					{
						System.out.println("Seats Saved");
						
					}
				}
			}
		}
		catch(Exception e)
		{
			ui.genericOutput("Seats failed to save to DB");
		}
	}
	
	//saves updated passenger info to db
	public void SavePassengersToDB()
	{
		UserInterface ui = new UserInterface(this);
		
		try
		{
			char type = ' ';
			String info = "";
			
			Connection conn = 
					DriverManager.getConnection
					("jdbc:ucanaccess://F:\\College\\Second Year\\Object Orientated Programming\\Scotia Airlines Booking System\\Airline.accdb");
			
			//for each flight
			for(Map.Entry<String, Flight>currentFlight: flights.entrySet())
			{
				//for each seat in the current flight from previous loop
				for(Map.Entry<String, Seat> currentSeat: currentFlight.getValue().getSeats().entrySet())
				{
					//if statement to get passenger details
					if(currentSeat.getValue().getaPassenger() != null)
					{
						if(currentSeat.getValue().getaPassenger().getClass().isInstance(new BusinessTraveller()))
						{
							type = 'B';
							
							BusinessTraveller bp = (BusinessTraveller)currentSeat.getValue().getaPassenger();
							
							info = bp.getCompanyName();
							
						}else if(currentSeat.getValue().getaPassenger().getClass().isInstance(new IslandResident()))

						{
							type = 'I';
							
							IslandResident ip = (IslandResident)currentSeat.getValue().getaPassenger();
							
							info = ip.getIslandOfResidence();
							
						}else if(currentSeat.getValue().getaPassenger().getClass().isInstance(new OrdinaryPassenger()))
						{

							type = 'O';
							
							OrdinaryPassenger op = (OrdinaryPassenger)currentSeat.getValue().getaPassenger();
							
							info = String.valueOf(op.getCurrentPromotion());
									
						}
					
					
					Statement stmt = conn.createStatement();
					
					String passengerType = String.valueOf(type);
					String passengerInfo = info;
					String flightNo = currentFlight.getValue().getFlightNumber();
					
					
			int rs = 
					stmt.executeUpdate
					("INSERT INTO Passenger(SeatNumber, PassengerName, Type, Information, FlightID) VALUES('" +  currentSeat.getValue().getSeatNumber() + "','" + currentSeat.getValue().getaPassenger().getPassengerName() + "','"	+ passengerType + "','"	+ passengerInfo + "','" + flightNo + "')");

		
			for(int i = 0; i < rs; i++)
			{
				System.out.println("Passengers Saved");
				
			}
		}
				}
				
				
			}
		}
		catch(Exception e)
		{
			ui.genericOutput("Seats failed to save to DB");
		}
	}
	
	//gets seat
	public Seat getSeat(String FlightNo, String SeatNo)
	{
		if(flights.containsKey(FlightNo))
		{
			//if flight exists input flightNo
			Flight currentFlight = flights.get(FlightNo);
			
			//if seat exists within flight return seat found
			if(currentFlight.getSeats().containsKey(SeatNo))
			{
				Seat foundSeat = currentFlight.getSeats().get(SeatNo);
				
				return foundSeat;
			}
			else
			{
				Seat tempSeat = new Seat(SeatNo);
				
				currentFlight.getSeats().put(SeatNo, tempSeat);
				
				return tempSeat;
				
			}
		
			
		}
		else
		{
			return null;
		}
		
		
		
	}
}//end of class
	

