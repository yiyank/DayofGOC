package com.example.to426.dayofgoc;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SurveyFragment extends Fragment implements View.OnClickListener{


    private Button buttonSurvey;
    private TextView textViewSurveyFeedback, textViewParagraph1, textViewParagraph2;


    public SurveyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View surveyview = inflater.inflate(R.layout.fragment_survey, container, false);

        buttonSurvey = surveyview.findViewById(R.id.buttonSurvey);
        textViewSurveyFeedback = surveyview.findViewById(R.id.textViewSurveyFeedback);
        textViewParagraph1 = surveyview.findViewById(R.id.textViewParagraph1);
        textViewParagraph2 = surveyview.findViewById(R.id.textViewParagraph2);

        buttonSurvey.setOnClickListener(this);
        // Inflate the layout for this fragment
        return surveyview;
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSurvey) {
            Intent browserSurvey = new Intent (Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/1gSQd4ePpPFuAv4y1nElVJ_TPk7WkzYRFVgXnV09oNLk/edit?ts=5be1a2cc"));
            startActivity(browserSurvey);
        }
    }

}
