package com.example.apppremierleague.view;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apppremierleague.R;
import com.example.apppremierleague.adapters.AdapterPremierLeague;
import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.interfaces.OnItemClickListener;
import com.example.apppremierleague.utility.Configuration;
import com.example.apppremierleague.utility.NetUtil;
import com.example.apppremierleague.utility.UIUtil;
import com.example.apppremierleague.utility.Util;
import com.example.apppremierleague.viewmodel.StandingViewModel;

import java.util.ArrayList;
import java.util.List;

public class PremierLeagueActivity extends BaseActivity  {

    RecyclerView recyclerView;
    TextView txtSort;
    AdapterPremierLeague adapterPremierLeague;
    private StandingViewModel standingViewModel;
    final String TAG = "PremierLeagueActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premier_league);
        getSupportActionBar().hide();

        setUpViews();
        setUpListeners();

        getListOfTeamsInLeague();
    }

    private void setUpViews() {
        recyclerView = findViewById(R.id.list_of_teams);
        adapterPremierLeague = new AdapterPremierLeague(this);
        recyclerView.setAdapter(adapterPremierLeague);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtSort = findViewById(R.id.sort_button);
        txtSort.setTypeface(UIUtil.getTypeface(this,UIUtil.FONTAWESOME));
    }

    public void setUpListeners() {
        txtSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtil.showAlertDialog(PremierLeagueActivity.this);
            }
        });
        adapterPremierLeague.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(Configuration.BUNDLE_KEY_TEAM, adapterPremierLeague.getListOfTeams().get(position));
                Util.openNewActivity(PremierLeagueActivity.this, TeamDetailsActivity.class, bundle);
            }
        });
    }

    private void getListOfTeamsInLeague() {
        NetUtil.sendGetRequest(PremierLeagueActivity.this, Configuration.GET_LEAGUE_TABLE,Configuration.RESPONSE_LEAGUE_TABLE);
        standingViewModel = new ViewModelProvider(this).get(StandingViewModel.class);
        standingViewModel.getAllStandings().observe(this, new Observer<List<TeamEntity>>() {
            @Override
            public void onChanged(@Nullable final List<TeamEntity> standingEntities) {
                if (adapterPremierLeague == null) {
                    adapterPremierLeague = new AdapterPremierLeague(PremierLeagueActivity.this);
                }
                adapterPremierLeague.setListOfTeams(standingEntities);
            }
        });
    }
    @Override
    public void sortBy(List<TeamEntity> listOfStandings) {
        adapterPremierLeague.setListOfTeams(listOfStandings);
    }


}