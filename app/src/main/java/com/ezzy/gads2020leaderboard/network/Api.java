package com.ezzy.gads2020leaderboard.network;

import retrofit2.Retrofit;

public class Api{

    public static Retrofit retrofit;
    public static final String FORM_BASE_URL = "https://docs.google.com/forms/d/e/";

    public static Retrofit retrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(FORM_BASE_URL)
                    .build();
        }

        return retrofit;
    }

}
