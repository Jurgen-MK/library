package kz.lib_mob_client.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import kz.lib_mob_client.LoginActivity;
import kz.lib_mob_client.R;
import kz.lib_mob_client.entity.UserInfo;
import kz.lib_mob_client.entity.Users;
import kz.lib_mob_client.utils.DataManager;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class RegStepOneFragment extends Fragment implements BlockingStep {

    DataManager dm;
    Users users;
    UserInfo userInfo;
    EditText etLogin;
    EditText etPassword;
    EditText etPasswordConfirm;
    EditText etAnswer;
    Spinner spSecretQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (users == null){
            users = new Users(null, null, "", 0, 0, null, null, (byte) 1);
        }
        if (userInfo == null){
            userInfo = new UserInfo(0, null, null, null, null, null,
                    "", 0, 0, 0, 0, 0, 0, null,
                    null, "");
        }
        return inflater.inflate(R.layout.fragment_reg_step_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etLogin = view.findViewById(R.id.etLogin);
        etLogin.setFilters(new InputFilter[] {
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dStart, int dEnd) {
                        if (charSequence.equals("")) { // for backspace
                            return charSequence;
                        }
                        if (charSequence.toString().matches("[a-zA-Z ]+")) {
                            return charSequence;
                        }
                        return "";
                    }
                }
        });
        etPassword = view.findViewById(R.id.etPassword);
        etPasswordConfirm = view.findViewById(R.id.etPasswordConfirm);
        etAnswer = view.findViewById(R.id.etAnswer);
        spSecretQuestion = view.findViewById(R.id.spSecretQuestion);

    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        if (etLogin.getText().toString().trim().isEmpty())
            return new VerificationError("Введите логин!");
        if (etPassword.getText().toString().trim().isEmpty())
            return new VerificationError("Введите пароль!");
        if (!TextUtils.equals(etPassword.getText().toString().trim(), etPasswordConfirm.getText().toString().trim()))
            return new VerificationError("Пароли не совпадают!");
        if (spSecretQuestion.getSelectedItem().toString().trim().isEmpty())
            return new VerificationError("Выберите секретный вопрос!");
        if (etAnswer.getText().toString().trim().isEmpty())
            return new VerificationError("Введите ответ на секретный вопрос!");
        return null;
    }

    @Override
    public void onSelected() {
//        Toast.makeText(getContext(), "Fragment - onSelected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

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

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        users.setUsername(etLogin.getText().toString().trim());
        users.setPassword(etPassword.getText().toString().trim());
        users.setAnswer(etAnswer.getText().toString().trim());
        users.setSecretquestions(spSecretQuestion.getSelectedItem().toString().trim());
        userInfo.setUsername(etLogin.getText().toString().trim());

        if (users != null)
            dm.saveUser(users);
        if (userInfo != null)
            dm.saveUserInfo(userInfo);
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }

}