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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.UserViewHolder;
import com.softdesign.school.ui.adapters.UsersAdapter;

import java.util.List;

public class ContactsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<User>> {

    private EditText mFirstName;
    private EditText mLastName;
    private Spinner mSpinner;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;

    public ContactsFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getLoaderManager().initLoader(0, savedInstanceState, this);
        return inflater.inflate(R.layout.fragment_contacts, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        MainActivity activity = (MainActivity) getActivity();

        mRecyclerView = (RecyclerView) activity.findViewById(R.id.user_recycle_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new UsersAdapter(User.getAll());
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
                builder.setTitle("Добавить пользователя")
                        .setCancelable(false)
                        .setPositiveButton("Готово",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String firstname = mFirstName.getText().toString();
                                        String lastname = mLastName.getText().toString();
                                        Team team = null;
                                        if (mSpinner.getSelectedItem() != null) {
                                            team = Team.getByName(mSpinner.getSelectedItem().toString());
                                        }
                                        new User(firstname, lastname, team).save();
                                        getLoaderManager().getLoader(0).forceLoad();
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setView(R.layout.dialog_add_user);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                mFirstName = (EditText) alertDialog.findViewById(R.id.add_user_et_firstname_value);
                mLastName = (EditText) alertDialog.findViewById(R.id.add_user_et_lastname_value);

                // fill mSpinner
                mSpinner = (Spinner) alertDialog.findViewById(R.id.team_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, Team.getAllNames());
                mSpinner.setAdapter(adapter);
            }
        });

        activity.lockAppBar(true);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        ((UserViewHolder) viewHolder).getUser().delete();
                        getLoaderManager().getLoader(0).forceLoad();
                    }
                });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public Loader<List<User>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<User>>(getContext()) {
            @Override
            public List<User> loadInBackground() {
                return User.getAll();
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
        mAdapter = new UsersAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<User>> loader) {

    }
}
