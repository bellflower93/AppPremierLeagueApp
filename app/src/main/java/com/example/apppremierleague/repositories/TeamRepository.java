package com.example.apppremierleague.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.apppremierleague.dao.TeamDAO;
import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.TeamEntity;

import java.util.List;

public class TeamRepository {

    private TeamDAO teamDAO;
    private LiveData<List<TeamEntity>> allTeams;

    public TeamRepository(Application application) {
        FootballRoomDatabase db = FootballRoomDatabase.getDatabase(application);
        teamDAO = db.standingDAO();
        allTeams = teamDAO.getAll();
    }

    public LiveData<List<TeamEntity>> getAll() {
        return allTeams;
    }

}

