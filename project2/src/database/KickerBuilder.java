package database;

import java.io.BufferedReader;
import java.util.ArrayList;  
import java.util.List;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.IOException;


public class KickerBuilder
{

  private List<Kicker> kickerList;
  
  public KickerBuilder()
  {
    kickerList = new ArrayList<Kicker>();
  }
  
  public void buildKickerDatabase()
  {
    String csvFileToRead = "C:\\Users\\David\\Desktop\\iv\\InfoViz Project 2\\InfoViz Project 2\\kicker_stats_2013.csv";
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

/*
  public class CsvToJavaObject {  
    
    public void convertCsvToJava() {  
     
      // print values stored in carList  
      printCarList(carList);  
 
    }  
     
    public void printCarList(List<cars> carListToPrint) {  
     for (int i = 0; i < carListToPrint.size(); i++) {  
      System.out.println("CARS [year= " + carListToPrint.get(i).getYear()  
        + " , make=" + carListToPrint.get(i).getMake()  
        + " , model=" + carListToPrint.get(i).getModel()  
        + " , description="  
        + carListToPrint.get(i).getDescription() + " , price="  
        + carListToPrint.get(i).getPrice() + "]");  
     }  
    }  
   }  
*/
}
