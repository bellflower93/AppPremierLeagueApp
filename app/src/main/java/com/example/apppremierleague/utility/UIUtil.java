package com.example.apppremierleague.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.apppremierleague.R;
import com.example.apppremierleague.db.FootballRoomDatabase;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.view.BaseActivity;
import com.example.apppremierleague.view.CompareTwoPlayersActivity;

import java.util.List;

public class UIUtil {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";

    public static Typeface getTypeface(Context context, String font) {
        return Typeface.createFromAsset(context.getAssets(), font);
    }

    public static void showAlertDialog(Activity activity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        alertDialog.setTitle(activity.getResources().getString(R.string.sort));

        String[] items = {activity.getResources().getString(R.string.name), activity.getResources().getString(R.string.point), activity.getResources().getString(R.string.rank), activity.getResources().getString(R.string.wins), activity.getResources().getString(R.string.lose), activity.getResources().getString(R.string.match)};
        int checkedItem = -1;
        FootballRoomDatabase footballRoomDatabase = FootballRoomDatabase.getDatabase(activity);
        alertDialog.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByName());
                        dialog.dismiss();
                        break;
                    case 1:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByPoints());
                        dialog.dismiss();
                        break;
                    case 2:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByRank());
                        dialog.dismiss();
                        break;
                    case 3:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByWin());
                        dialog.dismiss();
                        break;
                    case 4:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByLose());
                        dialog.dismiss();
                        break;
                    case 5:
                        ((BaseActivity) activity).sortBy(footballRoomDatabase.standingDAO().getSortByMatch());
                        dialog.dismiss();
                        break;
                }

            }
        });

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
        alert.getWindow().setBackgroundDrawableResource(R.color.soccer_green);

    }

    public static void showDetailsOfPlayer(Activity activity, PlayerEntity playerEntity) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity, R.style.AlertDialogCustom);
        alertDialog.setTitle(playerEntity.getPlayerName() + " - " + playerEntity.getPosition());
        String message = playerEntity.getFirstName() + " " + playerEntity.getLastName() + "\n" + playerEntity.getTeamName()
                + "\n" + activity.getResources().getString(R.string.red_card) + " " + playerEntity.getRedCard().intValue()
                + "\n" + activity.getResources().getString(R.string.yellow_card) + " "
                + playerEntity.getYellowCard().intValue();
        if (!playerEntity.getPosition().equalsIgnoreCase("Goalkeeper")) {
            LayoutInflater li = LayoutInflater.from(activity);
            View promptsView = li.inflate(R.layout.dialog_player_details, null);
            alertDialog.setView(promptsView);

            final Spinner mSpinner = promptsView.findViewById(R.id.spinner);
            final TextView okButton = promptsView.findViewById(R.id.ok_button);
            final TextView details = promptsView.findViewById(R.id.details);
            final TextView arrow = promptsView.findViewById(R.id.arrow_down);

            arrow.setTypeface(UIUtil.getTypeface(activity, UIUtil.FONTAWESOME));
            details.setText(message);
            FootballRoomDatabase footballRoomDatabase = FootballRoomDatabase.getDatabase(activity);
            List<PlayerEntity> list = footballRoomDatabase.playerDAO().getPlayerFromDatabaseWithoutGoalKeeper(playerEntity.getTeamId(), playerEntity.getPlayerId());

            ArrayAdapter<PlayerEntity> adapter = new ArrayAdapter<>(activity, R.layout.spinner_item, list);
            mSpinner.setAdapter(adapter);

            okButton.setTypeface(UIUtil.getTypeface(activity, UIUtil.FONTAWESOME));
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Configuration.BUNDLE_KEY_PLAYER_1, playerEntity);
                    bundle.putSerializable(Configuration.BUNDLE_KEY_PLAYER_2, (PlayerEntity) mSpinner.getSelectedItem());
                    Util.openNewActivity(activity, CompareTwoPlayersActivity.class, bundle);
                }
            });
        } else {
            alertDialog.setMessage(message);
        }
        AlertDialog alert = alertDialog.create();
        alert.show();
        alert.getWindow().setBackgroundDrawableResource(R.color.soccer_green);
    }

    public static void setPlayerInfo(Context context, TextView mainInfo, TextView detailsInfo, PlayerEntity playerEntity) {
        mainInfo.setText(playerEntity.getFirstName() + " " + playerEntity.getLastName());
        mainInfo.append("\n" + playerEntity.getAge().intValue());
        mainInfo.append("\n" + playerEntity.getPosition());
        detailsInfo.setText(context.getResources().getString(R.string.matches) + " " + playerEntity.getNumGames().intValue());
        detailsInfo.append("\n" + context.getResources().getString(R.string.red_card) + " " + playerEntity.getRedCard().intValue());
        detailsInfo.append("\n" + context.getResources().getString(R.string.yellow_card) + " " + playerEntity.getYellowCard().intValue());

        switch (playerEntity.getPosition()) {
            case Configuration.ATTACKER:
                detailsInfo.append("\n" + context.getResources().getString(R.string.goals) + " " + playerEntity.getGoal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.assists) + " " + playerEntity.getAssists().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.shot_total) + " " + playerEntity.getShotTotal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.shot_on) + " " + playerEntity.getShotOn().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.dribble_success) + " " + playerEntity.getDribbleSuccess().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.dribble_total) + " " + playerEntity.getDribbleTotal().intValue());
                break;
            case Configuration.MIDFIELDER:
                detailsInfo.append("\n" + context.getResources().getString(R.string.goals) + " " + playerEntity.getGoal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.assists) + " " + playerEntity.getAssists().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.passes_total) + " " + playerEntity.getPassesTotal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.passes_key) + " " + playerEntity.getPassesKey().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.dribble_success) + " " + playerEntity.getDribbleSuccess().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.dribble_total) + " " + playerEntity.getDribbleTotal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.fouls_committed) + " " + playerEntity.getFoulsCommitted().intValue());
                break;
            case Configuration.DEFENDER:
                detailsInfo.append("\n" + context.getResources().getString(R.string.tackles_total) + " " + playerEntity.getTacklesTotal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.blocks) + " " + playerEntity.getBlocks().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.interceptions) + " " + playerEntity.getInterceptions().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.duel_total) + " " + playerEntity.getDuelTotal().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.duel_won) + " " + playerEntity.getDuelWon().intValue());
                detailsInfo.append("\n" + context.getResources().getString(R.string.fouls_committed) + " " + playerEntity.getFoulsCommitted().intValue());
                break;
        }
    }


}

