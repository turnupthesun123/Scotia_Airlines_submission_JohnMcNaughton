package package1;

//island resident class, subclass of passenger
public class IslandResident extends Passenger{
	
	//attributes
	private String islandOfResidence;

	//getters
	public String getIslandOfResidence() {
		return islandOfResidence;
	}

	//setters
	public void setIslandOfResidence(String islandOfResidenceIn) {
		islandOfResidence = islandOfResidenceIn;
	}

	//constructors
	public IslandResident(){
		super();
		
		islandOfResidence = "";
		
		}
	
	public IslandResident(String passengerNameIn, String islandOfResidenceIn){
		super(0.9f,passengerNameIn);
		
		islandOfResidence = islandOfResidenceIn;
		
		
	}
		
		
		public IslandResident(String islandOfResidenceIn, float discountAmountIn, String passengerNameIn){
			super(discountAmountIn, passengerNameIn);
			
			islandOfResidence = islandOfResidenceIn;
			
			discountAmountIn = 0.9f;
			
			
			
		}
	
	
	
}
