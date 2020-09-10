package com.ezzy.gads2020leaderboard.services;

import com.ezzy.gads2020leaderboard.models.Leaner;
import com.ezzy.gads2020leaderboard.models.Skill;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetGadsService {
    @GET("api/hours")
    Call<List<Leaner>> getLearnerLeaders();

    @GET("api/skilliq")
    Call<List<Skill>> getSkillLeaders();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    void submit(@Field("entry.1877115667") String firstName,
                @Field("entry.2006916086") String lastName,
                @Field("entry.1824927963") String email,
                @Field("entry.284483984") String githubLink,
                Callback<Project> callback);

}
