package database.otherPlayers;

import database.Player;

public class Defense extends Player
{
  private double points_against_per_game;
  private double yards_against_per_game;
  private double rush_yards_against_per_game;
  private double pass_yards_against_per_game;
  private double interceptions;
  private double interceptions_touchdowns;
  private double forced_fumbles;
  private double defensive_touchdown;
  private double tackles;
  private double pd;  //punt downs?
  private double num_of_sacks;
  
  public Defense()
  {
    player_name  = "Not Applicable";
    fumbles      = 0;
    fumbles_lost = 0;
  }
  
  public void createDefense(String[] stat_field)
  {
    team_name                   = stat_field[1];
    num_games_played            = Double.parseDouble(stat_field[2]);
    points_against_per_game     = Double.parseDouble(stat_field[3]);
    yards_against_per_game      = Double.parseDouble(stat_field[4]);
    rush_yards_against_per_game = Double.parseDouble(stat_field[5]);
    pass_yards_against_per_game = Double.parseDouble(stat_field[6]);
    interceptions               = Double.parseDouble(stat_field[7]);
    interceptions_touchdowns    = Double.parseDouble(stat_field[8]);
    forced_fumbles              = Double.parseDouble(stat_field[9]);
    defensive_touchdown         = Double.parseDouble(stat_field[10]);
    tackles                     = Double.parseDouble(stat_field[11]);
    pd                          = Double.parseDouble(stat_field[12]);
    num_of_sacks                = Double.parseDouble(stat_field[13]);
  }
}
