package com.example.admin.codingchallenge_weatherapp.remote;

import android.location.Location;

import com.example.admin.codingchallenge_weatherapp.model.WeatherResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 11/13/2017.
 */

public class ApiProvider {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    public static final String WEATHER_KEY = "646745d4992c19daa03c872a71e71cb5";

    public static Retrofit create() {


        //looging interceptor would log the request and response of the network call
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }




    public static Observable<WeatherResponse> getWeatherResponse (String city) {

       // String latitude = String.valueOf(s);
        // String longitude = String.valueOf(location.getLongitude());
        Retrofit retrofit = create();
        ApiService apiService = retrofit.create(ApiService.class);
        //String locationStr = String.valueOf(s);

        return apiService.getWeatherResponse(city, WEATHER_KEY);
    }
}
