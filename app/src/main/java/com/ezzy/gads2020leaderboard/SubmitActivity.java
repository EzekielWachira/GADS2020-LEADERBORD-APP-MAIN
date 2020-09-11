package com.ezzy.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ezzy.gads2020leaderboard.dialogs.ConfirmSubmissionDialog;
import com.ezzy.gads2020leaderboard.dialogs.SubmissionSuccessDialog;
import com.ezzy.gads2020leaderboard.dialogs.SubmissionWarningDialog;
import com.ezzy.gads2020leaderboard.services.GetGadsService;

import java.util.HashMap;
import java.util.Map;

public class SubmitActivity extends AppCompatActivity {

    private static final String TAG = "SubmitActivity";

    private EditText fNameEditText, lNameEditText, emailEditText, linkEditText;
    private Button submitButton;
    private ImageButton backButton;

    public static final String EMAIL = "entry.1824927963";
    public static final String NAME = "entry.1877115667";
    public static final String LAST_NAME = "entry.2006916086";
    public static final String GITHUB_LINK = "entry.284483984" ;
    private RequestQueue queue;
    private final String FORM_URL="https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    private GetGadsService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        fNameEditText = findViewById(R.id.first_name_input);
        lNameEditText = findViewById(R.id.last_name_input);
        emailEditText = findViewById(R.id.email_input);
        linkEditText = findViewById(R.id.github_link_input);
        backButton = findViewById(R.id.backButton);
        submitButton = findViewById(R.id.submit_btn);

        queue = Volley.newRequestQueue(getApplicationContext());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmpty(fNameEditText.getText().toString()) || !isEmpty(lNameEditText.getText().toString())
                        || !isEmpty(emailEditText.getText().toString()) || !isEmpty(linkEditText.getText().toString())){
//                    submit();
                    ConfirmSubmissionDialog dialog = new ConfirmSubmissionDialog();
                    dialog.show(getSupportFragmentManager(), "Confirm Submission");
                }else {
                    makeToast("Please fill out all the fields");
                }
            }
        });
    }

    public void postData(final String firstName, final String lastName, final String email, final String githubUrl){
        StringRequest request = new StringRequest(StringRequest.Method.POST, FORM_URL, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0){
                    SubmissionSuccessDialog dialog = new SubmissionSuccessDialog();
                    dialog.show(getSupportFragmentManager(), "Submission Success dialog");
                }else {
                    SubmissionWarningDialog dialog = new SubmissionWarningDialog();
                    dialog.show(getSupportFragmentManager(), "Dialog Submission Warning");
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                SubmissionWarningDialog dialog = new SubmissionWarningDialog();
                dialog.show(getSupportFragmentManager(), "Dialog Submission Waring");
            }
        }){
            @Override
            protected Map<String, String> getParams(){   //throws AuthFailureError
                Map<String, String> data = new HashMap<>();
                data.put(NAME, firstName);
                data.put(LAST_NAME, lastName);
                data.put(EMAIL, email);
                data.put(GITHUB_LINK, githubUrl);
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        Log.d(TAG, "postData: " + request);
        queue.add(request);
    }

    private boolean isEmpty(String s){
        return s.equals("");
    }

    public void submit(){
//        service = Api.retrofitInstance()
//                .create(GetGadsService.class);
//        if (service != null){
//            service.submit(fNameEditText.getText().toString(), lNameEditText.getText().toString(), emailEditText.getText().toString(),
//                    linkEditText.getText().toString(), new Callback<Project>() {
//                        @Override
//                        public void onResponse(Call<Project> call, Response<Project> response) {
//                            SubmissionSuccessDialog dialog = new SubmissionSuccessDialog();
//                            dialog.show(getSupportFragmentManager(), "Submission Success");
//                        }
//
//                        @Override
//                        public void onFailure(Call<Project> call, Throwable t) {
//                            SubmissionWarningDialog dialog = new SubmissionWarningDialog();
//                            dialog.show(getSupportFragmentManager(), "Submission Error");
//                        }
//                    });
//        }else {
//            SubmissionWarningDialog dialog = new SubmissionWarningDialog();
//            dialog.show(getSupportFragmentManager(), "Error submitting");
//        }
        postData(fNameEditText.getText().toString(), lNameEditText.getText().toString(),
                emailEditText.getText().toString(), linkEditText.getText().toString());
    }

    private void makeToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}