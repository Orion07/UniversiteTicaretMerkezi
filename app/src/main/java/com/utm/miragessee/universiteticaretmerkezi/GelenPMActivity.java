package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONObject;

import java.sql.Date;
import java.util.ArrayList;

import Functions.RestFul;
import JsonParser.PMessage;

public class GelenPMActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelen_pm);
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
            if(list.size()<=0)
                return view;
            final PMessage currentElement = list.get(position);
            TableRow tableRow = (TableRow) view.findViewById(R.id.tablepm);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getParent());
                    final View pmLayout = getLayoutInflater().inflate(R.layout.pm_dialog, null);
                    builder.setView(pmLayout);
                    TextView msg = (TextView) pmLayout.findViewById(R.id.msg);
                    final EditText newmsg = (EditText) pmLayout.findViewById(R.id.newmsg);
                    newmsg.setEnabled(false);
                    msg.setMovementMethod(new ScrollingMovementMethod());
                    msg.setText(currentElement.getMessage());
                    final CheckBox chk = (CheckBox)pmLayout.findViewById(R.id.checkBox);
                    chk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(chk.isChecked()){
                                newmsg.setEnabled(true);
                            }else{
                                newmsg.setEnabled(false);
                            }
                        }
                    });
                    builder.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(chk.isChecked()){
                                //json
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
                    Log.i("Message Click Event : ", String.valueOf(position));
                    //mesaj okundu json
                }
            });

            //Button btn = (Button)view.findViewById(R.id.button2);
            //btn.setOnClickListener(new View.OnClickListener() {
            //    @Override
            //    public void onClick(View v) {
            //        Log.d("MAKECALL", "buton basildi");
            //        Intent call = new Intent(Intent.ACTION_CALL);
            //        call.setData(Uri.parse("tel:(0534) 852-3902"));
            //        startActivity(call);
            //    }
            //});
            TextView section = (TextView) view.findViewById(R.id.section);
            section.setText(currentElement.getTitle());
            TextView sender = (TextView) view.findViewById(R.id.adsoyad);
            sender.setText(currentElement.getSender());
            TextView date = (TextView) view.findViewById(R.id.textView10);
            date.setText(currentElement.getDate());
            ImageView tick = (ImageView)view.findViewById(R.id.imageView3);
            if(currentElement.getRead() == 1) {
                tick.setImageResource(R.drawable.tick2);
                Log.d("Okunan İlan id : ",String.valueOf(currentElement.getMsgid()));
            }
            else {
                tick.setImageResource(R.drawable.tick);
                Log.d("Okunmayan İlan id : ", String.valueOf(currentElement.getMsgid()));
            }

            return view;
        }

    }
}
