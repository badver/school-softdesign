package com.softdesign.school.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.TeamViewHolder;
import com.softdesign.school.ui.adapters.TeamsAdapter;

import java.util.List;

public class TeamFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Team>> {
    private View mView;
    private EditText mEditText;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_team, null, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_team));
        ((MainActivity) getActivity()).lockAppBar(true);
        getLoaderManager().initLoader(0, savedInstanceState, this);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        mRecyclerView = (RecyclerView) activity.findViewById(R.id.team_recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new TeamsAdapter(Team.getAll());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        FloatingActionButton actionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) actionButton.getLayoutParams();
        params.setAnchorId(R.id.coordinator);
        params.anchorGravity = Gravity.RIGHT | Gravity.BOTTOM;
        actionButton.setImageResource(R.drawable.ic_add_24dp);
        actionButton.setLayoutParams(params);
        actionButton.show();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // build dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Добавить команду")
                        .setCancelable(false)
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String name = mEditText.getText().toString();
                                        Team exist = Team.getByName(name);
                                        if (exist == null && !"".equals(name)) {
                                            new Team(name).save();
                                            getLoaderManager().getLoader(0).forceLoad();
                                            dialog.cancel();
                                        } else {
                                            Toast.makeText(getActivity(), "Team with this name already exists!", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                })
                        .setView(R.layout.dialog_add_team);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                mEditText = (EditText) alertDialog.findViewById(R.id.add_team_et_name_value);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        // TODO: 26.02.16 check if user with this team is exist and show warning
                        ((TeamViewHolder) viewHolder).getTeam().delete();
                        getLoaderManager().getLoader(0).forceLoad();
                    }
                });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public Loader<List<Team>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<Team>>(getContext()) {
            @Override
            public List<Team> loadInBackground() {
                return Team.getAll();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Team>> loader, List<Team> data) {
        mAdapter = new TeamsAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Team>> loader) {

    }
}
