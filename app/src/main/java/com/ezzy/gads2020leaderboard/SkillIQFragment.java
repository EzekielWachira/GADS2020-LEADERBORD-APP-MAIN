package com.ezzy.gads2020leaderboard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ezzy.gads2020leaderboard.models.Skill;
import com.ezzy.gads2020leaderboard.services.GetGadsService;
import com.ezzy.gads2020leaderboard.utils.SkillsAdapter;
import com.ezzy.gads2020leaderboard.utils.VerticalItemDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SkillIQFragment extends Fragment {
    private RecyclerView recyclerView;
    private SkillsAdapter adapter;
    private List<Skill> skillList = new ArrayList<>();
    Skill topSkillModel;

    private Context mContext;
    private GetGadsService service;

    public SkillIQFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void fetchData(final RecyclerView recyclerView) {
        String url = "https://gadsapi.herokuapp.com/api/skilliq";
        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("name");
                        String skill = jsonObject.getString("score");
                        String country = jsonObject.getString("country");
                        String badge = jsonObject.getString("badgeUrl");

                        topSkillModel = new Skill(name, skill, country, badge);
                        skillList.add(topSkillModel);

                    }
                    adapter = new SkillsAdapter(skillList, getContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                makeToast(error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_skill_i_q, container, false);
        recyclerView = view.findViewById(R.id.skill_iq_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalItemDecorator(20));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fetchData(recyclerView);

        return view;
    }


    private void makeToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}