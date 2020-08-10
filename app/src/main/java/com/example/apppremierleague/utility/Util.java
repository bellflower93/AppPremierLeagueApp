package com.example.apppremierleague.utility;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.entities.TeamEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    public static ArrayList<TeamEntity> parseResponseStanding(String jsonString) {
        ArrayList<TeamEntity> listOfStanding = new ArrayList<>();
        Map<String, Object> fullJsonMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        if (fullJsonMap.containsKey("api")) {
            Map<String, Object> api = (Map<String, Object>) fullJsonMap.get("api");
            if (api != null && api.containsKey("standings")) {
                List<Object> teams = (List<Object>) api.get("standings");
                if (teams != null) {
                    for (Object team : teams) {
                        for (Object one : (ArrayList<Object>) team) {
                            listOfStanding.add(TeamEntity.populateTeamFromMap((Map<String, Object>) one));
                        }
                    }
                }
            }
        }
        return listOfStanding;
    }

    public static ArrayList<TeamEntity> parseResponseTeamsList(String jsonString) {
        ArrayList<TeamEntity> listOfStanding = new ArrayList<>();
        Map<String, Object> fullJsonMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        if (fullJsonMap.containsKey("api")) {
            Map<String, Object> api = (Map<String, Object>) fullJsonMap.get("api");
            if (api != null && api.containsKey("teams")) {
                List<Object> teams = (List<Object>) api.get("teams");
                if (teams != null) {
                    for (Object team : teams) {
                        listOfStanding.add(TeamEntity.populateTeamFromMap((Map<String, Object>) team));
                    }
                }
            }
        }
        return listOfStanding;
    }

    public static ArrayList<PlayerEntity> parseResponsePlayersList(String jsonString) {
        ArrayList<PlayerEntity> listOfPlayers = new ArrayList<>();
        Map<String, Object> fullJsonMap = new Gson().fromJson(
                jsonString, new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
        if (fullJsonMap.containsKey("api")) {
            Map<String, Object> api = (Map<String, Object>) fullJsonMap.get("api");
            if (api != null && api.containsKey("players")) {
                List<Object> players = (List<Object>) api.get("players");
                if (players != null) {
                    for (Object team : players) {
                        if (((Map<String, Object>) team).containsKey("league") && (((Map<String, Object>) team).get("league") != null) && (((Map<String, Object>) team).get("league").equals("Premier League")) && (((Map<String, Object>) team).get("season") != null) && (((Map<String, Object>) team).get("season").equals("2018-2019"))) {
                            listOfPlayers.add(PlayerEntity.populatePlayerFromMap((Map<String, Object>) team));
                        }

                    }
                }
            }
        }
        return listOfPlayers;
    }

    public static void openNewActivity(Context context, Class<?> nextActivity, Bundle bundle) {
        Intent intent = new Intent(context, nextActivity);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public static Double sumPoints(PlayerEntity playerEntity) {
        switch (playerEntity.getPosition()) {
            case Configuration.ATTACKER:
                return playerEntity.getGoal() * 5 + playerEntity.getAssists() * 3 + playerEntity.getShotOn() * 0.5
                        + playerEntity.getDribbleSuccess() * 0.5 - playerEntity.getRedCard() - playerEntity.getYellowCard() * 0.5
                        - (playerEntity.getShotTotal() - playerEntity.getShotOn()) * 0.2 - (playerEntity.getDribbleTotal() - playerEntity.getDribbleSuccess()) * 0.2;
            case Configuration.MIDFIELDER:
                return playerEntity.getGoal() * 3 + playerEntity.getAssists() * 5 + playerEntity.getPassesKey()
                        + playerEntity.getDribbleSuccess() * 0.5 - playerEntity.getRedCard() - playerEntity.getYellowCard() * 0.5
                        - playerEntity.getFoulsCommitted() * 0.2 - (playerEntity.getDribbleTotal() - playerEntity.getDribbleSuccess()) * 0.5;
            case Configuration.DEFENDER:
                return playerEntity.getDuelWon() * 5 + playerEntity.getBlocks() * 3 + playerEntity.getInterceptions() * 3
                        + playerEntity.getTacklesTotal() - playerEntity.getRedCard() - playerEntity.getYellowCard() * 0.5
                        - playerEntity.getDuelTotal() - (playerEntity.getDuelTotal() - playerEntity.getDuelWon());
        }
        return 0.0;
    }
}
