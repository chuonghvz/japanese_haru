package prm3101.group_assignment.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import org.json.JSONArray;
import java.util.ArrayList;
import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.util.Utilities;

public class SearchFragment extends Fragment {

    private EditText mSearchValue;
    private RecyclerView recyclerView;
    private Utilities utils = new Utilities();
    private SharedPreferences prefs;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);

        // Search Function
        new SearchTask().execute();

        return view;
    }

    private void initView(View view) {
        mSearchValue = view.findViewById(R.id.inputSearch);
        recyclerView = view.findViewById(R.id.searchResult);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Kanji> temp = new ArrayList<>();
        KanjiAdapter adapter = new KanjiAdapter(getContext(), temp);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    public class SearchTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            prefs = getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String data = prefs.getString("All_KANJI", null);
            return utils.convertDataToSearch(data);
        }

        @Override
        protected void onPostExecute(final JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            mSearchValue.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    utils.searchKanji(recyclerView, mSearchValue, jsonArray, getContext());
                }
            });
        }
    }
}
