package com.example.apppremierleague.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apppremierleague.R;
import com.example.apppremierleague.entities.PlayerEntity;
import com.example.apppremierleague.interfaces.OnItemClickListener;

import java.util.List;

public class AdapterForPlayersList extends RecyclerView.Adapter<AdapterForPlayersList.PlayersViewHolder> {

    Context context;
    List<PlayerEntity> listPlayerEntities;
    OnItemClickListener onItemClickListener;

    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtPlayerName, txtPosition;

        private PlayersViewHolder(View itemView) {
            super(itemView);
            txtPlayerName = itemView.findViewById(R.id.player_name);
            txtPosition = itemView.findViewById(R.id.position);
        }

    }

    public AdapterForPlayersList(Context context) {
        this.context = context;
    }

    @Override
    public PlayersViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_for_player_list, parent, false);
        PlayersViewHolder playersViewHolder = new PlayersViewHolder(v);
        return playersViewHolder;
    }

    @Override
    public void onBindViewHolder(PlayersViewHolder holder, int position) {
        PlayerEntity playerEntity = listPlayerEntities.get(position);
        holder.txtPosition.setText(playerEntity.getPosition());
        holder.txtPlayerName.setText(playerEntity.getPlayerName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listPlayerEntities != null) {
            return listPlayerEntities.size();
        } else {
            return 0;
        }
    }

    public List<PlayerEntity> getListPlayers() {
        return listPlayerEntities;
    }

    public void setListOfPlayers(List<PlayerEntity> listOfPlayers) {
        listPlayerEntities = listOfPlayers;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}