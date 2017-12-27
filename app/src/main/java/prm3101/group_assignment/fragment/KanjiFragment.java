package prm3101.group_assignment.fragment;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

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
        Spinner dropdown = (Spinner) v.findViewById(R.id.spinner);
        mKanjiLevel = (RecyclerView) v.findViewById(R.id.kanjiLevel);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mKanjiLevel.setLayoutManager(layoutManager);
        String[] items = new String[]{"N5", "N4", "N3", "N2", "N1"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(spinnerAdapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        KanjiLevelAdapter N5_adapter = new KanjiLevelAdapter(getContext(),
                                utils.getKanjiLevel(getContext()).get("N5_level"));
                        mKanjiLevel.setAdapter(N5_adapter);
                        break;
                    case 1:
                        KanjiLevelAdapter N4_adapter = new KanjiLevelAdapter(getContext(),
                                utils.getKanjiLevel(getContext()).get("N4_level"));
                        mKanjiLevel.setAdapter(N4_adapter);
                        break;
                    case 2:
                        KanjiLevelAdapter N3_adapter = new KanjiLevelAdapter(getContext(),
                                utils.getKanjiLevel(getContext()).get("N3_level"));
                        mKanjiLevel.setAdapter(N3_adapter);
                        break;
                    case 3:
                        KanjiLevelAdapter N2_adapter = new KanjiLevelAdapter(getContext(),
                                utils.getKanjiLevel(getContext()).get("N2_level"));
                        mKanjiLevel.setAdapter(N2_adapter);
                        break;
                    case 4:
                        KanjiLevelAdapter N1_adapter = new KanjiLevelAdapter(getContext(),
                                utils.getKanjiLevel(getContext()).get("N1_level"));
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
