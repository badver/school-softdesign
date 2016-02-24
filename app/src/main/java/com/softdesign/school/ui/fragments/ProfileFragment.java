package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View temp = inflater.inflate(R.layout.fragment_profile, null, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_profile));
        ((MainActivity) getActivity()).lockAppBar(false);
        return temp;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton actionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) actionButton.getLayoutParams();
        params.setAnchorId(R.id.app_bar_layout);
        params.anchorGravity= Gravity.RIGHT|Gravity.BOTTOM;
        actionButton.setImageResource(R.drawable.ic_mode_edit_24dp);
    }
}
