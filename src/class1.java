public class class1 
{

	public static double sdeviation(int[] list, double avg)
	{
	    double total = 0.0;
	    double no =0.0;
	    double no1 = 0.0;
	    double denominator = 0.0;
	    
	    //Standard deviation calculation in this code
	    for (int i=0; i <list.length; i++)
	    {
	         no1 = Math.pow((list[i] - avg),2);
	         no+=no1;  
	    }
	
	
	    double sdeviationResult = Math.sqrt(no/list.length);
	    return sdeviationResult;
	}
	
	//Mean is calculated
	public static double mean(int[] list)
	{
	    double total = 0.0;
	    double avg = 0.0;
	    
	    for (int i=0; i < list.length; i++)
	    {
	        total+=list[i];
	    }
	    
	    avg = total/list.length;
	    return avg;
	}
}
	
