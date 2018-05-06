package com.example.travelparis.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Andrei on 05.05.2018.
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ArticlesView extends MvpView {

}
