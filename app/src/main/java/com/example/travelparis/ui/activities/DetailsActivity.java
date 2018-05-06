package com.example.travelparis.ui.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelparis.R;
import com.example.travelparis.mvp.models.Article;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.travelparis.tools.Constants.ARG_ARTICLE;


public class DetailsActivity extends AppCompatActivity {

    CollapsingToolbarLayout ctl;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.appbar_layout)
    AppBarLayout appBarLayout;

    @BindView(R.id.detail_image)
    ImageView imageView;

    @BindView(R.id.description)
    TextView mDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Article article = getIntent().getParcelableExtra(ARG_ARTICLE);
        imageView.setImageResource(article.getImageResource());

        String bold = "<b>".concat(article.getTitle()).concat("</b>");
        mToolbar.setTitle(Html.fromHtml(bold));

        mDescription.setText(article.getText());

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
