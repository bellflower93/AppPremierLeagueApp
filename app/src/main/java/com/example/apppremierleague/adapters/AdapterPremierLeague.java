package com.example.apppremierleague.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apppremierleague.R;
import com.example.apppremierleague.entities.TeamEntity;
import com.example.apppremierleague.interfaces.OnItemClickListener;
import com.example.apppremierleague.utility.UIUtil;

import java.util.List;

public class AdapterPremierLeague extends RecyclerView.Adapter<AdapterPremierLeague.PremierLeagueViewHolder> {

    Context context;
    List<TeamEntity> listOfTeams;
    OnItemClickListener onItemClickListener;
    Typeface typeface;

    public class PremierLeagueViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgTeamLogo;
        private final TextView txtTeamName, txtMatches, txtPoints, txtRank, txtWins, txtLose;

        private PremierLeagueViewHolder(View itemView) {
            super(itemView);
            imgTeamLogo = itemView.findViewById(R.id.image_of_team);
            txtTeamName = itemView.findViewById(R.id.name_of_team);
            txtMatches = itemView.findViewById(R.id.matchs);
            txtPoints = itemView.findViewById(R.id.points);
            txtRank = itemView.findViewById(R.id.rank);
            txtWins = itemView.findViewById(R.id.wins);
            txtLose = itemView.findViewById(R.id.lose);

        }

    }

    public AdapterPremierLeague(Context context) {
        this.context = context;
        typeface = UIUtil.getTypeface(context, UIUtil.FONTAWESOME);
    }

    @Override
    public AdapterPremierLeague.PremierLeagueViewHolder onCreateViewHolder(ViewGroup parent,
                                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_standing_list, parent, false);
        PremierLeagueViewHolder vh = new PremierLeagueViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PremierLeagueViewHolder holder, int position) {

        TeamEntity teamEntity = listOfTeams.get(position);
        Glide.with(context).asBitmap().load(teamEntity.getLogoTeam()).
                placeholder(context.getResources().getDrawable(R.drawable.ic_launcher_foreground)).into(holder.imgTeamLogo);
        holder.txtRank.setText(teamEntity.getRank());
        holder.txtPoints.setText(context.getResources().getString(R.string.points) + " " + teamEntity.getPoints());
        holder.txtMatches.setText(context.getResources().getString(R.string.matches) + " " + teamEntity.getNumOfMatch());
        holder.txtTeamName.setText(teamEntity.getTeamName());
        holder.txtWins.setText(context.getResources().getString(R.string.fa_icon_check));
        holder.txtWins.append(" " + teamEntity.getWin());
        holder.txtWins.setTypeface(typeface);
        holder.txtLose.setText(context.getResources().getString(R.string.fa_icon_times));
        holder.txtLose.append(" " + teamEntity.getLose());
        holder.txtLose.setTypeface(typeface);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listOfTeams != null) {
            return listOfTeams.size();
        } else {
            return 0;
        }
    }

    public List<TeamEntity> getListOfTeams() {
        return listOfTeams;
    }

    public void setListOfTeams(List<TeamEntity> teamEntityList) {
        listOfTeams = teamEntityList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
