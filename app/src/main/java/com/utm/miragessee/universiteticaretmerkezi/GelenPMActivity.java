package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.PMessage;

public class GelenPMActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelen_pm);
        loadMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gelen_pm, menu);
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
    public void loadMessages()
    {
        JSONObject params = null,func = null;
        try{
            params = new JSONObject();
            params.put("email",AnaActivity.getEmail());
            params.put("login_token",AnaActivity.getLogin_token());
            params.put("type",true);
            func = new JSONObject();
            func.put("method_name","getMessage");
            func.put("method_params",params);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        RestFul restful = new RestFul();
        PMessage msg = new PMessage(restful.JSONRequest(func));
        PMessageAdapter adapter = new PMessageAdapter(GelenPMActivity.this,msg.getList());
        ListView listview = (ListView)findViewById(R.id.listView5);
        listview.setAdapter(adapter);
    }
    public void confirmMessage(int msgid)
    {
        JSONObject params = null, func = null;
        Basic b = new Basic();
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            params.put("msgid", msgid);
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "confirmMessage");
            RestFul restFul = new RestFul();
            String str = restFul.JSONRequest(func);
            JSONObject obj = new JSONObject(str);
            if (obj.has("confirmMessage")) {
                JSONObject r = obj.getJSONObject("confirmMessage");
                int result = r.getInt("result");
                if (result == 1) {
                    b.MsgBox(GelenPMActivity.this, "Mesajın okunduğu karşı tarafa bildirildi.");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void sendMessage(PMessage currentElement,String message)
    {
        JSONObject params = null, func = null;
        Basic b = new Basic();
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            params.put("user_id", currentElement.getSender_id());
            params.put("advert_id", currentElement.getAdvert_id());
            params.put("message", message);
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "addMessage");
            RestFul restFul = new RestFul();
            String str = restFul.JSONRequest(func);
            JSONObject obj = new JSONObject(str);
            if (obj.has("addMessage")) {
                JSONObject r = obj.getJSONObject("addMessage");
                int result = r.getInt("result");
                if (result == 1) {
                    b.MsgBox(GelenPMActivity.this, "Mesajınız Gönderildi");
                } else {
                    b.MsgBox(GelenPMActivity.this, "Mesajı gönderirken bir hata oluştu");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void deleteMessage(int msgid,boolean type)
    {
        JSONObject params = null, func = null;
        Basic b = new Basic();
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            params.put("msgid", msgid);
            params.put("type", type);
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "deleteMessage");
            RestFul restFul = new RestFul();
            String str = restFul.JSONRequest(func);
            JSONObject obj = new JSONObject(str);
            if (obj.has("deleteMessage")) {
                JSONObject r = obj.getJSONObject("deleteMessage");
                int result = r.getInt("result");
                if (result == 1) {
                    b.MsgBox(GelenPMActivity.this, "Mesajınız Silindi");
                } else {
                    b.MsgBox(GelenPMActivity.this, "Mesajı silerken bir hata oluştu");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public class PMessageAdapter extends ArrayAdapter<PMessage>
    {
        private Context ctx;
        private ArrayList<PMessage> list = null;
        public PMessageAdapter(Context ctx,ArrayList<PMessage> list)
        {
            super(ctx, R.layout.pm_list, list);
            this.ctx = ctx;
            this.list = list;
        }
        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflaterPassword = getLayoutInflater();
                view = inflaterPassword.inflate(R.layout.pm_list, null);
            }
            if(list.size()<=0){
                LinearLayout layout = new LinearLayout(ctx);
                layout.setOrientation(LinearLayout.VERTICAL);
                TextView txt = new TextView(ctx);
                txt.setText("Mesaj Yok");
                txt.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                layout.addView(txt);
                return layout;
            }
            final Basic b = new Basic();
            final PMessage currentElement = list.get(position);
            final TextView section = (TextView) view.findViewById(R.id.section);
            section.setText(currentElement.getTitle());
            final  TextView sender = (TextView) view.findViewById(R.id.adsoyad);
            sender.setText(currentElement.getSender());
            final TextView date = (TextView) view.findViewById(R.id.textView10);
            date.setText(currentElement.getDate());
            final ImageView tick = (ImageView)view.findViewById(R.id.imageView3);
            if(currentElement.getRead() == 1)
                tick.setImageResource(R.drawable.tick2);
            else
                tick.setImageResource(R.drawable.tick);

            TableRow tableRow = (TableRow) view.findViewById(R.id.tablepm);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
                    final View pmLayout = getLayoutInflater().inflate(R.layout.pm_dialog, null);
                    builder.setView(pmLayout);
                    TextView msg = (TextView) pmLayout.findViewById(R.id.msg);
                    final EditText newmsg = (EditText) pmLayout.findViewById(R.id.newmsg);
                    newmsg.setEnabled(false);
                    msg.setMovementMethod(new ScrollingMovementMethod());
                    msg.setText(currentElement.getMessage());
                    final CheckBox chk = (CheckBox) pmLayout.findViewById(R.id.checkBox);
                    chk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (chk.isChecked()) {
                                newmsg.setEnabled(true);
                            } else {
                                newmsg.setEnabled(false);
                            }
                        }
                    });
                    AlertDialog.Builder btn = builder.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (chk.isChecked()) {
                                String message = newmsg.getText().toString();
                                if (message.length() > 0) {
                                    sendMessage(currentElement,message);
                                } else {
                                    b.MsgBox(GelenPMActivity.this, "Boş Mesaj Gönderemezsiniz");
                                }
                            }
                        }
                    });
                    builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("PHONE CANCEL ", "CANCEL tuşuna basildi");
                        }
                    });
                    builder.show();
                    if (currentElement.getRead() == 0) {
                        confirmMessage(currentElement.getMsgid());
                    }
                }
            });
            tableRow.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    v.setPressed(true);
                    Log.d("Table Row 2 : ", "Tabloya basilma durduruldu");
                    AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
                    Context ctx = getParent().getApplicationContext();
                    ListView list = new ListView(ctx);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(GelenPMActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"Sil"});
                    list.setAdapter(adapter);
                    builder.setView(list);
                    final AlertDialog dialog = builder.create();
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (position == 0) {
                                deleteMessage(currentElement.getMsgid(), true);
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();
                    v.setPressed(false);
                    return false;
                }
            });

            ImageView img = (ImageView)view.findViewById(R.id.imageView4);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("MAKECALL", "buton basildi");
                    Intent call = new Intent(Intent.ACTION_CALL);
                    String tel = currentElement.getPhone().replace("(", "(0");
                    call.setData(Uri.parse("tel:" + tel));
                    startActivity(call);
                }
            });


            return view;
        }

    }
}
