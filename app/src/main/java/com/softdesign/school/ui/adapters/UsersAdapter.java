package com.softdesign.school.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context mContext;
    private ArrayList<User> mUsers;
    private LayoutInflater mInflater;


    public UsersAdapter(ArrayList<User> users) {
        mUsers = users;
    }

    @Override

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View temp = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card_item, parent, false);
        return new UserViewHolder(temp);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.setFullname(user.getFirstName() + " " + user.getLastName());
        holder.setAvatar(user.getImage());
    }


    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
