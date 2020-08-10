package com.example.apppremierleague.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.apppremierleague.R;
import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.entities.TeamEntity;

import java.util.List;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }


    public void sortBy(List<TeamEntity> standingEntities) {};

    public void getPlayerFromDatabase(FootballRoomDatabase footballRoomDatabase) {};

}