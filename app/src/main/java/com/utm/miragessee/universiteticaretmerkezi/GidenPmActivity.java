package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.PMessage;

public class GidenPmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giden_pm);
        loadMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giden_pm, menu);
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
    public void loadMessages()
    {
        JSONObject params = null,func = null;
        try{
            params = new JSONObject();
            params.put("email",AnaActivity.getEmail());
            params.put("login_token",AnaActivity.getLogin_token());
            params.put("type",false);
            func = new JSONObject();
            func.put("method_name","getMessage");
            func.put("method_params",params);
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        RestFul restful = new RestFul();
        PMessage msg = new PMessage(restful.JSONRequest(func));
        PMessageAdapter adapter = new PMessageAdapter(GidenPmActivity.this,msg.getList());
        ListView listview = (ListView)findViewById(R.id.listView6);
        listview.setAdapter(adapter);
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
                    b.MsgBox(GidenPmActivity.this, "Mesajınız Silindi");
                } else {
                    b.MsgBox(GidenPmActivity.this, "Mesajı silerken bir hata oluştu");
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
                txt.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
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

            TextView txt = (TextView) view.findViewById(R.id.textView7);
            txt.setText("Kime : ");

            TableRow tableRow = (TableRow) view.findViewById(R.id.tablepm);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
                    Context ctx = getParent().getApplicationContext();
                    LinearLayout layout = new LinearLayout(ctx);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    TextView title = new TextView(ctx);
                    title.setText("Gönderdiğiniz Mesaj : ");
                    title.setTypeface(null, Typeface.BOLD);
                    title.setTextColor(Color.BLACK);
                    layout.addView(title);
                    TextView msg = new TextView(ctx);
                    msg.setText(currentElement.getMessage());
                    msg.setTextColor(Color.BLACK);
                    layout.addView(msg);
                    builder.setView(layout);
                    builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(GidenPmActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"Sil"});
                    list.setAdapter(adapter);
                    builder.setView(list);
                    final AlertDialog dialog = builder.create();
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(position == 0){
                                deleteMessage(currentElement.getMsgid(), false);
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
