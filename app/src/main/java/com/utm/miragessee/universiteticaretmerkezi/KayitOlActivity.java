package com.utm.miragessee.universiteticaretmerkezi;

import android.app.ProgressDialog;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.Settings.Secure;

import org.json.JSONException;
import org.json.JSONObject;

import Functions.Basic_Functions;
import Functions.RestFul;
import JsonParser.Signup;

public class KayitOlActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private boolean isShow = false;
    EditText txtFirstName,txtLastName,txtEmail,txtPassword,txtRePassword,txtPhone;
    Button btnShow,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        txtFirstName = (EditText) findViewById(R.id.txtFirstName);
        txtLastName = (EditText) findViewById(R.id.txtLastName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtRePassword= (EditText) findViewById(R.id.txtRePassword);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPassword();
            }
        });
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignupProcess();
                //Log.i("Password : ",String.valueOf(txtPassword.getText().toString().equals(txtRePassword.getText().toString())));
            }
        });
    }
    public void showPassword()
    {
        int inputType = txtPassword.getInputType();
        if (inputType == 0x81) {
            btnShow.setText("Gizle");
            txtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            txtRePassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else if (inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            btnShow.setText("Göster");
            txtPassword.setInputType(0x81);
            txtRePassword.setInputType(0x81);
        }
    }
    public void onSignupProcess()
    {
        Basic_Functions bf = new Basic_Functions();
        if(!bf.checkEmail(txtEmail.getText().toString()))
        {
            bf.MsgBox(getApplicationContext(),"Email Gecersiz.Lutfen edu.tr uzantili Email giriniz...");
            return;
        }
        //bf.MsgBox(getApplicationContext(),"Lütfen Bilgileri Eksiksiz Giriniz...");
        if((txtFirstName.getText().length() == 0 || txtLastName.getText().length() == 0) ||
                (txtPassword.getText().length() == 0 || !txtPassword.getText().toString().equals(txtRePassword.getText().toString())) ||
                (txtPhone.getText().length() != 14))
        {
            bf.MsgBox(getApplicationContext(),"Lütfen Bilgileri Eksiksiz Giriniz...");
            return;
        }
        JSONObject params = null, func = null;
        try {
            params = new JSONObject();
            params.put("firstname",txtFirstName.getText());
            params.put("lastname",txtLastName.getText());
            params.put("email", txtEmail.getText());
            params.put("password", txtPassword.getText());
            params.put("phone", txtPhone.getText());
            params.put("deviceID",bf.getDeviceID(getApplicationContext()));
            func = new JSONObject();
            func.put("method_name", "signup");
            func.put("method_params",params);
        } catch (JSONException ex) {

        }
        RestFul restful = new RestFul();
        String JSONResponse = restful.JSONRequest(func);
        Signup signup = new Signup(JSONResponse);
        bf.MsgBox(getApplicationContext(),signup.getMessage());
        if(signup.getResult() == 1)
            this.finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kayit_ol, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

/*
                final ProgressDialog progressDialog = new ProgressDialog(KayitOlActivity.this,
                        R.style.Base_ThemeOverlay_AppCompat_Dark);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                //onLoginSuccess();
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
 */