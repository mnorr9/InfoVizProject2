package database;

/**
 *   Player Class.
 *   Contains all necessary fields that are common
 *   between all NFL players.
 * 
 *   @author David
 */
public class Player
{

  protected String player_name;
  protected String team_name;
  protected double num_games_played;
  protected double fumbles;
  protected double fumbles_lost;

  /**
   *   Constructor for the Player class.
   *   Creates instances of the field goal stats class.
   *   Initializes the strings for player and team names.
   */
  public Player()
  {
    player_name = "";
    team_name   = "";
  }

  /**
   *   Getter to retrieve the Players name.
   *    
   *   @return String
   */
  public String getPlayerName()
  {
    return player_name;
  }

  /**
   *   Getter to retrieve the Team name that the 
   *   Player plays for.
   *    
   *   @return String
   */
  public String getTeamName()
  {
    return team_name;
  }

  /**
   *   Getter to retrieve the number of games the
   *   player has played that season.
   *    
   *   @return double
   */
  public double getNumberOfGamesPlayed()
  {
    return num_games_played;
  }

  /**
   *   Getter to retrieve the number of fumbles a
   *   player had.
   *    
   *   @return double
   */
  public double getNumberOfFumbles()
  {
    return fumbles;
  }

  /**
   *   Getter to retrieve the number of fumbles a
   *   player has lost.
   *    
   *   @return double
   */
  public double getNumberOfFumblesLost()
  {
    return fumbles_lost;
  }

}
