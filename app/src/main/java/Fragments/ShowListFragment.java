package Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.utm.miragessee.universiteticaretmerkezi.R;

import java.util.ArrayList;

import JsonParser.CategoryManager;
import JsonParser.ElemanlarManager;

public class ShowListFragment extends Fragment {

    public ArrayList<ElemanlarManager> elemanlarList = new ArrayList<ElemanlarManager>();
    private ListView listView2;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView txt;
    public static CategoryManager cat;
    private OnFragmentInteractionListener mListener;

    public static ShowListFragment newInstance(String param1, String param2) {
        ShowListFragment fragment = new ShowListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ShowListFragment() {
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
        ElemanlarManager elemanlarManager = new ElemanlarManager("resim","baslik","konum","fiyat");
        elemanlarList.add(elemanlarManager);
        ArrayAdapter<ElemanlarManager> adapter = new ElemanlarManagerListAdapter();
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

    private class ElemanlarManagerListAdapter extends ArrayAdapter<ElemanlarManager> {
        public ElemanlarManagerListAdapter() {
            super(getActivity(), R.layout.list_single, elemanlarList);
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            //return super.getView(position, convertView, parent);
            if (view == null)
                view = getLayoutInflater(getArguments()).inflate(R.layout.list_single, null, false);
            //TextView txt = (TextView) view.findViewById(R.id.txt);
            //ImageView img = (ImageView) view.findViewById(R.id.img);

            //ElemanlarManager currentKategori = elemanlarList.get(position);
            //txt.setText(currentKategori.getCateName());
            //img.setImageResource(R.drawable.ic_action_name);

            return view;
        }
    }
}
