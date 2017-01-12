package package1;

//business traveller class, subclass of passenger
public class BusinessTraveller extends Passenger{
	
	//attributes
	private String companyName;

	//getters and setters
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyNameIn) {
		companyName = companyNameIn;
	}
	
	//constructors
	public BusinessTraveller(){
		super();
	}

	public BusinessTraveller(String companyNameIn,float discountAmountIn, String passengerNameIn){
		super(discountAmountIn, passengerNameIn);
		
		
			companyName = companyNameIn ;
			discountAmountIn = 0.75f;
		
	}
	
	//setting discount amount
	public BusinessTraveller(String passengerNameIn, String companyNameIn){
		super(0.75f ,passengerNameIn);
		
		companyName = companyNameIn;
		
		
	}
	
	
}
