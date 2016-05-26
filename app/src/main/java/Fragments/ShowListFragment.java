package Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.utm.miragessee.universiteticaretmerkezi.AnaActivity;
import com.utm.miragessee.universiteticaretmerkezi.DetailActivity;
import com.utm.miragessee.universiteticaretmerkezi.IlanActivity;
import com.utm.miragessee.universiteticaretmerkezi.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Functions.Basic;
import Functions.IRestfulTask;
import JsonParser.Advert;
import JsonParser.CategoryManager;
import JsonParser.ElementManager;
import JsonParser.GetCategoryList;

public class ShowListFragment extends Fragment {

    public static ArrayList<ElementManager> elementsList = new ArrayList<ElementManager>();
    private ListView listView2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;
    public static CategoryManager cat;
    private OnFragmentInteractionListener mListener;
    public ShowListFragment(){

    }


    public static ShowListFragment newInstance(ArrayList<ElementManager> list) {
        ShowListFragment fragment = new ShowListFragment(list);
        //Bundle args = new Bundle();
        //fragment.setArguments(args);

        return fragment;
    }

    public ShowListFragment(ArrayList<ElementManager> list)
    {
        elementsList = list;
        System.out.println("Elements List Size : " + list.size());
        Log.i("Fragmen Log", "ben geldim");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            //Log.i("Param1 : ",mParam1);
            //Log.i("Param2 : ", mParam2);
            //AnaActivity aa = (AnaActivity) getActivity();
            //Log.i("Test : " ,aa.getTest());
            //Log.i("Category : ",cat.getCateName());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_showlist, container, false);

        listView2 = (ListView) view.findViewById(R.id.listView2);
        /*elementsList.add(new ElementManager("resim","test","antalya","100"));
        elementsList.add(new ElementManager("resim","test2","istanbul","200"));
        elementsList.add(new ElementManager("resim","test3","izmir","300"));*/
        ArrayAdapter<ElementManager> adapter = new ElemanlarManagerListAdapter();
        listView2.setAdapter(adapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
    private class ElemanlarManagerListAdapter extends ArrayAdapter<ElementManager>{
        public ElemanlarManagerListAdapter() {
            super(getActivity(), R.layout.list_single, elementsList);
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater(getArguments()).inflate(R.layout.list_single, null, false);

            final ElementManager currentElement = elementsList.get(position);
            TableRow tableRow = (TableRow) view.findViewById(R.id.tablerow);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),IlanActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("advertid",currentElement.getId());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            TextView section = (TextView) view.findViewById(R.id.section);
            TextView location = (TextView) view.findViewById(R.id.location);
            TextView price = (TextView) view.findViewById(R.id.price);
            section.setText(currentElement.getBaslik());
            location.setText(currentElement.getKonum());
            price.setText(currentElement.getFiyat());
            ImageView img = (ImageView) view.findViewById(R.id.img);
            //img.setImageResource(R.drawable.ev);
            Basic b = new Basic();
            Bitmap map = b.decompressImage(currentElement.getResim());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageBitmap(map);
            //setImageViewWithByteArray(img,currentElement.getResim());

            return view;
        }

    }
}


