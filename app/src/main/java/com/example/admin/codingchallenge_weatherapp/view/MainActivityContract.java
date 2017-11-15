package com.example.admin.codingchallenge_weatherapp.view;

import android.content.Context;

import com.example.admin.codingchallenge_weatherapp.BasePresenter;
import com.example.admin.codingchallenge_weatherapp.BaseView;
import com.example.admin.codingchallenge_weatherapp.model.WeatherDisplay;

/**
 * Created by Admin on 11/12/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView {
        void updateWeatherView(WeatherDisplay weatherDisplay);
        void updateWeatherViewError(String WeatherError);
    }
    interface Presenter extends BasePresenter<MainActivityContract.View>{
        void getCity(String city);
    }
}
