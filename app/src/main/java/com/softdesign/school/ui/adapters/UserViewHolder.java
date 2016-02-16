package com.softdesign.school.ui.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView mFullname;
    private ImageView mAvatar;
    private TextView mTeam;

    public UserViewHolder(View itemView) {
        super(itemView);
        mAvatar = (ImageView) itemView.findViewById(R.id.user_card_avatar);
        mFullname = (TextView) itemView.findViewById(R.id.user_card_fullname);
        mTeam = (TextView) itemView.findViewById(R.id.user_card_team);
    }

    public void setTeam(String team) {
        mTeam.setText(team);
    }

    public void setFullname(String fullname) {
        mFullname.setText(fullname);
    }

    public void setAvatar(Drawable avatar) {
        mAvatar.setImageDrawable(avatar);
    }
}
