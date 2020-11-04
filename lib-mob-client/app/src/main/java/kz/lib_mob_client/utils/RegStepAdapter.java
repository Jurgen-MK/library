package kz.lib_mob_client.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import kz.lib_mob_client.fragments.RegStepOneFragment;
import kz.lib_mob_client.fragments.RegStepThreeFragment;
import kz.lib_mob_client.fragments.RegStepTwoFragment;

public class RegStepAdapter extends AbstractFragmentStepAdapter {
    private static final String CURRENT_STEP_POSITION_KEY = "messageResourceId";

    public RegStepAdapter(@NonNull FragmentManager fm, @NonNull Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        switch (position) {
            case 0 : {
                final RegStepOneFragment step1 = new RegStepOneFragment();
//                Bundle b1 = new Bundle();
//                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
//                step1.setArguments(b1);
                return step1;
            }
            case 1 : {
                final RegStepTwoFragment step2 = new RegStepTwoFragment();
//                Bundle b1 = new Bundle();
//                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
//                step2.setArguments(b1);
                return step2;
            }
            case 2 : {
                final RegStepThreeFragment step3 = new RegStepThreeFragment();
//                Bundle b1 = new Bundle();
//                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
//                step3.setArguments(b1);
                return step3;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position) {
            case 0 :
                return new StepViewModel.Builder(context)
                        .setTitle("Шаг 1") //can be a CharSequence instead
                        .setSubtitle("Данные авторизации")
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Шаг 2") //can be a CharSequence instead
                        .setSubtitle("Основные данные пользователя")
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle("Шаг 3") //can be a CharSequence instead
                        .setSubtitle("Доп. данные пользователе")
                        .create();
        }
        return null;
    }
}
