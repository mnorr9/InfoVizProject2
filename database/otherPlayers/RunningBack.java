package database.otherPlayers;

import database.Player;

public class RunningBack extends Player
{

  private Rusher   rushing_stats;
  private Receiver receiving_stats;

  public RunningBack()
  {
    rushing_stats   = new Rusher();
    receiving_stats = new Receiver();
  }

  public void createRunningBack(String[] stat_field)
  {
    player_name                           = stat_field[0];
    team_name                             = stat_field[1];
    num_games_played                      = Double.parseDouble(stat_field[2]);
    rushing_stats.rush_attempts           = Double.parseDouble(stat_field[3]);
    rushing_stats.rush_yards              = Double.parseDouble(stat_field[4]);
    rushing_stats.rush_yards_per_game     = Double.parseDouble(stat_field[5]);
    rushing_stats.rush_average            = Double.parseDouble(stat_field[6]);
    rushing_stats.rush_touchdowns         = Double.parseDouble(stat_field[7]);
    receiving_stats.receptions            = Double.parseDouble(stat_field[8]);
    receiving_stats.targets               = Double.parseDouble(stat_field[9]);
    receiving_stats.rec_yards             = Double.parseDouble(stat_field[10]);
    receiving_stats.rec_yards_per_game    = Double.parseDouble(stat_field[11]);
    receiving_stats.rec_average           = Double.parseDouble(stat_field[12]);
    receiving_stats.rec_long              = Double.parseDouble(stat_field[13]);
    receiving_stats.rec_yards_after_catch = Double.parseDouble(stat_field[14]);
    receiving_stats.num_of_first_downs    = Double.parseDouble(stat_field[15]);
    receiving_stats.rec_touchdowns        = Double.parseDouble(stat_field[16]);
    fumbles                               = Double.parseDouble(stat_field[17]);
    fumbles_lost                          = Double.parseDouble(stat_field[18]);
  }
}
