package com.example.travelparis.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.travelparis.R;
import com.example.travelparis.mvp.models.Article;
import com.example.travelparis.mvp.presenters.ArticlesPresenter;
import com.example.travelparis.mvp.views.ArticlesView;
import com.example.travelparis.ui.adapters.ArticlesAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.travelparis.tools.Constants.ARG_ARTICLE_TYPE;
import static com.example.travelparis.tools.Constants.ARG_CATEGORY_TITLE;


public class ArticlesActivity extends MvpAppCompatActivity implements ArticlesView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @InjectPresenter
    ArticlesPresenter mPresenter;

    ArticlesAdapter mAdapter;
    int mArticleTypeId;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_left_in, R.anim.exit_right_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        Intent i = getIntent();
        if (i != null) {
            mArticleTypeId = i.getIntExtra(ARG_ARTICLE_TYPE, -1);
            initRecycler(mPresenter.getArticles(mArticleTypeId, getResources()));

            getSupportActionBar().setTitle(i.getCharSequenceExtra(ARG_CATEGORY_TITLE));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecycler(List<Article> data) {
        if (mAdapter == null) {
            mAdapter = new ArticlesAdapter(this, data);
            mRecycler.setLayoutManager(new GridLayoutManager(this, 2));
        }
        mRecycler.setAdapter(mAdapter);
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
