package endpoints;
public enum apiendpoints {
	addSerialNumberAPI("/charge-point"),
	getSerialNumberAPI("/charge-point"),
	DeleteSerialNumberAPI("/charge-point/");

	private final String resource;
	
	apiendpoints(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	

}

