package database;

public class FGDistanceStats
{
  public double fg_0_19;
  public double fg_20_29;
  public double fg_30_39;
  public double fg_40_49;
  public double fg_50_plus;

  public FGDistanceStats()
  {
    
  }
  
  public double get10To19FieldGoals()
  {
    return fg_0_19;
  }
  
  public double get20To29FieldGoals()
  {
    return fg_20_29;
  }
    
  public double get30To39FieldGoals()
  {
    return fg_30_39;
  }
  
  public double get40To49FieldGoals()
  {
    return fg_40_49;
  }
    
  public double get50PlusFieldGoals()
  {
    return fg_50_plus;
  }

}
