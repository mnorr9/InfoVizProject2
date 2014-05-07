package database;

/**
 *   Field Goal Distance Stats Class.
 *   Contains all the field goal distances a kicker
 *   can kick from.
 * 
 *   @author David Gwalthney
 */
public class FGDistanceStats
{
  private double fg_0_19;
  private double fg_20_29;
  private double fg_30_39;
  private double fg_40_49;
  private double fg_50_plus;

  /**
   *   Constructor for the field goal distance class.
   */
  public FGDistanceStats()
  {
    
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 10 and 19 yards.
   *    
   *   @return double. The number of fields goals successfully executed by this player
   *   from the 10th to the 19th yard as a double 
   */
  public double get10To19FieldGoals()
  {
    return fg_0_19;
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 20 and 29 yards.
   *    
   *   @return double. The number of fields goals successfully executed by this player
   *   from the 20th to the 29th yard as a double 
   */
  public double get20To29FieldGoals()
  {
    return fg_20_29;
  }
    
  /**
   *   Getter to retrieve the number of field goals made
   *   between 30 and 39 yards.
   *    
   *   @return double. The number of fields goals successfully executed by this player
   *   from the 30th to the 39th yard as a double 
   */
  public double get30To39FieldGoals()
  {
    return fg_30_39;
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 40 and 49 yards.
   *    
   *   @return double. The number of fields goals successfully executed by this player
   *   from the 40th to the 49th yard as a double 
   */
  public double get40To49FieldGoals()
  {
    return fg_40_49;
  }
    
  /**
   *   Getter to retrieve the number of field goals made
   *   at 50+ yards.
   *    
   *   @return double. The number of fields goals successfully executed by this player
   *   from the 50th yards and below as a double 
   */
  public double get50PlusFieldGoals()
  {
    return fg_50_plus;
  }

  /**
   *   Setter function to save off the number of field goals made
   *   between 10 and 19 yards.
   *   
   *   @param double. The number of fields goals successfully executed by this player
   *   from the 10th to the 19th yard as a double 
   */
  public void set10To19FieldGoals(double goals)
  {
    fg_0_19 = goals;
  }
  
  /**
   *   Setter function to save off the number of field goals made
   *   between 20 and 29 yards.
   *
   *   @param double. The number of fields goals successfully executed by this player
   *   from the 20th to the 29th yard as a double 
   */
  public void set20To29FieldGoals(double goals)
  {
    fg_20_29 = goals;
  }
    
  /**
   *   Setter function to save off the number of field goals made
   *   between 30 and 39 yards.
   * 
   *   @param double. The number of fields goals successfully executed by this player
   *   from the 30th to the 39th yard as a double
   */
  public void set30To39FieldGoals(double goals)
  {
    fg_30_39 = goals;
  }
  
  /**
   *   Setter function to save off the number of field goals made
   *   between 40 and 49 yards.
   * 
   *  @param double. The number of fields goals successfully executed by this player
   *   from the 40th to the 49th yard as a double 
   */
  public void set40To49FieldGoals(double goals)
  {
    fg_40_49 = goals;
  }
    
  /**
   *   Setter function to save off the number of field goals made
   *   at 50+ yards.  
   *   @param double. The number of fields goals successfully executed by this player
   *   from the 50th yard and below as a double 
   */
  public void set50PlusFieldGoals(double goals)
  {
    fg_50_plus = goals;
  }  
  
}
