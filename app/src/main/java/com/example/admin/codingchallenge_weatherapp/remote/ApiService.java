package com.example.admin.codingchallenge_weatherapp.remote;

import com.example.admin.codingchallenge_weatherapp.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 11/13/2017.
 */

public interface ApiService {

    String QUERY_PARAM_ADDRESS = "address";
    String QUERY_PARAM_LATLNG = "latlng";

    //Weather refers to city location
    String QUERY_PARAM_WEAHTER = "q";
    String QUERY_PARAM_APPID= "APPID";

    String QUERY_PARAM_KEY = "key";

    @GET("data/2.5/weather")
    Observable<WeatherResponse> getWeatherResponse(
            @Query(QUERY_PARAM_WEAHTER) String location
            , @Query(QUERY_PARAM_APPID) String key);

/*
    @GET("maps/api/geocode/json")
    Observable<GeocodeResponse> getGeocodeResponseAddress(
            @Query(QUERY_PARAM_ADDRESS) String address
            , @Query(QUERY_PARAM_KEY) String key);
            */
}
