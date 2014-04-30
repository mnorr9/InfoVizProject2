package database;

/**
 *   Field Goal Distance Stats Class.
 *   Contains all the field goal distances a kicker
 *   can kick from.
 * 
 *   @author David
 */
public class FGDistanceStats
{
  public double fg_0_19;
  public double fg_20_29;
  public double fg_30_39;
  public double fg_40_49;
  public double fg_50_plus;

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
   *   @return double
   */
  public double get10To19FieldGoals()
  {
    return fg_0_19;
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 20 and 29 yards.
   *    
   *   @return double
   */
  public double get20To29FieldGoals()
  {
    return fg_20_29;
  }
    
  /**
   *   Getter to retrieve the number of field goals made
   *   between 30 and 39 yards.
   *    
   *   @return double
   */
  public double get30To39FieldGoals()
  {
    return fg_30_39;
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 40 and 49 yards.
   *    
   *   @return double
   */
  public double get40To49FieldGoals()
  {
    return fg_40_49;
  }
    
  /**
   *   Getter to retrieve the number of field goals made
   *   at 50+ yards.
   *    
   *   @return double
   */
  public double get50PlusFieldGoals()
  {
    return fg_50_plus;
  }

}
