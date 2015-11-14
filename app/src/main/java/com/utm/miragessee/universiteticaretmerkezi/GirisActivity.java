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

import Functions.Basic_Functions;
import Functions.RestFul;
import JsonParser.Login;


public class GirisActivity extends AppCompatActivity {

    EditText txtEmail,txtPassword;
    Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
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
        Basic_Functions bf = new Basic_Functions();
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
        bf.MsgBox(getApplicationContext(),"Result : " + login.getResult());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_giris, menu);
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

        return super.onOptionsItemSelected(item);
    }
}

/*
SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("sayi", editText.getText().toString());

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
                androidHttpTransport.debug = true;

                try
                {
                    androidHttpTransport.call(SOAP_ACTION, envelope);
                    SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    editText2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editText2.setText(response.toString());
                }catch (Exception e1) {
                    e1.printStackTrace();
                }

 */