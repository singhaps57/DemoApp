package com.example.moviedemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.moviedemo.R;
import com.example.moviedemo.utils.CommonMethod;
import com.example.moviedemo.utils.ConnectionDetector;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivtiy extends AppCompatActivity {
    private AppCompatImageView imgBack;
    private TextInputEditText edtEmail, edtPwd;
    private LinearLayoutCompat linearSignIn;
    private String email, password;
    private ConnectionDetector connectionDetector;
    private boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activtiy);
        findView();
    }

    // initialize object
    private void findView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPwd = findViewById(R.id.edtPwd);
        linearSignIn = findViewById(R.id.linearSignIn);
        imgBack = findViewById(R.id.imgBack);

        // listener
        imgBack.setOnClickListener(view -> onBackPressed());
        linearSignIn.setOnClickListener(view -> moveToMovieListPage(view));
    }

    // validate login form
    private String validateLoginForm() {
        email = edtEmail.getText().toString().trim();
        password = edtPwd.getText().toString().trim();

        if (TextUtils.isEmpty(email) || !CommonMethod.isEmailValid(email)) {
            edtEmail.setFocusable(true);
            edtEmail.requestFocus();
            return getString(R.string.valid_email);
        }

        if (TextUtils.isEmpty(password)) {
            edtPwd.setFocusable(true);
            edtPwd.requestFocus();
            return getString(R.string.valid_password);
        }

        if (password.length() < 6) {
            edtPwd.setFocusable(true);
            edtPwd.requestFocus();
            return getString(R.string.valid_password_digit);
        }


        return "success";
    }


    // move to main activity
    private void moveToMovieListPage(View view) {
        CommonMethod.hideKeyBoard(LoginActivtiy.this);
        String result = validateLoginForm();
        if (result.equalsIgnoreCase("success")) {
            connectionDetector = new ConnectionDetector(getApplicationContext());
            isInternetPresent = connectionDetector.isConnectingToInternet();
            if (isInternetPresent) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Apply activity transition
                    CommonMethod.callActivityFinish(getApplicationContext(), new Intent(getApplicationContext(), MovieListActivity.class));
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    finish();
                } else {
                    // Swap without transition
                    CommonMethod.callActivityFinish(getApplicationContext(), new Intent(getApplicationContext(), MovieListActivity.class));
                    finish();
                }
            } else {
                CommonMethod.showToastShort(getString(R.string.internet_toast), getApplicationContext());
            }
        } else {
            Snackbar.make(view, result, Snackbar.LENGTH_LONG).setBackgroundTint(getResources().getColor(R.color.btn_rect_color)).show();
        }
    }

    // backpress method
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}