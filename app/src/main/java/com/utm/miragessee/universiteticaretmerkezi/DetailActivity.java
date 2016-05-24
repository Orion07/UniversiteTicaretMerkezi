package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.ChangePassword;
import JsonParser.ElementManager;
import JsonParser.Phone;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ListView list = (ListView)findViewById(R.id.listView4);
        String[] data = {"Şifre","Telefon"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,data);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("LISTVIEW POSITION : ",String.valueOf(position));
                switch (position){
                    case 0:
                        final AlertDialog.Builder builderPassword = new AlertDialog.Builder(DetailActivity.this);
                        LayoutInflater inflaterPassword = getLayoutInflater();
                        final View layoutView = inflaterPassword.inflate(R.layout.password, null);
                        builderPassword.setView(layoutView);
                        builderPassword.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText old = (EditText) layoutView.findViewById(R.id.oldpassword);
                                EditText newpw = (EditText) layoutView.findViewById(R.id.newpassword);
                                EditText renewpw = (EditText) layoutView.findViewById(R.id.renewpassword);
                                Basic b = new Basic();
                                if (newpw.getText().toString().equals(renewpw.getText().toString())) {
                                    if (!old.getText().toString().equals(newpw.getText().toString())) {
                                        String email = AnaActivity.getEmail();
                                        String login_token = AnaActivity.getLogin_token();
                                        JSONObject obj =null,func = null;
                                        try {
                                            obj = new JSONObject();
                                            obj.put("email",email);
                                            obj.put("login_token",login_token);
                                            obj.put("oldpw",old.getText().toString());
                                            obj.put("newpw",newpw.getText().toString());
                                            func = new JSONObject();
                                            func.put("method_name","changePassword");
                                            func.put("method_params",obj);
                                        } catch (JSONException e) {
                                            Log.d("ChangePassword :", e.getMessage());
                                        }
                                        RestFul rest = new RestFul();
                                        String jsonResponse = rest.JSONRequest(func);
                                        ChangePassword pw = new ChangePassword(jsonResponse);
                                        b.MsgBox(getApplicationContext(), pw.getMessage());


                                    } else {
                                        b.MsgBox(getApplicationContext(), "Yeni şifreniz eskisiyle aynı olamaz");
                                    }
                                } else {
                                    b.MsgBox(getApplicationContext(), "Yeni şifrelerin aynı olduğundan emin olunuz!");
                                }

                            }
                        });
                        builderPassword.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("Password CANCEL ", "CANCEL tuşuna basildi");
                            }
                        });
                        builderPassword.show();

                        break;
                    case 1 :
                        AlertDialog.Builder builderPhone = new AlertDialog.Builder(DetailActivity.this);
                        LayoutInflater inflaterPhone = getLayoutInflater();
                        final View phoneLayout = inflaterPhone.inflate(R.layout.phone, null);
                        builderPhone.setView(phoneLayout);
                        builderPhone.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText phone = (EditText)phoneLayout.findViewById(R.id.newphone);
                                EditText newphone = (EditText)phoneLayout.findViewById(R.id.renewphone);
                                Basic b = new Basic();
                                if (phone.getText().toString().equals(newphone.getText().toString())) {
                                    String email = AnaActivity.getEmail();
                                    String login_token = AnaActivity.getLogin_token();
                                    JSONObject obj =null,func = null;
                                    try {
                                        obj = new JSONObject();
                                        obj.put("email",email);
                                        obj.put("login_token", login_token);
                                        obj.put("phone",phone.getText().toString());
                                        func = new JSONObject();
                                        func.put("method_name","changePhone");
                                        func.put("method_params",obj);
                                    } catch (JSONException e) {
                                        Log.d("ChangePhone :", e.getMessage());
                                    }
                                    RestFul rest = new RestFul();
                                    String jsonResponse = rest.JSONRequest(func);
                                    Phone pw = new Phone(jsonResponse);
                                    b.MsgBox(getApplicationContext(), pw.getMessage());

                                }else{
                                    b.MsgBox(getApplicationContext(), "Telefon numaralarının aynı olduğundan emin olunuz!");
                                }
                            }
                        });
                        builderPhone.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.d("PHONE CANCEL ","CANCEL tuşuna basildi");
                            }
                        });
                        builderPhone.show();
                        break;
                    default:
                        Log.d("DETAIL ACTIVITY " , String.valueOf(position));
                        break;
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
