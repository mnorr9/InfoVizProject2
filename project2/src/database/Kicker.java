package database;

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
  private String splitBy;
  
  public Kicker()
  {
    splitBy = "-";
    
    distance_attempted = new FGDistanceStats();
    distance_made      = new FGDistanceStats();
    
    //Default these player stats to 0.
    fumbles      = 0;
    fumbles_lost = 0;
  }
  
  public void createKicker(String[] stat_field)
  {
    String[] temp;
    
    player_name                   = stat_field[0];
    team_name                     = stat_field[1];
    num_games_played              = Double.parseDouble(stat_field[2]);
    temp = stat_field[3].split(splitBy);
    distance_made.fg_0_19         = Double.parseDouble(temp[0]); //Double.parseDouble(stat_field[3]);
    distance_attempted.fg_0_19    = Double.parseDouble(temp[1]); //Double.parseDouble(stat_field[3]);
    temp = stat_field[4].split(splitBy);
    distance_made.fg_20_29        = Double.parseDouble(temp[0]); //Double.parseDouble(stat_field[4]);
    distance_attempted.fg_20_29   = Double.parseDouble(temp[1]); //Double.parseDouble(stat_field[4]);
    temp = stat_field[5].split(splitBy);
    distance_made.fg_30_39        = Double.parseDouble(temp[0]); //Double.parseDouble(stat_field[5]);
    distance_attempted.fg_30_39   = Double.parseDouble(temp[1]); //Double.parseDouble(stat_field[5]);
    temp = stat_field[6].split(splitBy);
    distance_made.fg_40_49        = Double.parseDouble(temp[0]); //Double.parseDouble(stat_field[6]);
    distance_attempted.fg_40_49   = Double.parseDouble(temp[1]); //Double.parseDouble(stat_field[6]);
    temp = stat_field[7].split(splitBy);
    distance_made.fg_50_plus      = Double.parseDouble(temp[0]); //Double.parseDouble(stat_field[7]);
    distance_attempted.fg_50_plus = Double.parseDouble(temp[1]); //Double.parseDouble(stat_field[7]);
    field_goals_made              = Double.parseDouble(stat_field[8]);
    field_goal_attempts           = Double.parseDouble(stat_field[9]);
    field_goal_percentage         = Double.parseDouble(stat_field[10]);
    field_goal_long               = Double.parseDouble(stat_field[11]);
    extra_points_made             = Double.parseDouble(stat_field[12]);
    extra_point_attempts          = Double.parseDouble(stat_field[13]);
    extra_point_percentage        = Double.parseDouble(stat_field[14]);
    extra_point_points            = Double.parseDouble(stat_field[15]);
  }
  
}
