package com.example.travelparis.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travelparis.R;
import com.example.travelparis.mvp.models.Article;
import com.example.travelparis.ui.activities.DetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.travelparis.tools.Constants.ARG_ARTICLE;

/**
 * Created by Andrei on 05.05.2018.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Article> mData;

    public ArticlesAdapter(Context mContext, List<Article> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_articles, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        ImageView image;

        @BindView(R.id.item_title)
        TextView title;

        @BindView(R.id.card)
        CardView card;


        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Article item) {
            this.image.setImageResource(item.getImageResource());
            this.title.setText(item.getTitle());
            this.card.setOnClickListener(view -> {
                showDetails(item);
            });
        }

        private void showDetails(Article article) {
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra(ARG_ARTICLE, article);

            Activity activity = (Activity) mContext;
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    image,
                    mContext.getString(R.string.article_image_transition));
            mContext.startActivity(intent, options.toBundle());

            activity.overridePendingTransition(R.anim.enter_right_in, R.anim.exit_left_out);
        }
    }
}
