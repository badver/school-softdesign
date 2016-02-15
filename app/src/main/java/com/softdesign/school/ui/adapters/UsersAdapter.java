package com.softdesign.school.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> mUsers;
    private View mView;

    public UsersAdapter(List<User> users) {
        mUsers = users;
    }

    @Override

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card_item, parent, false);
        return new UserViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.setFullname(user.mFirstName + " " + user.mLastName);
        holder.setAvatar(mView.getResources().getDrawable(Integer.parseInt(user.mImage)));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
