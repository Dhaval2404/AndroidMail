package com.dhaval2404.androidmail;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.dhaval2404.androidmail.utils.SimpleMailUtils;

import org.apache.commons.mail.EmailException;


public class MailActivity extends Activity {
    private EditText fromEmailText, passwordText, toEmailText, subjectText, messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        configureComponent();
    }

    private void configureComponent() {

        ActionBar actionbar = getActionBar();
        if (actionbar != null){
            actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
            actionbar.setDisplayUseLogoEnabled(false);
            actionbar.setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        }

        fromEmailText = (EditText) findViewById(R.id.fromEmail);
        passwordText = (EditText) findViewById(R.id.fromPassword);
        toEmailText = (EditText) findViewById(R.id.toEmail);
        subjectText = (EditText) findViewById(R.id.subject);
        messageText = (EditText) findViewById(R.id.message);
    }

    private void send() {
        String fromEmailId = fromEmailText.getEditableText().toString();
        String fromEmailpassword = passwordText.getEditableText().toString();
        String toEmailId = toEmailText.getEditableText().toString();
        String subject = subjectText.getEditableText().toString();
        String message = messageText.getEditableText().toString();

        if(TextUtils.isEmpty(fromEmailId)){
            Toast.makeText(getApplicationContext(),"Please Enter Valid From Email Address",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(fromEmailpassword)){
            Toast.makeText(getApplicationContext(),"Please Enter Valid From Email Password",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(toEmailId)){
            Toast.makeText(getApplicationContext(),"Please Enter Valid recipient Email Address",Toast.LENGTH_LONG).show();
            return;
        }else if(TextUtils.isEmpty(message)){
            Toast.makeText(getApplicationContext(),"Please Enter Valid Message",Toast.LENGTH_LONG).show();
            return;
        }

        new SendMail().execute(fromEmailId, fromEmailpassword, toEmailId, subject, message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mail, menu);
        return true;
    }

    public static boolean isAppFromPlayStore(Context context, String packageName) {
        boolean result = false;
        String installer = context.getPackageManager()
                .getInstallerPackageName(packageName);
        if(installer!=null && installer.equals("com.android.vending")){
            result = true;
        }
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_send){
            send();
            return true;
        } else if(id == R.id.action_mailer){
            DialogFragment df = new MailerDialog();
            df.show(this.getFragmentManager(), "Select Mailer");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SendMail extends AsyncTask<String, Void, String> {
        private ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(MailActivity.this);
            progress.setTitle("Sending Mail");
            progress.setMessage("Please wait..!!!");
            progress.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                    SimpleMailUtils.sendSimpleMail(MailActivity.this, params[0], params[1], params[2], params[3], params[4]);
            }catch (EmailException ee){
                ee.printStackTrace();
                return ee.getMessage();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progress != null){
                progress.dismiss();
            }
            if (result == null)  {
                Toast.makeText(getApplicationContext(), "Sending Mail Failed", Toast.LENGTH_LONG).show();
            } else if (result.equals("OK")) {
                Toast.makeText(getApplicationContext(), "Mail Successfully Sent", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), (new StringBuilder()).append("Sending Mail Failed. Error:").append(result).toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
