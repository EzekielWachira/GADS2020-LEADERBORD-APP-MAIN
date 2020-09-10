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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQFragment extends Fragment {
    private RecyclerView recyclerView;
    private SkillsAdapter adapter;
    private List<Skill> skillList = new ArrayList<>();
    Skill topSkillModel;
    //    private BaseAdapter adapter;
    private Context mContext;
    private GetGadsService service;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

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
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
//        service = RetrofitClientInstance.retrofitInstance()
//                .create(GetGadsService.class);
//        Call<List<Skill>> listCall = service.getSkillLeaders();
//        listCall.enqueue(new Callback<List<Skill>>() {
//            @Override
//            public void onResponse(Call<List<Skill>> call, Response<List<Skill>> response) {
//                adapter = new SkillsAdapter(response.body());
//                recyclerView.setAdapter(adapter);
////                generateSkillList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Skill>> call, Throwable t) {
//                makeToast("Error occurred retrieving the data");
//            }
//        });
        return view;
    }


    private void generateSkillList(List<Skill> skillList){
//        adapter = new SkillsAdapter(skillList, mContext);
//        adapter = new BaseAdapter(mContext, skillList, Skill.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    private void makeToast(String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}