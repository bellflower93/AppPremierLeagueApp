package com.example.apppremierleague.utility;

public class Configuration {

    public final static String RAPID_API = "https://api-football-v1.p.rapidapi.com/";
    public final static String GET_LEAGUE_TABLE = RAPID_API + "leagueTable/2";
    public final static String GET_LEAGUE = RAPID_API + "v2/teams/league/2";
    public final static String GET_PLAYERS_OF_TEAM = RAPID_API + "v2/players/team/";

    public final static String X_RAPID_API_KEY_HEADER_PARAMETER_KEY = "X-RapidAPI-Key";
    public final static String X_RAPID_API_KEY_HEADER_PARAMETER_VALUE = "63284ca007msh15dbd714e326824p1120e4jsn99bdcaf89bea";

    public final static int RESPONSE_LEAGUE_TABLE = 1;
    public final static int RESPONSE_LEAGUE = 2;
    public final static int RESPONSE_TEAM_PLAYERS = 3;

    public final static String FOOTBALL_DATABASE = "football_database";

    public final static String BUNDLE_KEY_TEAM = "team";
    public final static String BUNDLE_KEY_PLAYER_1 = "player_1";
    public final static String BUNDLE_KEY_PLAYER_2 = "player_2";

    public final static String ATTACKER = "Attacker";
    public final static String DEFENDER = "Defender";
    public final static String MIDFIELDER = "Midfielder";


}
