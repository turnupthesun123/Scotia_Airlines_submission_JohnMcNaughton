package package1;

//oradinary passenger class, subclass of passenger
public class OrdinaryPassenger extends Passenger {

	//attributes
	private char currentPromotion;

	//getters
	public char getCurrentPromotion() {
		return currentPromotion;
	}

	//setters
	public void setCurrentPromotion(char currentPromotionIn) {
		currentPromotion = currentPromotionIn;
	}
	
	//constructors
	public OrdinaryPassenger(){
	super();
	
	currentPromotion = ' ';
	
	}
	
	

	
	public OrdinaryPassenger(String passengerNameIn, char currentPromotionIn)
	{
		super(passengerNameIn);
		
		currentPromotion = currentPromotionIn;
	
		
		//takes in whether a promotion or not and sets discount accordingly
		if(currentPromotion == 'y')
		{
			
		this.setDiscountAmount(0.95f);
		
		}else{
			
		this.setDiscountAmount(1.0f);
		}
		
		
	}
	

	
}//end of class
