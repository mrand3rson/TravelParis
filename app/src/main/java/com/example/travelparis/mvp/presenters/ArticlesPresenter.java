package com.example.travelparis.mvp.presenters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.travelparis.R;
import com.example.travelparis.mvp.models.Article;
import com.example.travelparis.mvp.views.ArticlesView;
import com.example.travelparis.tools.BitmapProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Andrei on 05.05.2018.
 */
@InjectViewState
public class ArticlesPresenter extends MvpPresenter<ArticlesView> {

    private List<Article> mData;


    public List<Article> getArticles(int articleTypeId, Resources resources) {
        List<Article> result = new ArrayList<>(2);
        switch (articleTypeId) {
            case R.id.btn_tours: {
                result.add(new Article(R.drawable.paris_tours_article_1,
                        resources.getString(R.string.tour_paris_in_one_day),
                        resources.getString(R.string.tour_paris_in_one_day_description)
                ));
                result.add(new Article(R.drawable.paris_tours_article_2,
                        resources.getString(R.string.tour_paris_illuminations_of_paris_night),
                        resources.getString(R.string.tour_paris_illuminations_of_paris_night_description)
                ));
                break;
            }
            case R.id.btn_day_spending: {
                result.add(new Article(R.drawable.paris_day_article_1,
                        resources.getString(R.string.day_paris_eating),
                        resources.getString(R.string.day_paris_eating_description)
                ));
                result.add(new Article(R.drawable.paris_day_article_2,
                        resources.getString(R.string.day_paris_shopping),
                        resources.getString(R.string.day_paris_shopping_description)
                ));
                break;
            }
            case R.id.btn_attractions: {
                result.add(new Article(R.drawable.paris_attractions_article_1,
                        resources.getString(R.string.attractions_paris_eiffel_tower),
                        resources.getString(R.string.attractions_paris_eiffel_tower_description)
                ));
                result.add(new Article(R.drawable.paris_attractions_article_2,
                        resources.getString(R.string.attractions_paris_louvre_museum),
                        resources.getString(R.string.attractions_paris_louvre_museum_description)
                ));
                break;
            }
            default:
                return Collections.emptyList();
        }
        return result;
    }
}
