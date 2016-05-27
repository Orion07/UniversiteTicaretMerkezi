package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

import JsonParser.PMessage;

public class GidenPmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giden_pm);

        PMessageAdapter adapter = new PMessageAdapter(GidenPmActivity.this,null);
        ListView listview = (ListView)findViewById(R.id.listView6);
        listview.setAdapter(adapter);
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
            Log.d("position : ", String.valueOf(position));
            TextView txt = (TextView) view.findViewById(R.id.textView7);
            txt.setText("Kime : ");
            return view;
        }

    }
}
