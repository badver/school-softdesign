package com.softdesign.school.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.preferences.UserPreferences;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.utils.ViewMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {

    private static final ButterKnife.Action<View> INVISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.GONE);
        }
    };
    private static final ButterKnife.Action<View> VISIBLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(View view, int index) {
            view.setVisibility(View.VISIBLE);
        }
    };


    @Bind({R.id.txt_phone_value, R.id.txt_email_value, R.id.txt_vk_value, R.id.txt_github_value, R.id.txt_about_value})
    List<TextView> txtViewsValues;
    @Bind({R.id.txt_phone_label, R.id.txt_email_label, R.id.txt_vk_label, R.id.txt_github_label, R.id.txt_about_label})
    List<TextView> txtViewsLabels;
    @Bind({R.id.et_phone_wrapper, R.id.et_email_wrapper, R.id.et_vk_wrapper, R.id.et_git_wrapper, R.id.et_bio_wrapper})
    List<TextInputLayout> etViewsWrappers;
    @Bind({R.id.et_phone_value, R.id.et_email_value, R.id.et_vk_value, R.id.et_git_value, R.id.et_bio_value})
    List<EditText> etViewsValue;
    UserPreferences userFields;
    private List<String> mUserProfileData;
    private View mainView = null;
    private ViewMode mCurrentFunctionality = ViewMode.VIEW;


    public ProfileFragment() {
        this.setRetainInstance(true); // непересоздавать фрагмент при повороте экрана
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userFields = new UserPreferences();
        mUserProfileData = userFields.loadUserProfileData(); // получаем данные из локальной модели.
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainView == null) {
            // Если представления нет, создаем его*/
            mainView = inflater.inflate(R.layout.fragment_profile, container, false);
            ButterKnife.bind(this, mainView);
        }

        getActivity().setTitle(getResources().getString(R.string.drawer_profile));
        ((MainActivity) getActivity()).lockAppBar(false);

        setFieldsData(txtViewsValues, mUserProfileData); //заполняем View элементы данными
        setFieldsData(etViewsValue, mUserProfileData);
        setupFuncionality(mCurrentFunctionality); //выставляем текущую функциональность

        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FloatingActionButton actionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) actionButton.getLayoutParams();
        params.setAnchorId(R.id.app_bar_layout);
        params.anchorGravity = Gravity.RIGHT | Gravity.BOTTOM;
        actionButton.setImageResource(R.drawable.ic_mode_edit_24dp);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                     //создаем и вешаем новый обработчик на fab
                if (mCurrentFunctionality.equals(ViewMode.VIEW)) {  //выбираем действие для fab в зависимости от текущего режима
                    setupFuncionality(ViewMode.EDIT);
                } else {
                    setupFuncionality(ViewMode.VIEW);
                }
            }
        });
    }

    private void setFieldsData(List<? extends TextView> viewList, List<String> userFields) {
        for (int i = 0; i < viewList.size(); ++i) {
            viewList.get(i).setText(userFields.get(i));
        }
    }

    private void setupFuncionality(ViewMode Funcionality) {
        mCurrentFunctionality = Funcionality; // выставляем текущую функциональность в зависимости от переданного аргумента

        switch (Funcionality) {
            case VIEW: //режим просмотра
                List<String> ScreenData = getFieldsData(etViewsValue);
                if (!mUserProfileData.equals(ScreenData)) {  // если данные в EditView не совпадают с данными из модели UserPreferenses
                    userFields.saveUserProfileData(ScreenData); //то сохранить новые данные в модели
                    mUserProfileData = ScreenData; // заменить текущие данные в фрагменте
                    setFieldsData(txtViewsValues, mUserProfileData); // заполнить поля TextView
                }

                ButterKnife.apply(etViewsWrappers, INVISIBLE); //скрыть EditText
                ButterKnife.apply(txtViewsValues, VISIBLE); // показать TextView
                ButterKnife.apply(txtViewsLabels, VISIBLE);

                break;
            case EDIT: //режим редактирования
                ButterKnife.apply(etViewsWrappers, VISIBLE); //показать EditText
                ButterKnife.apply(txtViewsValues, INVISIBLE); //скрыть TextView
                ButterKnife.apply(txtViewsLabels, INVISIBLE);
                break;
            default:
                mCurrentFunctionality = ViewMode.VIEW;
                ButterKnife.apply(etViewsWrappers, INVISIBLE);
                ButterKnife.apply(txtViewsValues, VISIBLE);
                ButterKnife.apply(txtViewsLabels, VISIBLE);
                break;
        }
    }

    private List<String> getFieldsData(List<? extends TextView> viewList) {
        List<String> userFields = new ArrayList<String>();
        for (TextView viewField : viewList) {
            userFields.add(viewField.getText().toString());
        }
        return userFields;
    }
}
