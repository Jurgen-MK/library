package kz.lib_mob_client.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import kz.lib_mob_client.auth_utils.TokenManager;
import kz.lib_mob_client.controller.ServiceApi;
import kz.lib_mob_client.entity.ReportRequest;
import kz.lib_mob_client.network.NetworkServiceAuth;
import kz.lib_mob_client.network.NetworkServiceResource;
import kz.lib_mob_client.network.ServiceAuth;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SAFUtils {
    private static final int WRITE_REQUEST_CODE = 43;
    private static final int SETTINGS_REQUEST_CODE = 1;
    private static Activity act;
    private static Context ctx;
    private static String dtFrom;
    private static String dtTo;
    private static TokenManager tokenManager;

//    public SAFUtils(Activity activity){
//        this.activity = activity;
//    }

    public static void initSAF(Activity activity, String dateFrom, String dateTo){
        tokenManager = TokenManager.getInstance(activity.getSharedPreferences("prefs", activity.MODE_PRIVATE));
        act = activity;
        Dexter.withActivity(act).
                withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE).
                withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
//                            act = activity;
                            dtFrom = dateFrom;
                            dtTo = dateTo;
                            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                            intent.addCategory(Intent.CATEGORY_OPENABLE);
                            intent.setType("application/pdf");
                            intent.putExtra(Intent.EXTRA_TITLE, "innoReport.pdf");
                            act.startActivityForResult(intent, WRITE_REQUEST_CODE);
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    public static void downloadFile(Uri uri, String dateFrom, String dateTo){
        ServiceApi serviceApi = ServiceAuth.createService(ServiceApi.class, tokenManager);
        Call<ResponseBody> call = serviceApi.getReport(new ReportRequest("InnoReport", dateFrom, dateTo));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (writeFileContent(uri, response.body().bytes())){
                        Log.i("Загрузка завершена!", "Загрузка завершена!");
                        Toast.makeText(act, "Загрузка завершена!", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Ошибка при загрузке!", "Ошибка при загрузке!");
                Toast.makeText(act, "Ошибка при загрузке!", Toast.LENGTH_SHORT);
            }
        });
//        NetworkServiceResource.
//                getInstance().
//                getJSONApi().
//                getReport("Bearer " + NetworkServiceAuth.getInstance().getAccessToken(),
//                        new ReportRequest("InnoReport", dateFrom, dateTo)).
//                enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            if (writeFileContent(uri, response.body().bytes())){
//                                Log.i("Загрузка завершена!", "Загрузка завершена!");
//                                Toast.makeText(act, "Загрузка завершена!", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.i("Ошибка при загрузке!", "Ошибка при загрузке!");
//                        Toast.makeText(act, "Ошибка при загрузке!", Toast.LENGTH_SHORT);
//                    }
//                });
    }

    private static boolean writeFileContent(Uri uri, byte[] fileBytes) {
        try {
            ParcelFileDescriptor pfd =
                    act.getContentResolver().
                            openFileDescriptor(uri, "w");

            FileOutputStream fileOutputStream =
                    new FileOutputStream(
                            pfd.getFileDescriptor());

            fileOutputStream.write(fileBytes);

            fileOutputStream.close();
            pfd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Uri currentUri = null;
        if (requestCode == WRITE_REQUEST_CODE) {
            if (data != null) {
                currentUri = data.getData();
                downloadFile(currentUri, dtFrom, dtTo);
            }
        }
    }

    public static void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        builder.setTitle("Нужно разрешение");
        builder.setMessage("Этому приложению требуется разрешение для доступа к файловой системе. Вы можете дать разрешение в настройках");
        builder.setPositiveButton("НАСТРОЙКИ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    private static void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", "kz.lib_mob_client", null);
        intent.setData(uri);
        act.startActivityForResult(intent, SETTINGS_REQUEST_CODE);
    }
}
