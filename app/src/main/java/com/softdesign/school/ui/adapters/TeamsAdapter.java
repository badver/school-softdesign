package com.softdesign.school.ui.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamViewHolder> {

    private List<Team> mTeams;
    private View mView;

    public TeamsAdapter(List<Team> teams) {
        mTeams = teams;
    }


    @Override
    public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_card_item, parent, false);
        return new TeamViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(TeamViewHolder holder, int position) {
        Team team = mTeams.get(position);
        holder.setTeamName(team.getName());
    }

    @Override
    public int getItemCount() {
        return mTeams.size();
    }
}
