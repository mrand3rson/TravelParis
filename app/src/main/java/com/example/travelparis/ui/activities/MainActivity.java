package com.example.travelparis.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.travelparis.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.travelparis.tools.Constants.ARG_ARTICLE_TYPE;
import static com.example.travelparis.tools.Constants.ARG_CATEGORY_TITLE;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);
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
