package com.example.admin.codingchallenge_weatherapp;

/**
 * Created by Admin on 11/12/2017.
 */

public interface BasePresenter <V extends BaseView> {

    void attachView(V view);
    void detachView();
}
