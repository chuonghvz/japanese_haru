package prm3101.group_assignment.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.HiraganaAdapter;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.adapter.KanjiLevelAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.data.KanjiLevel;
import prm3101.group_assignment.util.Utils;


public class KanjiFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView mKanjiLevel;
    private TextView mTotalValue;
    private Utils utils = new Utils();

    public KanjiFragment() {
        // Required empty public constructor
    }

    public static KanjiFragment newInstance(String param1, String param2) {
        KanjiFragment fragment = new KanjiFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // to do
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kanji, container, false);
        
        // Get data from BasicFragment
        SharedPreferences prefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String data = prefs.getString("All_KANJI", null);
        final JSONArray AllKanji = utils.convertDataToSearch(data);

        Spinner dropdown = (Spinner) v.findViewById(R.id.spinner);
        mKanjiLevel = (RecyclerView) v.findViewById(R.id.kanjiLevel);
        mTotalValue = (TextView) v.findViewById(R.id.totalValue);

        final ArrayList<KanjiLevel> N0_level = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mKanjiLevel.setLayoutManager(layoutManager);
        KanjiLevelAdapter N0_adapter = new KanjiLevelAdapter(getContext(), N0_level);
        mKanjiLevel.setAdapter(N0_adapter);


        String[] items = new String[]{"N5", "N4", "N3", "N2", "N1"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(spinnerAdapter);


        HashMap<String, ArrayList<KanjiLevel>> kanjiLevelData = utils.getKanjiLevel(getContext(), AllKanji);
        final ArrayList<KanjiLevel> N5_level = kanjiLevelData.get("N5_level");
        final ArrayList<KanjiLevel> N4_level = kanjiLevelData.get("N4_level");
        final ArrayList<KanjiLevel> N3_level = kanjiLevelData.get("N3_level");
        final ArrayList<KanjiLevel> N2_level = kanjiLevelData.get("N2_level");
        final ArrayList<KanjiLevel> N1_level = kanjiLevelData.get("N1_level");
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        KanjiLevelAdapter N5_adapter = new KanjiLevelAdapter(getContext(), N5_level);
                        mTotalValue.setText("" + N5_level.size());
                        mKanjiLevel.setAdapter(N5_adapter);
                        break;
                    case 1:
                        KanjiLevelAdapter N4_adapter = new KanjiLevelAdapter(getContext(), N4_level);
                        mTotalValue.setText("" + N4_level.size());
                        mKanjiLevel.setAdapter(N4_adapter);
                        break;
                    case 2:
                        KanjiLevelAdapter N3_adapter = new KanjiLevelAdapter(getContext(), N3_level);
                        mTotalValue.setText("" + N3_level.size());
                        mKanjiLevel.setAdapter(N3_adapter);
                        break;
                    case 3:
                        KanjiLevelAdapter N2_adapter = new KanjiLevelAdapter(getContext(), N2_level);
                        mTotalValue.setText("" + N2_level.size());
                        mKanjiLevel.setAdapter(N2_adapter);
                        break;
                    case 4:
                        KanjiLevelAdapter N1_adapter = new KanjiLevelAdapter(getContext(), N1_level);
                        mTotalValue.setText("" + N1_level.size());
                        mKanjiLevel.setAdapter(N1_adapter);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Log.e("aaaaa", "KanjiFragment");
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
