package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.softdesign.school.R;

public class TeamViewHolder extends RecyclerView.ViewHolder {

    private TextView mTeamName;

    public TeamViewHolder(View itemView) {
        super(itemView);
        mTeamName = (TextView) itemView.findViewById(R.id.team_name);
    }

    public TextView getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName.setText(teamName);
    }
}
