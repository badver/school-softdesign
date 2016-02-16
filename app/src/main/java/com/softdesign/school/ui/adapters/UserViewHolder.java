package com.softdesign.school.ui.adapters;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView mFullname;
    private ImageView mAvatar;
    private TextView mTeam;
    private User mUser;

    public UserViewHolder(View itemView) {
        super(itemView);
        mAvatar = (ImageView) itemView.findViewById(R.id.user_card_avatar);
        mFullname = (TextView) itemView.findViewById(R.id.user_card_fullname);
        mTeam = (TextView) itemView.findViewById(R.id.user_card_team);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
        mFullname.setText(mUser.getFirstName() + " " + mUser.getLastName());

        if (user.getTeam() != null) {
            mTeam.setText(mUser.getTeam().getName());
        } else {
            mTeam.setText("Нет команды");
        }
    }

    public void setAvatar(Drawable avatar) {
        mAvatar.setImageDrawable(avatar);
    }
}
