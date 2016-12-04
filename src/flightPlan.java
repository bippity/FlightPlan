
import java.io.File;
import java.util.Scanner;

public class FlightPlan 
{
	public static void main(String[] args) 
	{
		if (args.length == 3)
		{
			try 
			{
				Scanner scan = new Scanner(System.in);
				File inputFile = new File(args[0]);
				File pathsFile = new File(args[1]);
				File outputFile = new File(args[2]);
				scan.close();
			} catch (Exception e) 
			{
				System.out.println(e.toString());
			}
		}
		else
		{
			System.out.println("Correct Syntax: ./flightPlan <FlightDataFile> <PathsToCaculateFile> <OutputFile>");
			return;
		}
	}
}
