package database.otherPlayers;

import database.Player;

public class QuarterBack extends Player
{

  private Rusher rushing_stats;
  private double qb_rating;
  private double passing_completions;
  private double passing_attempts;
  private double passing_percentage;
  private double passing_yards;
  private double passing_yards_per_game;
  private double passing_yards_per_attempt;
  private double passing_touchdowns;
  private double interceptions;
  private double num_of_sacks;
  private double yards_lost;

  public QuarterBack()
  {
    rushing_stats = new Rusher();
  }

  public void createQuarterBack(String[] stat_field)
  {
    player_name                       = stat_field[0];
    team_name                         = stat_field[1];
    num_games_played                  = Double.parseDouble(stat_field[2]);
    qb_rating                         = Double.parseDouble(stat_field[3]);
    passing_completions               = Double.parseDouble(stat_field[4]);
    passing_attempts                  = Double.parseDouble(stat_field[5]);
    passing_percentage                = Double.parseDouble(stat_field[6]);
    passing_yards                     = Double.parseDouble(stat_field[7]);
    passing_yards_per_game            = Double.parseDouble(stat_field[8]);
    passing_yards_per_attempt         = Double.parseDouble(stat_field[9]);
    passing_touchdowns                = Double.parseDouble(stat_field[10]);
    interceptions                     = Double.parseDouble(stat_field[11]);
    rushing_stats.rush_attempts       = Double.parseDouble(stat_field[12]);
    rushing_stats.rush_yards          = Double.parseDouble(stat_field[13]);
    rushing_stats.rush_yards_per_game = Double.parseDouble(stat_field[14]);
    rushing_stats.rush_average        = Double.parseDouble(stat_field[15]);
    rushing_stats.rush_touchdowns     = Double.parseDouble(stat_field[16]);
    num_of_sacks                      = Double.parseDouble(stat_field[17]);
    yards_lost                        = Double.parseDouble(stat_field[18]);
    fumbles                           = Double.parseDouble(stat_field[19]);
    fumbles_lost                      = Double.parseDouble(stat_field[20]);
  }

  public double getQBRating()
  {
    return qb_rating;
  }

  public double getNumberOfCompletions()
  {
    return passing_completions;
  }

  public double getNumberOfAttempts()
  {
    return passing_attempts;
  }
}
