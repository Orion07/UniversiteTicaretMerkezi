package com.utm.miragessee.universiteticaretmerkezi;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.*;

import java.util.ArrayList;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.CategoryManager;
import JsonParser.Login;


public class GirisActivity extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnLogin,btnSignup;
    public ArrayList<CategoryManager> categoriesList ;
    int zaa = 0;
    int testasdfasdf12asdf = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        RestFul.restfulURL = getString(R.string.restfulURL);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtEmail.setText("l1211016020@stud.sdu.edu.tr");
        txtPassword.setText("hakan6464");
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginProcess();
            }
        });

        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupActivity = new Intent(getBaseContext(), KayitOlActivity.class);
                startActivity(signupActivity);
            }
        });
    }
    public void onLoginProcess()
    {
        Basic bf = new Basic();
        if(!bf.checkEmail(txtEmail.getText().toString()))
        {
            bf.MsgBox(getApplicationContext(),"Email Gecersiz.Lutfen edu.tr uzantili Email giriniz...");
            return;
        }
        if(txtEmail.getText().length() == 0 || txtPassword.getText().length() == 0)
        {
            bf.MsgBox(getApplicationContext(),"Lütfen Bilgileri Eksiksiz Giriniz...");
            return;
        }
        JSONObject params = null, func = null;
        try {
            params = new JSONObject();
            params.put("email", txtEmail.getText());
            params.put("password", txtPassword.getText());
            params.put("deviceID",bf.getDeviceID(getApplicationContext()));
            func = new JSONObject();
            func.put("method_name", "login");
            func.put("method_params",params);
        } catch (JSONException ex) {

        }
        RestFul restful = new RestFul();
        String JSONResponse = restful.JSONRequest(func);
        Login login = new Login(JSONResponse);
        if(login.getResult() == 1)
        {
            bf.MsgBox(getApplicationContext(), login.getMessage());
            Intent mainActivity = new Intent(GirisActivity.this, AnaActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("email",login.getEmail());
            mainActivity.putExtras(bundle);
            startActivity(mainActivity);
            this.finish();
        } else {
            bf.MsgBox(getApplicationContext(),login.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giris, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
