package com.example.apppremierleague.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.apppremierleague.R;
import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.utility.Configuration;
import com.example.apppremierleague.utility.UIUtil;
import com.example.apppremierleague.utility.Util;

public class CompareTwoPlayersActivity extends AppCompatActivity {

    TextView txtMainInfoPlayer1, txtDetailsInfoPlayer1, txtPoints1, txtMainInfoPlayer2, txtDetailsInfoPlayer2, txtPoints2, txtBack;
    LinearLayout parentLayoutForPlayer1, parentLayoutForPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_two_players);
        getSupportActionBar().hide();

        setUpViews();
        clicksOnViews();
        loadData();
    }

    public void setUpViews() {
        txtMainInfoPlayer1 = findViewById(R.id.main_info_player_1);
        txtDetailsInfoPlayer1 = findViewById(R.id.detail_info_player_1);
        txtPoints1 = findViewById(R.id.points_player_1);
        txtMainInfoPlayer2 = findViewById(R.id.main_info_player_2);
        txtDetailsInfoPlayer2 = findViewById(R.id.detail_info_player_2);
        txtPoints2 = findViewById(R.id.points_player_2);
        parentLayoutForPlayer1 = findViewById(R.id.parent_player1);
        parentLayoutForPlayer2 = findViewById(R.id.parent_player2);
        txtBack = findViewById(R.id.back_button);
        txtBack.setTypeface(UIUtil.getTypeface(this, UIUtil.FONTAWESOME));
    }

    public void clicksOnViews() {
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void loadData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            PlayerEntity player1 = (PlayerEntity) bundle.get(Configuration.BUNDLE_KEY_PLAYER_1);
            PlayerEntity player2 = (PlayerEntity) bundle.get(Configuration.BUNDLE_KEY_PLAYER_2);
            Double points1 = Util.sumPoints(player1), points2 = Util.sumPoints(player2);

            UIUtil.setPlayerInfo(this, txtMainInfoPlayer1, txtDetailsInfoPlayer1, player1);
            UIUtil.setPlayerInfo(this, txtMainInfoPlayer2, txtDetailsInfoPlayer2, player2);

            txtPoints1.setText(points1.toString());
            txtPoints2.setText(points2.toString());
            if (points1 > points2) {
                parentLayoutForPlayer1.setBackgroundColor(getResources().getColor(R.color.green_win));
                parentLayoutForPlayer2.setBackgroundColor(getResources().getColor(R.color.red));
            } else {
                parentLayoutForPlayer1.setBackgroundColor(getResources().getColor(R.color.red));
                parentLayoutForPlayer2.setBackgroundColor(getResources().getColor(R.color.green_win));

            }
        }
    }
}