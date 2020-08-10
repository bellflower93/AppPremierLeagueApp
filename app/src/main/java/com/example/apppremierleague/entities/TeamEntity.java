package com.example.apppremierleague.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Map;

@Entity(tableName = "team_table")
public class TeamEntity implements Serializable {

    @PrimaryKey
    @NonNull
    private String teamId;

    private String rank;
    private String teamName;
    private String numOfMatch;
    private String lose;
    private String points;
    private String logoTeam;
    private String win;

    public TeamEntity(@NonNull String teamId, String rank, String teamName, String numOfMatch, String win, String lose, String points, String logoTeam) {
        this.teamId = teamId;
        this.rank = rank;
        this.teamName = teamName;
        this.numOfMatch = numOfMatch;
        this.win = win;
        this.lose = lose;
        this.points = points;
        this.logoTeam = logoTeam;
    }

    public String getTeamId() {
        return this.teamId;
    }

    public String getRank() {
        return this.rank;
    }

    public String getTeamName() {
        return teamName;
    }


    public String getNumOfMatch() {
        return numOfMatch;
    }

    public String getWin() {
        return win;
    }

    public String getLose() {
        return lose;
    }

    public String getPoints() {
        return points;
    }

    public String getLogoTeam() {
        return logoTeam;
    }

    public void setLogoTeam(String logoTeam) {
        this.logoTeam = logoTeam;
    }



    public static TeamEntity populateTeamFromMap(Map<String, Object> map) {
        TeamEntity teamEntity = new TeamEntity(map.containsKey("team_id") ? (map.get("team_id") instanceof String ? (String) map.get("team_id") : String.valueOf(map.get("team_id"))) : "", map.containsKey("rank") ? (String) map.get("rank") : "", map.containsKey("teamName") ? (String) map.get("teamName") : "", map.containsKey("matchsPlayed") ? (String) map.get("matchsPlayed") : "", map.containsKey("win") ? (String) map.get("win") : "", map.containsKey("lose") ? (String) map.get("lose") : "", map.containsKey("points") ? (String) map.get("points") : "", map.containsKey("logo") ? (String) map.get("logo") : "");
        return teamEntity;
    }
}
