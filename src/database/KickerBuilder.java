package database;

import java.io.BufferedReader;
import java.util.ArrayList;  
import java.util.List;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;

import database.Kicker;


public class KickerBuilder
{

  private List<Kicker> kickerList;
  
  public KickerBuilder()
  {
    kickerList = new ArrayList<Kicker>();
  }
  
  public void buildKickerDatabase()
  {
    String csvFileToRead = "kicker_stats_2013.csv";
    BufferedReader br = null;
    String line = "";
    String splitBy = ",";
    
    try
    {
      br = new BufferedReader(new FileReader(csvFileToRead));
      line = br.readLine();  //Read in first line since it is just categories.

      while ((line = br.readLine()) != null)
      {  
        // split on comma(',')
        String[] stats = line.split(splitBy);

        // create car object to store values
        Kicker kickerObject = new Kicker();

        // add values from csv to car object
        kickerObject.createKicker(stats);

        // adding car objects to a list
        kickerList.add(kickerObject);
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    finally
    {
      if (br != null)
      {
        try
        {
          br.close();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  public Kicker getKicker(String kicker_name)
  {
    for( Kicker item: kickerList )
    {
      if( item.player_name.equals(kicker_name) )
      {
        return item;
      }
    }
    
    return null;
  }
  
  public Kicker getKicker(int kicker_index)
  {
    if( kicker_index >= 0 && kicker_index <= kickerList.size() )
    {
      return kickerList.get(kicker_index);
    }

    return null;
  }

  public int getNumOfKickers()
  {
    return kickerList.size();
  }
  
  public ArrayList<String> getNameList() {
	  ArrayList<String> names = new ArrayList<String>();
	  for(int i=0; i<getNumOfKickers(); i++) {
		  names.add(getKicker(i).getPlayerName());
	  }
	  return names;
  }

}
