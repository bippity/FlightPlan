//addCity(CityName)
//findPaths(Depart, Arrive)

import java.util.*;

public class Manager
{
	Stack<String> backtrack;
	LinkedList<LinkedList<City>> mainList;
	public Manager()
	{
		backtrack= new Stack<String>();
		mainList = new LinkedList<LinkedList<City>>();
	}
	
	//Processes the path from input file
	public Boolean addPath(String path)
	{
		//add the stuff into the respective lists
		String[] args = path.split("\\|");
		if (args.length == 4)
		{
			String departName = args[0];
			String destName = args[1];
			int cost = Integer.parseInt(args[2]);
			int time = Integer.parseInt(args[3]);
			City temp = new City(departName, 0, 0); //Departing city has no cost
			
			
			//Determine if mainList already has the city
			int departIndex = 0 ;
			boolean found = false;
			for (int i = 0; i < mainList.size(); i++)
			{
				if (!mainList.get(i).isEmpty() && mainList.get(i).getFirst().name.equals(departName)) //departingCity already exists, set temp to destCity.
				{
					temp = new City(destName, cost, time);
					departIndex = i;
					found = true;
					break;
				}
			}
			
			//Add the city
			if(!found) //If departCity doesn't exist, add it in first
			{
				mainList.add(new LinkedList<City>());
				departIndex = mainList.size() - 1;
				mainList.get(departIndex).add(temp);
				
				//set temp back to destCity
				temp = new City(destName, cost, time);
			}
			//Add destCity
			mainList.get(departIndex).add(temp); 
			return true;
		}
		
		return false;
	}
	
	
	
	
	//For debugging
	public void displayList()
	{
		for(int i = 0; i < mainList.size(); i++)
		{
			LinkedList<City> temp = mainList.get(i);
			for (int j = 0; j < temp.size(); j++)
			{
				System.out.print(temp.get(j).name + " -> ");
			}
			System.out.println();
		}
	}
}
