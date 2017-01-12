package package1;


//passenger superclass
public class Passenger {

	
	//attributes
	private float discountAmount;
	private String passengerName;
	
	
	//getters
	public float getDiscountAmount() {
		return discountAmount;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	
	//setters
	public void setDiscountAmount(float discountAmountIn) {
		discountAmount = discountAmountIn;
	}
	
	public void setPassengerName(String passengerNameIn) {
		passengerName = passengerNameIn;
	}

	//constructors
	public Passenger(){
		
		discountAmount = 0;
		passengerName = "";
		
	}
	
	public Passenger(String passengerNameIn)
	{
		discountAmount = 0f;
		passengerName = passengerNameIn;
	}
	
	public Passenger(float discountAmountIn, String passengerNameIn){
		
		discountAmount = discountAmountIn;
		passengerName = passengerNameIn;
		
	}
	
}//end of class
