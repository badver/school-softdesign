package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;

public class TeamViewHolder extends RecyclerView.ViewHolder {

    private TextView mTeamName;
    private Team mTeam;

    public TeamViewHolder(View itemView) {
        super(itemView);
        mTeamName = (TextView) itemView.findViewById(R.id.team_name);
    }

    public Team getTeam() {
        return mTeam;
    }

    public void setTeam(Team team) {
        mTeam = team;
        mTeamName.setText(getTeamName());
    }

    public String getTeamName() {
        return mTeam.getName();

    }
}
