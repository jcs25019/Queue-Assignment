import java.util.Date;

public class CustomerInfo
{
	private String partyName;
	private String seatType;
	private int partySize;
	private Date date = new Date();
	
	public CustomerInfo(String partyName, String seatType, int partySize)
	{
		this.partyName = partyName;
		this.seatType = seatType;
		this.partySize = partySize;
	}
	public String toString()
	{
		String str = partyName + " Size of party: " + partySize + " " + seatType + " " + date.toString();
		return str;
	}
	
	public boolean equals(Object obj)
	{
	   boolean same = false;
	   if (obj != null)
	   {
		   if (obj instanceof CustomerInfo)
		   {
			   CustomerInfo otherNode = (CustomerInfo) obj;
		       same = (partyName.equals(otherNode.partyName) && (partySize == otherNode.partySize));
		   }
	   }
	   return same;
	}

}