package package1;

public class Controller {

	public static void main(String[] args) 
	{
		//loads items from database on startup
		Airline myAirline = new Airline();
		myAirline.loadFlightsFromDB();
		myAirline.loadSeatsFromDB();
		
		
		
		//runs ui
		UserInterface ui = new UserInterface(myAirline);
		ui.mainMenu();
		
	

	}

}
