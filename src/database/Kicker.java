package database;

/**
 *   Kicker Class.
 *   Contains all necessary fields to create a kicker.
 * 
 *   @author David
 */
public class Kicker extends Player
{
  private FGDistanceStats distance_attempted;
  private FGDistanceStats distance_made;
  private double field_goals_made;
  private double field_goal_attempts;
  private double field_goal_percentage;
  private double field_goal_long;
  private double extra_points_made;
  private double extra_point_attempts;
  private double extra_point_percentage;
  private double extra_point_points;
  private static final String splitBy = "-";

  /**
   *   Constructor for the Kicker class.
   *   Creates instances of the field goal stats class
   *   for attempts and makes.
   */
  public Kicker()
  {
    distance_attempted = new FGDistanceStats();
    distance_made      = new FGDistanceStats();
    
    //Default these player stats to 0.
    fumbles      = 0;
    fumbles_lost = 0;
  }

  /**
   *   Function to populate the fields in the kicker class.
   *   Takes an array string that contains each element in
   *   the class. Current design needs the string to match
   *   up perfectly in the right order.
   *   
   *   @param stat_field
   */
  public void createKicker(String[] stat_field)
  {
    String[] temp;
    
    player_name                   = stat_field[0];
    team_name                     = stat_field[1];
    num_games_played              = Double.parseDouble(stat_field[2]);
    temp = stat_field[3].split(splitBy);
    distance_made.fg_0_19         = Double.parseDouble(temp[0]);
    distance_attempted.fg_0_19    = Double.parseDouble(temp[1]);
    temp = stat_field[4].split(splitBy);
    distance_made.fg_20_29        = Double.parseDouble(temp[0]);
    distance_attempted.fg_20_29   = Double.parseDouble(temp[1]);
    temp = stat_field[5].split(splitBy);
    distance_made.fg_30_39        = Double.parseDouble(temp[0]);
    distance_attempted.fg_30_39   = Double.parseDouble(temp[1]);
    temp = stat_field[6].split(splitBy);
    distance_made.fg_40_49        = Double.parseDouble(temp[0]);
    distance_attempted.fg_40_49   = Double.parseDouble(temp[1]);
    temp = stat_field[7].split(splitBy);
    distance_made.fg_50_plus      = Double.parseDouble(temp[0]);
    distance_attempted.fg_50_plus = Double.parseDouble(temp[1]);
    field_goals_made              = Double.parseDouble(stat_field[8]);
    field_goal_attempts           = Double.parseDouble(stat_field[9]);
    field_goal_percentage         = Double.parseDouble(stat_field[10]);
    field_goal_long               = Double.parseDouble(stat_field[11]);
    extra_points_made             = Double.parseDouble(stat_field[12]);
    extra_point_attempts          = Double.parseDouble(stat_field[13]);
    extra_point_percentage        = Double.parseDouble(stat_field[14]);
    extra_point_points            = Double.parseDouble(stat_field[15]);
  }

  /**
   *   Getter to retrieve the number of field goals made
   *   between 10 and 19 yards.
   *    
   *   @return double
   */
  public double get10To19FieldGoalsMade()
  {
    return distance_made.get10To19FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goal attempts
   *   between 10 and 19 yards.
   *    
   *   @return double
   */
  public double get10To19FieldGoalsAttempted()
  {
    return distance_attempted.get10To19FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 20 and 29 yards.
   *    
   *   @return double
   */
  public double get20To29FieldGoalsMade()
  {
    return distance_made.get20To29FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goal attempts
   *   between 20 and 29 yards.
   *    
   *   @return double
   */
  public double get20To29FieldGoalsAttempted()
  {
    return distance_attempted.get20To29FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 30 and 39 yards.
   *    
   *   @return double
   */
  public double get30To39FieldGoalsMade()
  {
    return distance_made.get30To39FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goal attempts
   *   between 30 and 39 yards.
   *    
   *   @return double
   */
  public double get30To39FieldGoalsAttempted()
  {
    return distance_attempted.get30To39FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   between 40 and 49 yards.
   *    
   *   @return double
   */
  public double get40To49FieldGoalsMade()
  {
    return distance_made.get40To49FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goal attempts
   *   between 40 and 49 yards.
   *    
   *   @return double
   */
  public double get40To49FieldGoalsAttempted()
  {
    return distance_attempted.get40To49FieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goals made
   *   from 50+ yards.
   *    
   *   @return double
   */
  public double get50PlusFieldGoalsMade()
  {
    return distance_made.get50PlusFieldGoals();
  }
  
  /**
   *   Getter to retrieve the number of field goal attempts
   *   from 50+ yards.
   *    
   *   @return double
   */
  public double get50PlusFieldGoalsAttempted()
  {
    return distance_attempted.get50PlusFieldGoals();
  }

  /**
   *   Getter to retrieve the number of field goals made.
   *
   *   @return double
   */
  public double getFieldGoalsMade()
  {
    return field_goals_made;
  }

  /**
   *   Getter to retrieve the number of field goal attempts.
   *
   *   @return double
   */
  public double getFieldGoalAttempts()
  {
    return field_goal_attempts;
  }

  /**
   *   Getter to retrieve the kickers field goal percentage.
   *
   *   @return double
   */
  public double getFieldGoalPercentage()
  {
    return field_goal_percentage;
  }
  
  /**
   *   Getter to retrieve the kickers longest field
   *   goal made.
   *
   *   @return double
   */
  public double getLongestFieldGoal()
  {
    return field_goal_long;
  }

  /**
   *   Getter to retrieve the number of extra points made.
   *
   *   @return double
   */
  public double getExtraPointsMade()
  {
    return extra_points_made;
  }

  /**
   *   Getter to retrieve the number of extra point attempts.
   *
   *   @return double
   */
  public double getExtraPointAttempts()
  {
    return extra_point_attempts;
  }

  /**
   *   Getter to retrieve the kickers extra point percentage.
   *
   *   @return double
   */
  public double getExtraPointPercentage()
  {
    return extra_point_percentage;
  }

  /**
   *   Getter to retrieve the kickers extra point points.
   *
   *   @return double
   */
  public double getExtraPointPoints()
  {
    return extra_point_points;
  }
  
}
