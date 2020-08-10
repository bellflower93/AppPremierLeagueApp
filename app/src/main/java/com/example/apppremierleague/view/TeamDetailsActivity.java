package com.example.apppremierleague.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.apppremierleague.R;
import com.example.apppremierleague.adapters.AdapterForPlayersList;
import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.interfaces.OnItemClickListener;
import com.example.apppremierleague.utility.Configuration;
import com.example.apppremierleague.utility.NetUtil;
import com.example.apppremierleague.utility.UIUtil;
import com.example.apppremierleague.utility.Util;

import java.util.List;

public class TeamDetailsActivity extends BaseActivity {

    TeamEntity teamEntity;
    TextView nameOfTeam, txtBack;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    AdapterForPlayersList adapterForPlayersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        getSupportActionBar().hide();

        setUpViews();
        clicksOnView();
        getBundleData();
        getListOfPlayersForTeam();
    }

    public void setUpViews() {
        nameOfTeam = findViewById(R.id.name_of_team);
        txtBack = findViewById(R.id.back_button);
        txtBack.setTypeface(UIUtil.getTypeface(this, UIUtil.FONTAWESOME));
        recyclerView = findViewById(R.id.list_of_players);
        progressBar = findViewById(R.id.progress);
        adapterForPlayersList = new AdapterForPlayersList(this);
        recyclerView.setAdapter(adapterForPlayersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void clicksOnView() {
        adapterForPlayersList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                UIUtil.showDetailsOfPlayer(TeamDetailsActivity.this, adapterForPlayersList.getListPlayers().get(position));
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getBundleData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            teamEntity = (TeamEntity) bundle.getSerializable(Configuration.BUNDLE_KEY_TEAM);
            if (teamEntity != null) {
                nameOfTeam.setText(teamEntity.getTeamName());
            }
        }
    }

    public void getListOfPlayersForTeam() {
        if (teamEntity != null) {
            NetUtil.sendGetRequest(this, Configuration.GET_PLAYERS_OF_TEAM + teamEntity.getTeamId(), Configuration.RESPONSE_TEAM_PLAYERS);
        }

    }

    @Override
    public void getPlayerFromDatabase(FootballRoomDatabase footballRoomDatabase) {
        adapterForPlayersList.setListOfPlayers(footballRoomDatabase.playerDAO().getPlayerFromDatabaseByTeamId(Double.parseDouble(teamEntity.getTeamId())));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapterForPlayersList.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }
        });

    }
}