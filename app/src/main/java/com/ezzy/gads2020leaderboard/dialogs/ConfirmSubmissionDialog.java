package com.ezzy.gads2020leaderboard.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ezzy.gads2020leaderboard.R;
import com.ezzy.gads2020leaderboard.SubmitActivity;

public class ConfirmSubmissionDialog extends DialogFragment {

    private Button submitBtn;
    private ImageButton closeBtn;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_submission_confirm, container, false);
        closeBtn = view.findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        submitBtn = view.findViewById(R.id.okayButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SubmitActivity) getActivity()).submit();
                getDialog().dismiss();
            }
        });

        return view;
    }


}
