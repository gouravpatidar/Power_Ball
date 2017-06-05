/*Homework # 6
Due Date: Wednesday, April 6, 2016
Purpose: To perform string manipulations, sorting of data in Java using Power Ball winning numbers data and performing statistical calculations. New features are added to the functionality of homework 5.
Requirements:
-Prompt the user for the input file name pb.txt
-Generate output files pbo.txt, dates.txt, numbers.txt, stats.txt and probs.txt
-In addition to the files generated in homework # 5, a new file called probs.txt is generated.
-Each row in this file contain a number and its probability.
-The number must be formatted as an integer. The probability must be formatted as a float number with two decimal places.
-In this way, the first column contains numbers, and the second column contain probabilities.
-The numbers must be sorted in ascending order.
-The numbers used are the ones contained in file numbers.txt.
-The probability using a percentage is defined as the number of times a number is repeated divided by the total number of entries in file numbers.txt, and multiplied by 100. A way to check the validity of your probabilities is that their addition must equal 100.
-When file reading and processing is completed, close the input and output files
-Then output the message "Power Ball data processing completed" must be printed
Example of a probability calculation.
Suppose you have numbers
2, 3, 4, 3, 2 and 3.
There are a total of 6 numbers.
2 is repeated twice, so its probability is (2/6)*100 = 33.33
3 is repeated three times, so its probability is (3/6)*100 = 50.00
4 is shown only one time, so its probability is (1/6)*100 = 16.67
The numbers and their probabilities are then printed as
2 33.33
3 50.00
4 16.67
To check this work we add the probabilities:
33.33 + 50.00 + 16.67 =100.00
If the addition is not 100, there is an error in the calculations.
-Example of output files
Input file sample from pb.txt. Note that this is a sample data set which can be used to test your
code, but the file that you must read and process is pb.txt which contains much more numbers.
01/23/16 -22 32- 34- 40- 69 PB 19 X4 07/25/15 -27 29- 34- 41- 44 PB 2 X3
01/20/16 -5 39- 44- 47- 69 PB 24 X5 07/22/15 -12 31- 43- 44- 57 PB 11 X2
01/16/16 -3 51- 52- 61- 64 PB 6 X2 07/18/15 -6 37- 39- 45- 55 PB 33 X3
The first output file pbo.txt will look as follows:
1 6 37 39 45 55 33
2 12 31 43 44 57 11
3 27 29 34 41 44 2
4 3 51 52 61 64 6
5 5 39 44 47 69 24
6 22 32 34 40 69 19
The second output file sample dates.txt will look as follows:
1 07 18 15
2 07 22 15
3 07 25 15
4 01 16 16
5 01 20 16
6 01 23 16
The third output file numbers.txt will look as follows:
6
37
39
45
55
33
12
31
43
44
57
11
27
29
34
41
44
2
3
51
52
61
64
6
5
39
44
47
69
24
22
32
34
40
69
19
The fourth output file stats.txt will look as follows:
MEAN = 35.3056
STANDARD DEVIATION = 18.8070
The fifth output file probs.txt will look as follows:
2
2.78
3
2.78
5
2.78
6
5.56
11
2.78
12
2.78
19
2.78
22
2.78
24
2.78
27
2.78
29
2.78
31
2.78
32
2.78
33
2.78
34
5.56
37
2.78
39
5.56
40
2.78
41
2.78
43
2.78
44
8.33
45
2.78
47
2.78
51
2.78
52
2.78
55
2.78
57
2.78
61
2.78
64
2.78
69
5.56
How it will be graded:
-Include the problem statement at the beginning (you can cut and paste this entire document)
-Provide the Java files and the output files pbo.txt, dates.txt, numbers.txt, stats.txt and probs.txt
-Provide the flowchart or pseudocode for the portion of the code developed in homework # 6 dealing with the probabilities.
-Compress all files in a zip folder or equivalent and attach to the assignment
-The Java code must produce correct results
-The specifications in this document must be followed
-The assignment must be submitted on time for full credit
-Code style: Include comments in your code, use spaces to improve code readability

*/
//Before executing the code, extract and drag the files(pb.txt, pbo.txt, dates.txt, number.txt, stats.txt, probs.txt) from the Folder 
//HW6Q1 to the Eclipse Package Explorer HW6Q1
//Flowchart is attached separately in a PDF File, FlowChart made from www.code2flow.com
//To run this application on Eclipse, click on RUN menu on the status bar



import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PowerBall4 
//Add a class called PowerBall4
{
	
	//Main Function
	public static void main(String[] args) throws IOException
	{
		//Opens input file pb.txt
		openInputFile(); 
		
		//Opens first output file pbo.txt
		openFirstOutputFile();
		
		//Opens second output file dates.txt
		openSecondOutputFile();
		
		//Opens third output file numbers.txt
		openThirdOutputFile();
		
		//Opens fourth output file stats.txt
		openFourthOutputFile();
		
		//Opens fifth output file probs.txt
		openFifthOutputFile();
		
		File fileinput = new File("pb.txt");
 		Scanner scan = new Scanner(fileinput);
 		
 		//Initialize starting begin to 1
 		int begin = 1;
		String string1 = null;
		int n1;
        int n2;
        int n3;
        int n4;
        int n5;
        int pb;
        
        //Initialize integers a, b
        int a = 0;
		int b = 0;
		int NumberCount = 0;
	
		String numbers = null;
		int num = 0;
		int pointer = 0;
		int sort = 0;
		
		//Initialize integer arrays
		int[] SortArray = new int[1000];
		int[][] PointerArray = new int[1][3];
		int[][] Final = new int[1000][10];
		int[][] DateIndex = new int[1000][3];
		int[] ListNum = new int[4368];
		float[] Prob = new float[69];
		
		//Scanning the line of data
		while(scan.hasNext())
		{
			string1 = scan.nextLine();	
			//Create pattern object 
			Pattern numData = Pattern.compile("[0-1][0-9]/[0-3][0-9]/[0-9][0-9]\\s*-[0-9]*\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*-\\s*[0-9]*\\s*[P][B]\\s*[0-9]*");
			
			//Create matcher object
			Matcher match = numData.matcher(string1);
			
			//This loop searches the data
			while(match.find())
			{
				b = 0;
				String string2 = match.group();
				//This will scan only one or more numbers from 0-9
				numData = Pattern.compile("[0-9]+");
				Matcher match1 = numData.matcher(string2);					
				
					while(match1.find())
					{
						numbers = match1.group().trim();
						num = Integer.parseInt(numbers);
						if(b < 3)
						{
							DateIndex[a][b] = num;
						}
						Final[a][b] = num;
						b++;
					}
					begin++;
					a++;	
			}
		}
		
		//Scans and compare two dates, accordingly arrange the dates in ascending order. PointerArray points to a date and compares to 
		//next sequential date. Similarly it repeats till the end
		for(int i = 0; i < a; i++)
		{
			pointer = i;
			
			for(int j = 0; j < 3; j++)
			{
				PointerArray[0][j] = DateIndex[pointer][j];
			}
			
			for(int j = 0; j <= a; j++)
			{
				if(PointerArray[0][2] > DateIndex[j][2])
				{
					pointer = j;
					
					PointerArray[0][0] = DateIndex[pointer][0];
					PointerArray[0][1] = DateIndex[pointer][1];
					PointerArray[0][2] = DateIndex[pointer][2];
				}
				
				else if(PointerArray[0][0] > DateIndex[j][0] && PointerArray[0][2] == DateIndex[j][2])
				{
					pointer = j;
					
					PointerArray[0][0] = DateIndex[pointer][0];
					PointerArray[0][1] = DateIndex[pointer][1];
					PointerArray[0][2] = DateIndex[pointer][2];
				}
				
				else if(PointerArray[0][1] > DateIndex[j][1] && PointerArray[0][0] == DateIndex[j][0] && PointerArray[0][2] == DateIndex[j][2])
				{
					pointer = j;
					
					PointerArray[0][0] = DateIndex[pointer][0];
					PointerArray[0][1] = DateIndex[pointer][1];
					PointerArray[0][2] = DateIndex[pointer][2];
				}
				
				
			}
			
			DateIndex[pointer][0] = 100;
			DateIndex[pointer][1] = 100;
			DateIndex[pointer][2] = 100;
			
			System.out.println(i+" "+pointer);
			SortArray[i] = pointer;
		}
		
		//Prints the data into first output file pbo.txt
		PrintWriter writer = new PrintWriter("pbo.txt", "UTF-8");
		for(int i = 1; i <= a; i++)
		{
			sort = SortArray[i];
			writer.print(i+" ");
			for(int j = 3; j < 9; j++)
			writer.print(Final[sort][j]+ " ");
			writer.println();
		}
		//Closes the first output file
		writer.close();
		
		//Prints the data into second output file dates.txt
		PrintWriter writer2 = new PrintWriter("dates.txt", "UTF-8");
		for(int i = 1; i <= a; i++)
		{
			sort = SortArray[i];
			writer2.print(i+" ");
			for(int j = 0; j < 3; j++)
			writer2.print(Final[sort][j]+ " ");
			writer2.println();
		}
		//Closes the second output file
		writer2.close();
		
		
		//Prints the data into third output file numbers.txt
		PrintWriter writer3 = new PrintWriter("numbers.txt", "UTF-8");  
		for(int i = 1; i <= a; i++)
		{
			sort = SortArray[i];
			for(int j = 3; j < 9; j++)
			{
				ListNum[NumberCount] = Final[sort][j];
				NumberCount++;
				writer3.println(Final[sort][j]);
				writer3.println();					
			}
		}
		//Closes the third output file		
		writer3.close();  
		
		//This is used to call mean function
		double mean= class1.mean(ListNum);   
		//This is used to call standard deviation function
		double sdeviation = class1.sdeviation(ListNum,mean);   
		//Prints the data into fourth output file stats.txt
		PrintWriter writer4 = new PrintWriter("stats.txt", "UTF-8");   
						
		//Printing Average and Standard Deviation in Fourth File
		writer4.println("Mean: "+mean);
		writer4.println("Standard Deviation: "+sdeviation);
		
		//Closes the fourth output file
		writer4.close();
				
		
		//Prints the probability data into fifth output file probs.txt
		PrintWriter writer5 = new PrintWriter("probs.txt", "UTF-8");
		for(int i = 0; i < Prob.length; i++)
			{
				Prob[i] = 0;
			}
			//To calculate instances of numbers
			for(int i = 0; i < ListNum.length; i++)
			{
				for(int j = 0; j < Prob.length; j++)
				{
					if(ListNum[i] == j+1)
					{
						Prob[j]++;
					}
				}
			}
			for(int j = 0; j < Prob.length; j++)
			{
				writer5.println(j+1+" "+(Prob[j]/4368)*100);
			}
			//Closes the fifth output file
			writer5.close();
			
		System.out.println("\nPowerBall data processing has been completed successfully. Open the first output file pbo.txt to see the output data of numbers and Open the second output file dates.txt to see the dates in ascending order.");
	   		
	}
	
	   public static void openInputFile()
	   {
	      try
	      {
	    	  //Prompts user to Enter the input file name
	    	  Scanner s1 = new Scanner(System.in);
	    	  System.out.print("Enter the Input File name:\n "); //Example: pb.txt
	    	  String file1 = s1.nextLine();
	    	  File ipFile = new File(file1);
	    	  Scanner reader = new Scanner(ipFile);
	      } 
	      catch (IOException ioException)
	      {
	    	  //Gives exception when wrong input file name is provided
	    	  System.err.println("Error opening file. Terminating.");
	    	  System.exit(1);
	      } 
	   }

	  public static void openFirstOutputFile()
	   {
		   try
		   {
			  //Prompts user to Enter the first output file name
			  Scanner s2 = new Scanner(System.in);
			  System.out.print("\nEnter the first output File name:\n "); //Example: pbo.txt
			  String file2 = s2.nextLine();
			  File opFile1 = new File(file2);      
			  Scanner reader = new Scanner(opFile1); 
		   } 
	      catch (IOException ioException)
		  {
	    	  //Gives exception when wrong output file name is provided
		      System.err.println("Error opening file. Terminating.");
		      System.exit(1);
		  } 

	}

	   
	   public static void openSecondOutputFile()
	   {
		   try
		   {
			  //Prompts user to Enter the second output file name
			  Scanner s3 = new Scanner(System.in);
			  System.out.print("\nEnter the second output File name:\n "); //Example: dates.txt
			  String file3 = s3.nextLine();
			  File opFile2 = new File(file3);      
			  Scanner reader = new Scanner(opFile2); 
		   } 
	      catch (IOException ioException)
		  {
	    	  //Gives exception when wrong output file name is provided
		      System.err.println("Error opening file. Terminating.");
		      System.exit(1);
		  } 

	} 

	   public static void openThirdOutputFile()
	   {
		   try
		   {
			  //Prompts user to Enter the third output file name
			  Scanner s4 = new Scanner(System.in);
			  System.out.print("\nEnter the third output File name:\n "); //Example: numbers.txt
			  String file4 = s4.nextLine();
			  File opFile3 = new File(file4);      
			  Scanner reader = new Scanner(opFile3); 
		   } 
	      catch (IOException ioException)
		  {
	    	  //Gives exception when wrong output file name is provided
		      System.err.println("Error opening file. Terminating.");
		      System.exit(1);
		  } 

	}
	   
	   public static void openFourthOutputFile()
	   {
		   try
		   {
			  //Prompts user to Enter the fourth output file name
			  Scanner s5 = new Scanner(System.in);
			  System.out.print("\nEnter the fourth output File name:\n "); //Example: stats.txt
			  String file5 = s5.nextLine();
			  File opFile4 = new File(file5);      
			  Scanner reader = new Scanner(opFile4); 
		   } 
	      catch (IOException ioException)
		  {
	    	  //Gives exception when wrong output file name is provided
		      System.err.println("Error opening file. Terminating.");
		      System.exit(1);
		  } 

		   
	}
	  
	   public static void openFifthOutputFile()
	   {
		   try
		   {	
			   //Prompts user to Enter the fifth output file name
			   Scanner s6 = new Scanner(System.in);
			   System.out.print("\nEnter the fifth output File name:\n"); //Example: probs.txt
			   String file6 = s6.nextLine();
			   File opFile5 = new File(file6);
			   Scanner reader = new Scanner(opFile5);
		   }
		   catch (IOException ioException)
		   {
			   //Gives exception when wrong output file name is provided
			   System.err.println("Error opening file. Terminating.");
			   System.exit(1);
		   }
	   }
	      
}

//Referred from http://www.tutorialspoint.com/java/java_regular_expressions.htm
//http://stackoverflow.com/questions/18995177/how-to-prompt-a-user-for-a-file-in-java-using-scanner
//http://www.cs.utexas.edu/~mitra/csSummer2009/cs303/lectures/fileIO.html
//http://www.codejava.net/java-core/collections/sorting-arrays-examples-with-comparable-and-comparator
//http://faculty.washington.edu/stepp/courses/2005winter/tcss342/lectures/files/2005-02-03/Sorting.java