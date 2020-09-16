package kz.lib_mob_client.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kz.lib_mob_client.R;
import kz.lib_mob_client.entity.InnovationReport;
import kz.lib_mob_client.entity.ReportRequest;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.utils.FileUtils;
import kz.lib_mob_client.utils.SAFUtils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InnovationReportFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static final int WRITE_REQUEST_CODE = 43;
    private ResponseBody body;

    EditText edtFrom;
    EditText edtTo;
    EditText edtDate;
    Button btnReport;
    final Calendar myCalendar = Calendar.getInstance();
    Context ctx;
//    SAFUtils safUtils;

    public InnovationReportFragment() {
        // Required empty public constructor
    }

    public static InnovationReportFragment newInstance(String param1, String param2) {
        InnovationReportFragment fragment = new InnovationReportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_innovation_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtFrom = view.findViewById(R.id.etdFrom);
        edtTo = view.findViewById(R.id.etdTo);
        btnReport = view.findViewById(R.id.btnReport);
        ctx = getContext();
//        safUtils = new SAFUtils(getActivity());

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };

        edtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDate = (EditText) view;
                new DatePickerDialog(getContext(),
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        edtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtDate = (EditText) view;
                new DatePickerDialog(getContext(),
                        date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateFrom = edtFrom.getText().toString().trim();
                String dateTo = edtTo.getText().toString().trim();
                Log.i("*********** CONTEXT ", ctx.toString());
//**************  Загрузка SAF
                SAFUtils.initSAF(getActivity(), dateFrom, dateTo);
//**************  Загрузка без SAF
//                NetworkServiceResource.
//                        getInstance().
//                        getJSONApi().
//                        getReport("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(),
//                        new ReportRequest("InnoReport", dateFrom, dateTo)).
//                        enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                if (FileUtils.writeResponseBodyToDisk(view.getContext(), response.body(), "innoReport.pdf")) {
//                                    Toast.makeText(view.getContext(), "innoReport.pdf сохранен", Toast.LENGTH_LONG).show();
//                                } else {
//                                    Toast.makeText(view.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Toast.makeText(view.getContext(), "Файл не найден", Toast.LENGTH_LONG).show();
//                            }
//                        });
            }
        });
    }

    private void updateDate() {
        String myFormat = "yyyy.MM.dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        edtDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SAFUtils.onActivityResult(requestCode, resultCode, data);
    }

}