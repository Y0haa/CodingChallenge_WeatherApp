package com.example.admin.codingchallenge_weatherapp.view;


import android.util.Log;

import com.example.admin.codingchallenge_weatherapp.model.WeatherDisplay;
import com.example.admin.codingchallenge_weatherapp.model.WeatherResponse;
import com.example.admin.codingchallenge_weatherapp.remote.ApiProvider;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Admin on 11/12/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private static final String TAG = "MainActivity";
    MainActivityContract.View view;
    WeatherDisplay weatherDisplay;

    @Override
    public void attachView(MainActivityContract.View view) {
        this.view = view;
    }
    @Override
    public void detachView() {
        this.view = null;
    }

// Convert Kelvin valus from openweather to Fahrenheit
    private String getConversionKelvinToFahrenheit(Double Kelvin) {
        Double kelvinToFahrenheit = Kelvin * 9 / 5 - 459.67;
        return String.format("%.2f", kelvinToFahrenheit);
    }

//    Dynamically call weather images from openweather
    public static String getWeatherIcon(String icon) {
        return String.format("http://openweathermap.org/img/w/%s.png", icon);
    }

    @Override
    public void getCity(final String city) {

        // get users query through getWeatherFromCity(city);
        //create an observable that will emit the response from the network call
        Observable<WeatherResponse> responseObservableAddress = ApiProvider.getWeatherResponse(city);

        //create an observer that is going to read the emitted values
        Observer<WeatherResponse> responseObserverAddress = new Observer<WeatherResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }
            @Override
            public void onNext(@NonNull WeatherResponse weatherResponse) {
                try {
                    if (weatherResponse != null) {

                        Log.d(TAG, "onNext: " + weatherResponse.getMain().getTemp().toString());
                        weatherDisplay = new WeatherDisplay();
                        weatherDisplay.setCityName(city);
                        weatherDisplay.setWeatherType(weatherResponse.getWeather().get(0).getDescription());
                        weatherDisplay.setWeatherIcon(weatherResponse.getWeather().get(0).getIcon());
                        weatherDisplay.setCurrentTemp(getConversionKelvinToFahrenheit(weatherResponse.getMain().getTemp()));
                        weatherDisplay.setMaxTemp(getConversionKelvinToFahrenheit(weatherResponse.getMain().getTempMax()));
                        weatherDisplay.setMinTemp(getConversionKelvinToFahrenheit(weatherResponse.getMain().getTempMin()));
                        weatherDisplay.setHumidity(weatherResponse.getMain().getHumidity());
                        weatherDisplay.setWeatherUrl(getWeatherIcon(weatherResponse.getWeather().get(0).getIcon()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                view.updateWeatherViewError("Please Enter a Correct City Name");
            }
            @Override
            public void onComplete() {
                view.updateWeatherView(weatherDisplay);
                Log.d(TAG, "onComplete: ");
                Log.d(TAG, "onComplete: " + weatherDisplay.getWeatherType());
            }
        };
        //subscribe the observer to the observable
        responseObservableAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseObserverAddress);
        responseObservableAddress.unsubscribeOn(Schedulers.io());
        //   return weatherDisplay;
    }
}
