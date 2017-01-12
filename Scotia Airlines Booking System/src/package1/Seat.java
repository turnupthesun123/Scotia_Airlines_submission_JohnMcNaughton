package package1;




public class Seat {
	
	//attributes
	private String seatNumber;
	private float seatPrice;
	private float seatTakings;
	private int currentStatus;
	private int completeSeatStatus;
	private Passenger aPassenger;
	
	//getters/setters
	
	public void setCompleteSeatStatus(int completeSeatStatusIn)
	{
		completeSeatStatus =  completeSeatStatusIn;
	}
	
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumberIn) {
		seatNumber = seatNumberIn;
	}
	public float getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(float seatPriceIn) {
		seatPrice = seatPriceIn;
	}
	public float getSeatTakings() {
		return seatTakings;
	}
	public void setSeatTakings(float seatTakingsIn) {
		seatTakings = seatTakingsIn;
	}
	public int getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(int currentStatusIn) {
		currentStatus = currentStatusIn;
	}
	public Passenger getaPassenger() {
		return aPassenger;
	}
	public void setaPassenger(Passenger aPassengerIn) {
		aPassenger = aPassengerIn;
	}

	//default constructor
	public Seat()
	{
		seatNumber = "";
		seatPrice = 100;
		seatTakings = 0f;
		currentStatus = 1;
		aPassenger = null;
	}

	//constructor
	//2 params
	public Seat(int column, char row)
	{
		seatNumber = String.valueOf(column) + row;
		seatPrice = 100;
		seatTakings = 0f;
		currentStatus = 1;
		aPassenger = null;
		
	}
	
	//constructor
	//1 params
	public Seat(String seatNo)
	{
		seatNumber = seatNo;
		seatPrice = 100;
		seatTakings = 0f;
		currentStatus = 1;
		aPassenger = null;
	}
	
	
	//method which displays details for specific seat
	public String DisplaySeatDetails()
	{
		String output = "";
		String seatStatus = "";
		
		switch(currentStatus)
		{
		case 1:
			seatStatus = "Free";
			break;
			
		case 2:
		
			seatStatus = "Reserved";
			break;
		
		case 3:
			
			seatStatus = "Booked";
			
		}
		
		output = "<html> Seat No: " + seatNumber + "<br /> Current Status: "
				+ seatStatus + "<br /> Seat Price: £" + seatPrice
				+ "<br /> Seat Takings: £" + seatTakings;
				
				if(aPassenger != null)
				{
					output = output + "<br /> Passenger Name: " + aPassenger.getPassengerName();
							
				}
				
				output = output+"</html>";
				
				return output;
				
				
	}
	
	//method which changes status of particular seat based on passenger type
	public int changeSeatStatus(int seatStatus, float currentTakings, String passengerName, char passengerType, String passengerInfo)
	{
		completeSeatStatus = -1;
		seatTakings = currentTakings;
		
		if(passengerType == 'I')
		{
			IslandResident newPassenger = new IslandResident(passengerName,passengerInfo);
			aPassenger = newPassenger;
			if(seatStatus == 2)
			{
				currentStatus = 2;
				completeSeatStatus = 3;
			}else if(seatStatus == 3)
			{
				currentStatus = 3;
				completeSeatStatus = 4;
			}
		}else if(passengerType == 'B')
		{
		
			BusinessTraveller newPassenger = new BusinessTraveller(passengerName,passengerInfo);
			aPassenger = newPassenger;
			if(seatStatus == 2)
			{
				currentStatus =2;
				completeSeatStatus = 3;
			}else if(seatStatus == 3)
			{
				currentStatus =3;
				completeSeatStatus = 4;
			}
			
		}else if(passengerType == 'O')
		{
			OrdinaryPassenger newPassenger = new OrdinaryPassenger(passengerName,passengerInfo.charAt(0));
			aPassenger = newPassenger;
			if(seatStatus == 2)
			{
				currentStatus =2;
				completeSeatStatus = 3;
			}else if(seatStatus == 3)
			{
				currentStatus =3;
				completeSeatStatus = 4;
		}
		
		
		
		}
		
		return completeSeatStatus;
	}
	
	//method which changes status of particular seat depending on booking choice made by passenger
	public int changeSeatStatus(Airline myAirline, int newStatus, Passenger newPassenger, Flight newFlight)
	{
		UserInterface ui = new UserInterface(myAirline);
		
		//cancel seat
		if(newStatus == 1)
		{
			if(currentStatus == 1)
			{
				ui.genericOutput("Seat Number: " +seatNumber + " Is Already Free");
				return -1;
			}else if(currentStatus == 2)
			{
				currentStatus = 1;
				aPassenger = null;
				completeSeatStatus = 1;
				ui.genericOutput("Seat Number: " +seatNumber + " Has Been Cancelled");
			}else if(currentStatus == 3)
			{
				currentStatus = 1;
				aPassenger = null;
				completeSeatStatus = 2;
				ui.genericOutput("Seat Number: " +seatNumber + " Has Been Cancelled - No Refund");
			}
		//reserve seat
		}else if (newStatus == 2)
		{
			if(currentStatus == 1)
			{
				currentStatus = 2;
				aPassenger = newPassenger;
				completeSeatStatus = 3;
				ui.genericOutput("Seat Number: " + seatNumber + " Has Now Been Reserved By " + newPassenger.getPassengerName());
				
			}else if(currentStatus == 2)
			{
				ui.genericOutput("Seat Number: " +seatNumber + " Is Already Reserved By " + aPassenger.getPassengerName());
				return -1;
				
			}else if(currentStatus == 3)
			{
				ui.genericOutput("Seat Number: " +seatNumber + " Is Already Booked By " + aPassenger.getPassengerName());
				return -1;
			}
			
			//book seat
		}else if(newStatus == 3)
		{
			if(currentStatus == 1)
			{
				currentStatus = 3;
				aPassenger = newPassenger;
				completeSeatStatus = 4;
				seatTakings+=(seatPrice*newPassenger.getDiscountAmount());
				ui.genericOutput("Seat Number: " +seatNumber + " Has Now Been Booked By " + newPassenger.getPassengerName());
				
			}else if(currentStatus == 2)
			{
				if(newPassenger.getPassengerName().equalsIgnoreCase(aPassenger.getPassengerName()))
				{
					currentStatus = 3;
					aPassenger = newPassenger;
					completeSeatStatus = 5;
					seatTakings+=(seatPrice*newPassenger.getDiscountAmount());
					ui.genericOutput("Seat Number: " + seatNumber + " Has Now Been Booked By " + newPassenger.getPassengerName());
				}else{
					ui.genericOutput("Seat Number: " + seatNumber + " Is Already Reserved By " + aPassenger.getPassengerName());
					return -1;
				}
				
			}else if(currentStatus == 3)
			{
					ui.genericOutput("Seat Number: " +seatNumber + " Is Already Booked By " + aPassenger.getPassengerName());
					return -1;
			}
			}
		

		
		return completeSeatStatus;
	
	}//end of method
	
	
}//end of class
