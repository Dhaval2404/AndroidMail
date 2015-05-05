package com.dhaval2404.androidmail;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dhaval2404.androidmail.utils.HostUtils;
import com.dhaval2404.androidmail.utils.SharedPrefrenceUtils;

public class MailerDialog extends DialogFragment implements View.OnClickListener {
    Button cancelBtn;
    private View rootView;
    Button saveBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialog_mail, container, false);
        configureComponent(rootView);
        return rootView;
    }

    private void configureComponent(View view) {
        cancelBtn = (Button)view.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(this);
        saveBtn = (Button)view.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
        String s = SharedPrefrenceUtils.getString(getActivity(), HostUtils.HOST_NAME, HostUtils.GOOGLE_HOST_NAME);
        RadioButton radiobutton;
        if (s.equals(HostUtils.GOOGLE_HOST_NAME)){
            radiobutton = (RadioButton)view.findViewById(R.id.googleRB);
        } else  if (s.equals(HostUtils.LIVE_HOST_NAME)) {
            radiobutton = (RadioButton)view.findViewById(R.id.liveRB);
        } else  if (s.equals(HostUtils.YAHOO_HOST_NAME)){
            radiobutton = (RadioButton)view.findViewById(R.id.yahooRB);
        } else {
            radiobutton = (RadioButton)view.findViewById(R.id.googleRB);
        }
        radiobutton.setChecked(true);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancelBtn:
                this.dismiss();
                break;
            case R.id.saveBtn:
                int rbId = ((RadioGroup) rootView.findViewById(R.id.mailer)).getCheckedRadioButtonId();
                if (rbId == R.id.googleRB) {
                    SharedPrefrenceUtils.putString(getActivity(), HostUtils.HOST_NAME, HostUtils.GOOGLE_HOST_NAME);
                    SharedPrefrenceUtils.putInt(getActivity(), HostUtils.SMTP_PORT, HostUtils.GOOGLE_SMTP_PORT);
                    SharedPrefrenceUtils.putBoolean(getActivity(), HostUtils.TLS_REQUIRE, false);
                } else if (rbId == R.id.liveRB) {
                    SharedPrefrenceUtils.putString(getActivity(), HostUtils.HOST_NAME, HostUtils.LIVE_HOST_NAME);
                    SharedPrefrenceUtils.putInt(getActivity(), HostUtils.SMTP_PORT, HostUtils.LIVE_SMTP_PORT);
                    SharedPrefrenceUtils.putBoolean(getActivity(), HostUtils.TLS_REQUIRE, true);
                } else if (rbId == R.id.yahooRB) {
                    SharedPrefrenceUtils.putString(getActivity(), HostUtils.HOST_NAME, HostUtils.YAHOO_HOST_NAME);
                    SharedPrefrenceUtils.putInt(getActivity(), HostUtils.SMTP_PORT, HostUtils.YAHOO_SMTP_PORT);
                    SharedPrefrenceUtils.putBoolean(getActivity(), HostUtils.TLS_REQUIRE, true);
                }
                this.dismiss();
                break;
        }
    }
}
