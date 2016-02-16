package com.softdesign.school.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.Team;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adapters.UsersAdapter;

public class ContactsFragment extends Fragment {

    private EditText firstName;
    private EditText lastName;
    private Spinner spinner;

    public ContactsFragment() {
        super();
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

        RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.user_recycle_view);
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
                                        // TODO
                                        new User(firstName.getText().toString(), lastName.getText().toString(), Team.getByName(spinner.getSelectedItem().toString())).save();
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

                firstName = (EditText) alertDialog.findViewById(R.id.add_user_et_firstname_value);
                lastName = (EditText) alertDialog.findViewById(R.id.add_user_et_lastname_value);

                // fill spinner
                spinner = (Spinner) alertDialog.findViewById(R.id.team_spinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Team.getAllNames());
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // TODO
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // TODO
                    }
                });
            }
        });

        activity.lockAppBar(true);
    }
}
