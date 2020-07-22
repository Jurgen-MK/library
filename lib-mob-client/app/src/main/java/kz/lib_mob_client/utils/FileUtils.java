package kz.lib_mob_client.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class FileUtils {
    static boolean result;
    private static Activity act;
    private static Context ctx;
    private static int SETTINGS_REQUEST_CODE = 1;

    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body, String name) {
        ctx = context;
        act = (Activity) context;
        Dexter.withActivity((Activity) context).
                withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        result = toDisc(body, name);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        result = false;
                        showSettingsDialog();
//                        Toast.makeText(context, "NO PERMISSION", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
        return result;
    }

    public static boolean toDisc(ResponseBody body, String name){
        try {
            // todo change the file location/name according to your needs
            File file = new File(Environment.getExternalStorageDirectory().toString()+File.separator+name);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];


                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("huitag", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (Exception e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Activity) ctx);
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
