package kz.lib_mob_client.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;

import kz.lib_mob_client.R;
import kz.lib_mob_client.RegistrationActivity;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.UserCreationRequest;
import kz.lib_mob_client.network.ServiceAuth;
import kz.lib_mob_client.utils.DataManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class RegStepThreeFragment extends Fragment implements Step, BlockingStep {

    DataManager dm;
    EditText etPhone;
    EditText etEmail;
    EditText etBDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg_step_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        etBDate = view.findViewById(R.id.etBDate);
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("____-__-__");
        FormatWatcher formatWatcher = new MaskFormatWatcher( // форматировать текст будет вот он
                MaskImpl.createTerminated(slots)
        );
        formatWatcher.installOn(etBDate);

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
        if (etPhone.getText().toString().trim().isEmpty())
            return new VerificationError("Введите номер телефона!");
        if (etEmail.getText().toString().trim().isEmpty())
            return new VerificationError("Введите почту!");
        if (etBDate.getText().toString().trim().isEmpty())
            return new VerificationError("Введите дату рождения!");
        return null;
    }

    @Override
    public void onSelected() {
        dm.getUserInfo().setPhone(etPhone.getText().toString().trim());
        dm.getUserInfo().setEmail(etEmail.getText().toString().trim());
        dm.getUserInfo().setBirthday(etBDate.getText().toString().trim());

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.goToNextStep();
            }
        }, 2000L);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        ServiceAuth.createServiceRegistration(ServiceApi.class).
                doRegistration(new UserCreationRequest(dm.getUsers(), dm.getUserInfo())).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String message = null;
                        try {
                            message = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                        callback.complete();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getContext(), "Ошибка при регистрации! " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        callback.complete();
                    }
                });
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}