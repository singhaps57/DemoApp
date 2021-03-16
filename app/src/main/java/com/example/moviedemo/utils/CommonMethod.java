package com.example.moviedemo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethod {
    public static ProgressDialog showProgressDialog(ProgressDialog mProgressDialog, Context context, String title) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setCancelable(false);
        }
        mProgressDialog.setMessage(title);
        mProgressDialog.show();
        return mProgressDialog;
    }

    public static void hideProgressDialog(ProgressDialog mProgressDialog) {
        try {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //**************** it is used for validate email address***************//
    public static boolean isEmailValid(String email) {
        boolean isValid = false;
        String expression = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        //  String expression = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }

        return isValid;
    }



    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern emailPattern = Pattern
                .compile("^[6-9]\\d{9}$");
        Matcher m = emailPattern.matcher(phoneNumber);
        return m.matches();
    }

    public static boolean isValidName(String name) {
        String regex = "^[a-z0-9_-]{3,15}$";
        Pattern namePattern = Pattern
                .compile(regex);
        Matcher m = namePattern.matcher(name);
        return m.matches();
    }

    public static boolean passwordValidation(String pwd) {
        Pattern namePattern = Pattern
                .compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
        // .compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher m = namePattern.matcher(pwd);
        return m.matches();
    }

    public static void hideKeyBoard(Context context) {
        InputMethodManager manager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            if (manager != null) {
                manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception ex) {
        }

    }


    /* public static boolean isValidPassword(final String password) {
         Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,})");
         Matcher matcher = pattern.matcher(password);

         return matcher.matches();
     }*/

    public static boolean isValidPassword(final String password) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z]).{6,})");
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }


    public static void showAlertWithNoButtons(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(msg)
                .show();
    }

    public static void alertErrorAndExit(Context context, String msg) {
        AlertDialog.Builder d = new AlertDialog.Builder(context);
        d.setCancelable(false);
        d.setTitle("Oppps!");
        d.setMessage(msg + "!\n" + "App will be terminated!");
        d.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid()); //exit app (by killing its process)
            }
        });
        d.show();
    }

//    public static void showAlert(Context context, String title, String string) {
//        // TODO Auto-generated method stub
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setTitle(title);
//        alert.setMessage(string);
//        alert.setCancelable(false);
//        alert.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        alert.show();
//    }


    //**************** it is used for make short length toast***************//
    public static void showToastShort(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

    }


    //**************** it is used for make long length toast***************//
    public static void showToastlong(String text, Context context) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

//    @SuppressLint("ResourceAsColor")
//    public static void displayMessage(View view, String toastString) {
////        Snackbar.make(view, toastString, Snackbar.LENGTH_SHORT)
////                .setAction("Action", null).show();
//        Snackbar snackbar = Snackbar
//                .make(view, toastString, Snackbar.LENGTH_LONG);
//
//        // Changing message text color
//        snackbar.setActionTextColor(R.color.app_color);
//        View sbView = snackbar.getView();
//        //     sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.app_color));
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.WHITE);
//        snackbar.show();
//    }

    public static Date getDate(String sDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date getTime(String sTime) {
        try {
            return new SimpleDateFormat("hh:mm aa").parse(sTime);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(src).openConnection();
            connection.setDoInput(true);
            connection.connect();
            return BitmapFactory.decodeStream(connection.getInputStream());
        } catch (IOException e) {
            return null;
        }
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, 0);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    public static byte[] getBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void callActivity(Context context, Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void callActivityFinish(Context context, Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static String getDateFromTimestamp(long timestamp , String formate){
        SimpleDateFormat sdf = new SimpleDateFormat(formate, Locale.US);
        /*sdf.setTimeZone(TimeZone.getTimeZone("GMT"));*/
        Date date = new Date( timestamp );
        return sdf.format(date);
    }
}
