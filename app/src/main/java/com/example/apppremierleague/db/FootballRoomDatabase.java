package com.example.apppremierleague.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.apppremierleague.dao.PlayerDAO;
import com.example.apppremierleague.dao.TeamDAO;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.utility.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TeamEntity.class, PlayerEntity.class}, version = 1, exportSchema = false)
public abstract class FootballRoomDatabase extends RoomDatabase {

    public abstract TeamDAO standingDAO();
    public abstract PlayerDAO playerDAO();

    public static volatile FootballRoomDatabase INSTANCE;

    public static FootballRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FootballRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FootballRoomDatabase.class, Configuration.FOOTBALL_DATABASE)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
