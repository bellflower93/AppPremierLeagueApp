package com.example.apppremierleague.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.repositories.TeamRepository;

import java.util.List;

public class StandingViewModel extends AndroidViewModel {

    private TeamRepository teamRepository;
    private LiveData<List<TeamEntity>> allTeams;

    public StandingViewModel (Application application) {
        super(application);
        teamRepository = new TeamRepository(application);
        allTeams = teamRepository.getAll();
    }

    public  LiveData<List<TeamEntity>> getAllStandings() { return allTeams; }



}
