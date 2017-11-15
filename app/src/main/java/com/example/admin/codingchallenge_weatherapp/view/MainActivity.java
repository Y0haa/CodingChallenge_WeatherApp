package com.example.admin.codingchallenge_weatherapp.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.admin.codingchallenge_weatherapp.R;
import com.example.admin.codingchallenge_weatherapp.model.LastLocation;
import com.example.admin.codingchallenge_weatherapp.model.WeatherDisplay;

import java.text.DateFormat;
import java.util.Date;
// Implement the View from the contract to Connect the interface
public class MainActivity extends AppCompatActivity implements MainActivityContract.View{

    private static final String TAG = "MainActivity" ;
    private static final String MY_PREF_FILE = "com.example.admin.codingchallenge_weatherapp" ;

    TextView tvWeather, tvDate, tvHumidity, tvMaxTemp, tvMinTemp, tvCurrentTemp, tvCityName;
    ImageView ivWeather;
    MainActivityPresenter presenter = new MainActivityPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.attachView(this);
        ivWeather = (ImageView) findViewById(R.id.ivWeather);
        tvWeather = (TextView) findViewById(R.id.tvWeather);
        tvCityName = (TextView) findViewById(R.id.tvCityName);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvHumidity = (TextView) findViewById(R.id.tvHumidity);
        tvMaxTemp = (TextView) findViewById(R.id.tvMaxTemp);
        tvMinTemp = (TextView) findViewById(R.id.tvMinTemp);
        tvCurrentTemp = (TextView) findViewById(R.id.tvCurrentTemp);

        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);
        String LastCity  = sharedPreferences.getString("lastCityKey", "NoPreviousCity");

        if (!LastCity.equals("NoPreviousCity")) {
            presenter.getCity(LastCity);
        }
        Log.d(TAG, "onCreate: "+ LastCity);

        SearchView etSearchView = (SearchView) findViewById(R.id.svSearchCity);

        etSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//              send the users request to the presenter
                presenter.getCity(query );
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    @Override
    public void updateWeatherView(WeatherDisplay weatherDisplay) {

        try {
            if (weatherDisplay != null) {
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                tvCityName.setText(weatherDisplay.getCityName());
                tvWeather.setText(weatherDisplay.getWeatherType());
                tvCurrentTemp.setText(weatherDisplay.getCurrentTemp() + "°C");
                tvMaxTemp.setText(weatherDisplay.getMaxTemp() + "°C");
                tvMinTemp.setText(weatherDisplay.getMinTemp() + "°C");
                tvHumidity.setText(weatherDisplay.getHumidity() + "%");
                tvDate.setText(currentDateTimeString);
                Glide.with(MainActivity.this)
                        .load(weatherDisplay.getWeatherUrl())
                        .error(R.mipmap.ic_launcher)
                        .into(ivWeather);

                // used to send location data the model
                LastLocation lastLocation = new LastLocation(this);
                lastLocation.SaveCity(weatherDisplay.getCityName());
            } else {
                Toast.makeText(this, "NULL!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateWeatherViewError(String WeatherError) {
        Toast.makeText(MainActivity.this, WeatherError, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showError(String s) {
        Toast.makeText(this, "Enter Valid City Name", Toast.LENGTH_SHORT).show();
    }
}
