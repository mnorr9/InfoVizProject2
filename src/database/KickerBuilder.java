package database;

import java.io.BufferedReader;
import java.util.ArrayList;  
import java.util.List;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;

import database.Kicker;
import java.util.Collections;


/**
 *   Kicker Builder Class.
 *   Contains all kickers that have been read in.
 *   Class is a database of all kickers.
 * 
 *   @author David
 */
public class KickerBuilder
{

  private List<Kicker> kickerList;
  
  /**
   *   Constructor for the Kicker Builder class.
   *   Initializes a new list of kickers to be used.
   */
  public KickerBuilder()
  {
    kickerList = new ArrayList<Kicker>();
  }
  
  /**
   *   Function to build the kicker database.
   *   File that contains all the kickers is the string
   *   that gets passed in.
   *   The function will read one line in at a time,
   *   parse the line and then create a kicker with that
   *   data.
   *   
   *   @param csvFileToRead
   */
  public void buildKickerDatabase(String csvFileToRead)
  {
    BufferedReader br = null;
    String line = "";
    String splitBy = ",";
    
    try
    {
      kickerList.clear();
      
      br = new BufferedReader(new FileReader(csvFileToRead));
      line = br.readLine();  //Read in first line since it is just categories.

      while ((line = br.readLine()) != null)
      {  
        // split on comma(',')
        String[] stats = line.split(splitBy);

        // create kicker object to store values
        Kicker kickerObject = new Kicker();

        // add values from csv to kicker object
        kickerObject.createKicker(stats);

        // adding kicker objects to a list
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

  /**
   *   Getter to retrieve the kicker by name.
   *   If no kicker is found then null is returned.
   * 
   *   @param  kicker_name
   *   @return Kicker
   */
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
  
  /**
   *   Getter to retrieve the kicker by index.
   *   If the index is invalid then null is returned.
   * 
   *   @param  kicker_index
   *   @return Kicker
   */
  public Kicker getKicker(int kicker_index)
  {
    if( kicker_index >= 0 && kicker_index <= kickerList.size() )
    {
      return kickerList.get(kicker_index);
    }

    return null;
  }

  /**
   *   Getter to retrieve the number of kickers.
   * 
   *   @return int
   */
  public int getNumOfKickers()
  {
    return kickerList.size();
  }
  
  /**
   *   Getter to retrieve the list of kickers names.
   *
   *   @return ArrayList<String>
   */
  public ArrayList<String> getNameList() {
	  ArrayList<String> names = new ArrayList<String>();
	  for(int i=0; i<getNumOfKickers(); i++) {
		  names.add(getKicker(i).getPlayerName());
	  }
          Collections.sort(names); //sort the list
	  return names;
  }

}
