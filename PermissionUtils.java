package com.charpixel.baseandroidproject.Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * PermissionUtils
 */
public class PermissionUtils {

    /**
     * @return true if given permission is granted
     */
    public static boolean hasPermission(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
                || !(context == null
                || permission == null
                || permission.trim().isEmpty())
                && ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * @return true if given permission list is granted
     */
    public static boolean hasPermissions(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (permissions == null || permissions.length == 0) {
            return false;
        }
        for (String permission : permissions) {
            if (!hasPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(Activity activity, String[] permissions, int requestId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (activity == null || permissions == null || permissions.length == 0) {
            return;
        }
        activity.requestPermissions(permissions, requestId);
    }

    public static void requestPermissions(Fragment fragment, String[] permissions, int requestId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (fragment == null || permissions == null || permissions.length == 0) {
            return;
        }
        fragment.requestPermissions(permissions, requestId);
    }

    public static boolean isMarshMallow() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.M;
    }




    public static boolean isStoragePermissionGranted(FragmentActivity fragmentActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (fragmentActivity.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("PermissionUtils", "Permission is granted");
                return true;
            } else {

                Log.v("PermissionUtils", "Permission is revoked");
                ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("PermissionUtils", "Permission is granted");
            return true;
        }
    }

    public static boolean isCameraPermissionGranted(FragmentActivity fragmentActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (fragmentActivity.checkSelfPermission(Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("PermissionUtils", "Permission is granted");
                return true;
            } else {

                Log.v("PermissionUtils", "Permission is revoked");
                ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.CAMERA}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("PermissionUtils", "Permission is granted");
            return true;
        }
    }


 public static boolean gpsPermission(FragmentActivity fragmentActivity){
     if (Build.VERSION.SDK_INT >= 23) {
         if (fragmentActivity.checkSelfPermission(Manifest.permission.LOCATION_HARDWARE)
                 == PackageManager.PERMISSION_GRANTED && fragmentActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


             Log.v("PermissionUtils", "Permission is granted");
             return true;
         } else {

             Log.v("PermissionUtils", "Permission is revoked");
             ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.LOCATION_HARDWARE}, 1);
             ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

             return false;
         }
     } else { //permission is automatically granted on sdk<23 upon installation
         Log.v("PermissionUtils", "Permission is granted");
         return true;
     }
 }

 public static boolean isFingerPrintPermissionGranted(FragmentActivity fragmentActivity){

     if (Build.VERSION.SDK_INT >= 23) {
         if (fragmentActivity.checkSelfPermission(Manifest.permission.USE_FINGERPRINT)
                 == PackageManager.PERMISSION_GRANTED) {


             Log.v("PermissionUtils", "Permission is granted");
             return true;
         } else {

             Log.v("PermissionUtils", "Permission is revoked");
             ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.USE_FINGERPRINT}, 1);


             return false;
         }
     } else { //permission is automatically granted on sdk<23 upon installation
         Log.v("PermissionUtils", "Permission is granted");
         return true;
     }

 }
    public static boolean isLocationPermissionGranted(FragmentActivity fragmentActivity){

        if (Build.VERSION.SDK_INT >= 23) {
            if (fragmentActivity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {


                Log.v("PermissionUtils", "Permission is granted");
                return true;
            } else {

                Log.v("PermissionUtils", "Permission is revoked");
                ActivityCompat.requestPermissions(fragmentActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("PermissionUtils", "Permission is granted");
            return true;
        }

    }

    public static boolean isLocationPermissionGranted(android.app.Activity context){

        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {


                Log.v("PermissionUtils", "Permission is granted");
                return true;
            } else {

                Log.v("PermissionUtils", "Permission is revoked");
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("PermissionUtils", "Permission is granted");
            return true;
        }

    }

    /*----Method to Check GPS is enable or disable ----- */
    private Boolean getDisplayGpsStatus(Context context) {
        ContentResolver contentResolver = context
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }


    public static boolean isbuildGreaterMarshmallow(){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return true;
        }
        else {
            return false;
        }
    }



}
