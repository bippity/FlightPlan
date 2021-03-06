//addCity(CityName)
//findPaths(Depart, Arrive)

import java.util.*;

public class Manager
{
	Stack<City> history;
	LinkedList<LinkedList<City>> mainList;
	public Manager()
	{
		history = new Stack<City>();
		mainList = new LinkedList<LinkedList<City>>();
	}
	
	/**
	 * addPath
	 * Processes the @path from input file.
	 * @return true if everything succeeds
	 */
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
			City departCity = new City(departName, 0, 0); //Departing city has no cost
			City destCity = new City(destName, cost, time);
			
			addCities(departCity, destCity);
			return true;
		}
		
		return false;
	}
	
	/**
	 * addCities
	 * Subfunction for addPath. Adds both cities into mainList
	 */
	private void addCities(City departCity, City destCity)
	{
		int index = 0 ;
		boolean found = false;
		for (int i = 0; i < mainList.size(); i++)
		{
			if (!mainList.get(i).isEmpty() && mainList.get(i).getFirst().name.equals(departCity.name)) //departCity already exists, set index
			{
				index = i;
				found = true;
				break;
			}
		}
		
		//Add the city
		if(!found) //If departCity doesn't exist, add it in first
		{
			mainList.add(new LinkedList<City>());
			index = mainList.size() - 1;
			mainList.get(index).add(departCity);
		}
		//Add destCity
		mainList.get(index).add(destCity);
		
		
		//Same thing as above, but for destCity - Reversed
		found = false;
		for (int i = 0; i < mainList.size(); i++)
		{
			if (!mainList.get(i).isEmpty() && mainList.get(i).getFirst().name.equals(destCity.name)) //destCity already exists, set index
			{
				index = i;
				found = true;
				break;
			}
		}
		
		if (!found)
		{
			mainList.add(new LinkedList<City>());
			index = mainList.size() - 1;
			mainList.get(index).add(destCity);
		}
		mainList.get(index).add(departCity);
	}
	
	/**
	 * Starting from the destination city, go through each of its child cities to find the departCity.
	 * Do the same thing for each of these child cities. (Recursive)
	 */
	public boolean findPaths(String path) //Search backwards. Start from destination.
	{
		String[] args = path.split("\\|");
		if (args.length == 3)
		{
			String departName = args[0];
			String destName = args[1];
			String sortType = args[2];
			if (!sortType.equals("T") || !sortType.equals("C"))
				return false;
			
			for (int i = 0; i < mainList.size(); i++) //search for destination
			{
				if (!mainList.get(i).isEmpty() && mainList.get(i).getFirst().name.equals(destName)) //Found destination
				{
					City destCity = mainList.get(i).getFirst();
					history.push(destCity); //adds destination to bottom of history stack. Should be popped out last.
					
					//iterate through destination's children
					for (int j = 0; j < mainList.get(i).size(); j++)
					{
						LinkedList<City> subList = mainList.get(i); //The destCity's linkedList
						City destChildCity = subList.get(j);
						findSubPath(destCity, destChildCity, departName, subList);
					}
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	//Recursive function. Loops through every child in the subList
	private void findSubPath(City parentCity, City childCity, String targetCityName, LinkedList<City> subList)
	{
		int index = 0;
		//Loop through sublist and look for the departCity
		for (int i = 0; i < subList.size(); i++)
		{
			if (subList.get(i).name.equals(targetCityName))
			{
				history.push(subList.get(i));
			}
		}
	}
	
	
	//For debugging; displays the linkedLists
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
			System.out.println("|");
		}
	}
}
