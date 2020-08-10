package com.example.apppremierleague.utility;

import android.app.Activity;

import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.view.BaseActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetUtil {


    public static String sendGetRequest(final Activity activity, String url, int typeResponse) {
        FootballRoomDatabase footballRoomDatabase = FootballRoomDatabase.getDatabase(activity);

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .header(Configuration.X_RAPID_API_KEY_HEADER_PARAMETER_KEY, Configuration.X_RAPID_API_KEY_HEADER_PARAMETER_VALUE)
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                switch (typeResponse) {
                    case Configuration.RESPONSE_TEAM_PLAYERS:
                        ((BaseActivity) activity).getPlayerFromDatabase(footballRoomDatabase);
                    break;
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                switch (typeResponse) {
                    case Configuration.RESPONSE_LEAGUE_TABLE:
                        List<TeamEntity> listOfTeams = Util.parseResponseStanding(response.body().string());
                        for (TeamEntity entity : listOfTeams) {
                            if (footballRoomDatabase.standingDAO().getTeamFromDatabase(entity.getTeamId()) != null) {
                                footballRoomDatabase.standingDAO().update(entity);
                            } else {
                                footballRoomDatabase.standingDAO().insert(entity);
                            }
                        }
                        NetUtil.sendGetRequest(activity, Configuration.GET_LEAGUE, Configuration.RESPONSE_LEAGUE);
                        break;
                    case 2:
                        List<TeamEntity> list = Util.parseResponseTeamsList(response.body().string());
                        for (TeamEntity entity : list) {
                            int teamId = (int) Double.parseDouble(entity.getTeamId());
                            TeamEntity teamEntity = footballRoomDatabase.standingDAO().getTeamFromDatabase(String.valueOf(teamId));
                            teamEntity.setLogoTeam(entity.getLogoTeam());
                            footballRoomDatabase.standingDAO().update(teamEntity);
                        }
                        break;
                    case 3:
                        List<PlayerEntity> playerEntities = Util.parseResponsePlayersList(response.body().string());
                        for (PlayerEntity entity : playerEntities) {
                            if (footballRoomDatabase.playerDAO().getPlayerFromDatabase(entity.getPlayerId()) != null) {
                                footballRoomDatabase.playerDAO().update(entity);
                            } else {
                                footballRoomDatabase.playerDAO().insert(entity);
                            }
                        }
                        ((BaseActivity) activity).getPlayerFromDatabase(footballRoomDatabase);
                }
            }
        });
        return null;
    }

}
