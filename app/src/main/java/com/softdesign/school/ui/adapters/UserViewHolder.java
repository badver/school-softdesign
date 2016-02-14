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

    public UserViewHolder(View itemView) {
        super(itemView);

        mFullname = (TextView) itemView.findViewById(R.id.card_fullname);
        mAvatar = (ImageView) itemView.findViewById(R.id.card_avatar);
    }

    public TextView getFullname() {
        return mFullname;
    }

    public void setFullname(String fullname) {
        mFullname.setText(fullname);
    }

    public ImageView getAvatar() {
        return mAvatar;
    }

    public void setAvatar(Drawable avatar) {
        mAvatar.setImageDrawable(avatar);
    }
}
