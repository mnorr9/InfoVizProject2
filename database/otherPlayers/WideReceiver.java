package database.otherPlayers;

import database.Player;

public class WideReceiver extends Player
{

  private Receiver receiving_stats;
  private Returner returning_stats;

  // special team stats

  public WideReceiver()
  {
    receiving_stats = new Receiver();
    returning_stats = new Returner();
  }

  public void createWideReceiver(String[] stat_field)
  {
    player_name                            = stat_field[0];
    team_name                              = stat_field[1];
    num_games_played                       = Double.parseDouble(stat_field[2]);
    receiving_stats.receptions             = Double.parseDouble(stat_field[3]);
    receiving_stats.targets                = Double.parseDouble(stat_field[4]);
    receiving_stats.rec_yards              = Double.parseDouble(stat_field[5]);
    receiving_stats.rec_yards_per_game     = Double.parseDouble(stat_field[6]);
    receiving_stats.rec_average            = Double.parseDouble(stat_field[7]);
    receiving_stats.rec_long               = Double.parseDouble(stat_field[8]);
    receiving_stats.rec_yards_after_catch  = Double.parseDouble(stat_field[9]);
    receiving_stats.num_of_first_downs     = Double.parseDouble(stat_field[10]);
    receiving_stats.rec_touchdowns         = Double.parseDouble(stat_field[11]);
    returning_stats.kick_return_attempts   = Double.parseDouble(stat_field[12]);
    returning_stats.kick_return_yards      = Double.parseDouble(stat_field[13]);
    returning_stats.kick_return_average    = Double.parseDouble(stat_field[14]);
    returning_stats.kick_return_long       = Double.parseDouble(stat_field[15]);
    returning_stats.kick_return_touchdowns = Double.parseDouble(stat_field[16]);
    returning_stats.punt_return_attempts   = Double.parseDouble(stat_field[17]);
    returning_stats.punt_return_yards      = Double.parseDouble(stat_field[18]);
    returning_stats.punt_return_average    = Double.parseDouble(stat_field[19]);
    returning_stats.punt_return_long       = Double.parseDouble(stat_field[20]);
    returning_stats.punt_return_touchdowns = Double.parseDouble(stat_field[21]);
    fumbles                                = Double.parseDouble(stat_field[22]);
    fumbles_lost                           = Double.parseDouble(stat_field[23]);
  }

}
