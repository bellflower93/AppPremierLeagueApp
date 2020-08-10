package com.example.apppremierleague.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.dom.DOMLocator;

@Entity(tableName = "player_table")
public class PlayerEntity implements Serializable {
    @PrimaryKey
    @NonNull
    private Double playerId;

    private String playerName;
    private String teamName;
    private Double teamId;
    private String firstName;
    private String lastName;
    private String position;
    private Double age;
    private Double numGames;
    private Double yellowCard;
    private Double redCard;
    private Double goal;
    private Double assists;
    private Double shotTotal;
    private Double shotOn;
    private Double dribbleTotal;
    private Double dribbleSuccess;
    private Double passesTotal;
    private Double passesKey;
    private Double foulsCommitted;
    private Double tacklesTotal;
    private Double blocks;
    private Double interceptions;
    private Double duelTotal;
    private Double duelWon;

    public PlayerEntity(@NonNull Double playerId, String playerName, Double teamId, String teamName, String firstName, String lastName,
                        String position, Double age, Double numGames, Double yellowCard, Double redCard,
                        Double goal, Double assists, Double shotTotal, Double shotOn, Double dribbleTotal,
                        Double dribbleSuccess, Double passesKey, Double passesTotal, Double foulsCommitted,
                        Double tacklesTotal, Double blocks, Double interceptions,
                        Double duelTotal, Double duelWon) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.teamId = teamId;
        this.teamName = teamName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.age = age;
        this.numGames = numGames;
        this.yellowCard = yellowCard;
        this.redCard = redCard;
        this.goal = goal;
        this.assists = assists;
        this.shotOn = shotOn;
        this.shotTotal = shotTotal;
        this.dribbleSuccess = dribbleSuccess;
        this.dribbleTotal = dribbleTotal;
        this.passesKey = passesKey;
        this.passesTotal = passesTotal;
        this.foulsCommitted = foulsCommitted;
        this.tacklesTotal = tacklesTotal;
        this.blocks = blocks;
        this.interceptions = interceptions;
        this.duelTotal = duelTotal;
        this.duelWon = duelWon;
    }

    @NonNull
    public Double getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public Double getTeamId() {
        return teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Double getAge() {
        return age;
    }

    public Double getNumGames() {
        return numGames;
    }

    public Double getYellowCard() {
        return yellowCard;
    }

    public Double getRedCard() {
        return redCard;
    }

    public Double getGoal() {
        return goal;
    }

    public Double getAssists() {
        return assists;
    }

    public Double getShotTotal() {
        return shotTotal;
    }

    public Double getShotOn() {
        return shotOn;
    }

    public Double getDribbleTotal() {
        return dribbleTotal;
    }

    public Double getDribbleSuccess() {
        return dribbleSuccess;
    }

    public Double getPassesTotal() {
        return passesTotal;
    }

    public Double getPassesKey() {
        return passesKey;
    }

    public Double getFoulsCommitted() {
        return foulsCommitted;
    }

    public Double getTacklesTotal() {
        return tacklesTotal;
    }

    public Double getBlocks() {
        return blocks;
    }

    public Double getInterceptions() {
        return interceptions;
    }

    public Double getDuelTotal() {
        return duelTotal;
    }

    public Double getDuelWon() {
        return duelWon;
    }

    public static PlayerEntity populatePlayerFromMap(Map<String, Object> map) {

        double numOfGames = 0.0;
        if (map.containsKey("games")) {
            Map<String, Double> appearences = (Map<String, Double>) map.get("games");
            numOfGames = appearences.get("appearences");
        }
        double redCards = 0.0;
        double yellowCards = 0.0;
        if (map.containsKey("cards")) {
            Map<String, Double> cards = ( Map<String, Double>) map.get("cards");
            redCards = cards.get("red");
            yellowCards = cards.get("yellow");
        }
        double totalGoals = 0.0;
        double assistsGoals = 0.0;
        if (map.containsKey("goals")) {
            Map<String, Double> goals = ( Map<String, Double>) map.get("goals");
            totalGoals = goals.get("total");
            assistsGoals = goals.get("assists");
        }

        double shotTotal = 0.0;
        double shotOn = 0.0;
        if (map.containsKey("shots")) {
            Map<String, Double> shots = ( Map<String, Double>) map.get("shots");
            shotTotal = shots.get("total");
            shotOn = shots.get("on");
        }
        double dribbleTotal = 0.0;
        double dribbleSuccess = 0.0;
        if (map.containsKey("dribless")) {
            Map<String, Double> dribless = (Map<String, Double>) map.get("dribless");
            dribbleTotal = dribless.get("attempts");
            dribbleSuccess = dribless.get("success");
        }
        double passesTotal = 0.0;
        double passesKey = 0.0;
        if (map.containsKey("passes")) {
            Map<String, Double> passes = (Map<String, Double>) map.get("passes");
            passesTotal = passes.get("total");
            passesKey = passes.get("key");
        }
        double fouls = 0.0;
        if (map.containsKey("fouls")) {
            Map<String, Double> committed = ( Map<String, Double>) map.get("fouls");
            fouls = committed.get("committed");
        }
        double tackles = 0.0;
        double blocks = 0.0;
        double interceptors = 0.0;
        if (map.containsKey("tackles")) {
            Map<String, Double> tacklesObj = ( Map<String, Double>) map.get("tackles");
            tackles = tacklesObj.get("total");
            blocks = tacklesObj.get("blocks");
            interceptors = tacklesObj.get("interceptions");
        }
        double duelsWon = 0.0;
        double duelsTotals = 0.0;
        if (map.containsKey("duels")) {
            Map<String, Double> duels = ( Map<String, Double>) map.get("duels");
            duelsTotals = duels.get("total");
            duelsWon = duels.get("won");
        }
        PlayerEntity playerEntity = new PlayerEntity(map.containsKey("player_id") ? (Double) map.get("player_id") : 0,
                map.containsKey("player_name") ? (String) map.get("player_name") : "",
                map.containsKey("team_id") ? (Double) map.get("team_id") : 0,
                map.containsKey("team_name") ? (String) map.get("team_name") : "",
                map.containsKey("firstname") ? (String) map.get("firstname") : "",
                map.containsKey("lastname") ? (String) map.get("lastname") : "",
                map.containsKey("position") ? (String) map.get("position") : "",
                map.containsKey("age") ? (Double) map.get("age") : 0,
                numOfGames, yellowCards, redCards,totalGoals,assistsGoals, shotTotal, shotOn, dribbleTotal, dribbleSuccess, passesTotal,
                passesKey, fouls, tackles, blocks,interceptors, duelsTotals,duelsWon);
        return playerEntity;
    }
    @Override
    public String toString() {
        return getPlayerName();
    }
}
