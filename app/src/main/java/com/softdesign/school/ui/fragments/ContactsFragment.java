package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.ActiveAndroid;
import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.UsersAdapter;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private ArrayList<User> mUsers;

    public ContactsFragment() {
        super();
        mUsers = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        MainActivity activity = (MainActivity) getActivity();

        generateData();

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new UsersAdapter(User.getAll());
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        FloatingActionButton actionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) actionButton.getLayoutParams();
        params.setAnchorId(R.id.coordinator);
        params.anchorGravity = Gravity.RIGHT | Gravity.BOTTOM;
        actionButton.setImageResource(R.drawable.ic_add_24dp);
        actionButton.setLayoutParams(params);
        actionButton.show();

        activity.lockAppBar(true);
    }

    private void generateData() {
        new User("Morlee", "Andreatta", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Kaye", "Santo", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Cly", "Dasse", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Ianthe", "Packer", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Cleve", "Arnold", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Marylee", "Pliego", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Dory", "Sen", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Kikelia", "Huber", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("John", "Maugham", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Perceval", "Reynolds", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Billie", "Hubsch", String.valueOf(R.drawable.ic_face_24dp)).save();
        new User("Pedro", "Chuang", String.valueOf(R.drawable.ic_face_24dp)).save();
    }
}
