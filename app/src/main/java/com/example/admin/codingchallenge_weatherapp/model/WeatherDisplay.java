package com.example.admin.codingchallenge_weatherapp.model;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.codingchallenge_weatherapp.R;

import org.w3c.dom.Text;

import retrofit2.http.Url;

/**
 * Created by Admin on 11/13/2017.
 */

public class WeatherDisplay {

    String CityName;
    String WeatherIcon;
    String WeatherType;
    String Date;
    String MaxTemp,MinTemp,CurrentTemp;
    Double Humidity;
    String WeatherUrl;

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getWeatherUrl() {
        return WeatherUrl;
    }

    public void setWeatherUrl(String weatherUrl) {
        WeatherUrl = weatherUrl;
    }

    public Double getHumidity() {
        return Humidity;
    }

    public void setHumidity(Double humidity) {
        Humidity = humidity;
    }

    public String getMaxTemp() {
        return MaxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        MaxTemp = maxTemp;
    }

    public String getMinTemp() {
        return MinTemp;
    }

    public void setMinTemp(String minTemp) {
        MinTemp = minTemp;
    }

    public String getCurrentTemp() {
        return CurrentTemp;
    }

    public void setCurrentTemp(String currentTemp) {
        CurrentTemp = currentTemp;
    }



    public String getWeatherIcon() {
        return WeatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        WeatherIcon = weatherIcon;
    }

    public String getWeatherType() {
        return WeatherType;
    }

    public void setWeatherType(String weatherType) {
        WeatherType = weatherType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }


}
