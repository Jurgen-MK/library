package kz.lib_mob_client.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import kz.lib_mob_client.R;
import kz.lib_mob_client.utils.DataManager;

public class RegStepTwoFragment extends Fragment implements Step {

    DataManager dm;
    EditText etSurname;
    EditText etName;
    EditText etPatr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_step_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etSurname = view.findViewById(R.id.etSurname);
        etName = view.findViewById(R.id.etName);
        etPatr = view.findViewById(R.id.etPatr);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dm = (DataManager) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (etSurname.getText().toString().trim().isEmpty())
            return new VerificationError("Введите фамилию!");
        if (etName.getText().toString().trim().isEmpty())
            return new VerificationError("Введите имя!");
        if (etPatr.getText().toString().trim().isEmpty())
            return new VerificationError("Введите отчество!");
        return null;
    }

    @Override
    public void onSelected() {
        dm.getUserInfo().setSurname(etSurname.getText().toString().trim());
        dm.getUserInfo().setName(etName.getText().toString().trim());
        dm.getUserInfo().setPatronymic(etPatr.getText().toString().trim());
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }
}