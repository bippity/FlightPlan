
import java.io.File;
import java.util.Scanner;

public class FlightPlan 
{
	public static void main(String[] args) 
	{
		if (args.length != 3)
		{
			System.out.println("Correct Syntax: ./flightPlan <FlightDataFile> <PathsToCalculateFile> <OutputFile>");
			return;
		}
		try 
		{
			File inputFile = new File(args[0]);
			File pathsFile = new File(args[1]);
			File outputFile = new File(args[2]);
			
			Manager mgr = new Manager();
			
			Scanner scan = new Scanner(inputFile); //read in each line of flight paths
			int count = scan.nextInt();
			scan.nextLine();
			for (int i = 0; i < count; i++)
			{
				String s = scan.nextLine();
				if (!mgr.addPath(s))
				{
					scan.close();
					throw new Exception("[Error] Something is wrong with the FlightDataFile.");
				}
			}
			scan.close();
			
			mgr.displayList(); //debug the flight plaths
			
			scan = new Scanner(pathsFile); //read in each line of requested paths
			count = scan.nextInt();
			scan.nextLine();
			for (int i = 0; i < count; i++)
			{
				String s = scan.nextLine();
				if (!mgr.findPaths(s))
				{
					scan.close();
					throw new Exception("[Error] Something is wrong with the PathsToCalculateFile.");
				}
			}
			scan.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
