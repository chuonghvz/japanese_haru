package prm3101.group_assignment.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.SearchActivity;
import prm3101.group_assignment.adapter.KanjiLevelAdapter;
import prm3101.group_assignment.data.KanjiLevel;
import prm3101.group_assignment.util.Utilities;


public class KanjiFragment extends Fragment {

    private RecyclerView mKanjiLevel;
    private EditText searchBar;
    private TextView mTotalValue;
    private Utilities utils = new Utilities();
    private SharedPreferences prefs;
    private Spinner dropdown;
    private FloatingActionButton mSearch;

    public static KanjiFragment newInstance() {
        return new KanjiFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kanji, container, false);
        dropdown = v.findViewById(R.id.spinner);
        mKanjiLevel = v.findViewById(R.id.kanjiLevel);
        mTotalValue = v.findViewById(R.id.totalValue);
        searchBar = v.findViewById(R.id.searchValue);

        final ArrayList<KanjiLevel> temp = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mKanjiLevel.setLayoutManager(layoutManager);
        KanjiLevelAdapter N0_adapter = new KanjiLevelAdapter(getContext(), temp);
        mKanjiLevel.setAdapter(N0_adapter);

        String[] items = new String[]{"N5", "N4", "N3", "N2", "N1"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(spinnerAdapter);

        // Update View with Kanji level data
        new UpdateView().execute();

        return v;
    }

    public class UpdateView extends AsyncTask<Void, Void, HashMap<String, ArrayList<KanjiLevel>>>{

        @Override
        protected HashMap<String, ArrayList<KanjiLevel>> doInBackground(Void... voids) {
            prefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String data = prefs.getString("All_KANJI", null);
            JSONArray AllKanji = utils.convertDataToSearch(data);
            HashMap<String, ArrayList<KanjiLevel>> kanjiLevelData = utils.getKanjiLevel(getContext(), AllKanji);
            return kanjiLevelData;
        }

        @Override
        protected void onPostExecute(HashMap<String, ArrayList<KanjiLevel>> kanjiLevelData) {
            super.onPostExecute(kanjiLevelData);
            final ArrayList<KanjiLevel> N5_level = kanjiLevelData.get("N5_level");
            KanjiLevelAdapter N5_adapter = new KanjiLevelAdapter(getContext(), N5_level);
            mTotalValue.setText("" + N5_level.size());
            mKanjiLevel.setAdapter(N5_adapter);
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
        }
    }

}
