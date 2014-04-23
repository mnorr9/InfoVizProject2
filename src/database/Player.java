package database;

public class Player
{

  protected String player_name;
  protected String team_name;
  protected double num_games_played;
  protected double fumbles;
  protected double fumbles_lost;

  public Player()
  {
    player_name = "";
    team_name   = "";
  }

  public String getPlayerName()
  {
    return player_name;
  }

  public String getTeamName()
  {
    return team_name;
  }

  public double getNumberOfGamesPlayed()
  {
    return num_games_played;
  }

  public double getNumberOfFumbles()
  {
    return fumbles;
  }

  public double getNumberOfFumblesLost()
  {
    return fumbles_lost;
  }

}
