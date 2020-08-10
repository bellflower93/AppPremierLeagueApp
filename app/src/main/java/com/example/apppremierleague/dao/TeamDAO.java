package com.example.apppremierleague.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apppremierleague.entities.TeamEntity;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert()
    void insert(TeamEntity teamEntity);

    @Query("Select * FROM team_table")
    LiveData<List<TeamEntity>> getAll();

    @Query("Select * FROM team_table where teamId = :teamId")
    TeamEntity getTeamFromDatabase(String teamId);

    @Update()
    void update(TeamEntity teamEntity);

    @Query("SELECT * from team_table ORDER BY teamName ASC")
    List<TeamEntity> getSortByName();

    @Query("SELECT * from team_table ORDER BY CAST(points AS INTEGER) ASC")
    List<TeamEntity> getSortByPoints();

    @Query("SELECT * from team_table ORDER BY CAST(numOfMatch AS INTEGER) ASC")
    List<TeamEntity> getSortByMatch();

    @Query("SELECT * from team_table ORDER BY CAST(win AS INTEGER) ASC")
    List<TeamEntity> getSortByWin();

    @Query("SELECT * from team_table ORDER BY CAST(lose AS INTEGER) ASC")
    List<TeamEntity> getSortByLose();

    @Query("SELECT * from team_table ORDER BY CAST(rank AS INTEGER) ASC")
    List<TeamEntity> getSortByRank();
}
