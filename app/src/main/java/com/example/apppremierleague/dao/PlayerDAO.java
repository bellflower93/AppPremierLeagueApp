package com.example.apppremierleague.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.apppremierleague.entities.PlayerEntity;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Insert()
    void insert(PlayerEntity playerEntity);

    @Update()
    void update(PlayerEntity playerEntity);

    @Query("Select * FROM player_table where playerId = :playerId")
    PlayerEntity getPlayerFromDatabase(Double playerId);

    @Query("Select * FROM player_table where teamId = :teamId ORDER BY position = 'Attacker', position = 'Midfielder', position = 'Defender', position = 'Goalkeeper' ")
    List<PlayerEntity> getPlayerFromDatabaseByTeamId(Double teamId);

    @Query("Select * FROM player_table where teamId = :teamId AND playerId <> :playerId AND position<>'Goalkeeper'")
    List<PlayerEntity> getPlayerFromDatabaseWithoutGoalKeeper(Double teamId, Double playerId);
}
