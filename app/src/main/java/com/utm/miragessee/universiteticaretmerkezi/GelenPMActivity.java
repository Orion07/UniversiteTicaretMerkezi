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
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import JsonParser.PMessage;

public class GelenPMActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelen_pm);

        PMessage p = new PMessage(1,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p2 = new PMessage(2,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p3 = new PMessage(3,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p4 = new PMessage(4,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p5 = new PMessage(5,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p6 = new PMessage(6,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p7 = new PMessage(7,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaa",true);
        PMessage p8 = new PMessage(8,"asdf","qwer", Date.valueOf("25-05-2016"),"zaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",true);
        ArrayList<PMessage> list = new ArrayList<PMessage>();
        list.add(p);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        PMessageAdapter adapter = new PMessageAdapter(GelenPMActivity.this,list);
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
            section.setText(String.valueOf(position));
            Log.d("position : " , String.valueOf(position));
            return view;
        }

    }
}
