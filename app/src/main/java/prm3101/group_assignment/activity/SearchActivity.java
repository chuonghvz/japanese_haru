package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.fragment.SearchFragment;
import prm3101.group_assignment.util.Utils;

public class SearchActivity extends AppCompatActivity {

    private EditText mSearchValue;
    private RecyclerView recyclerView;
    private Utils utils = new Utils();
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TextView mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mSearchValue = (EditText) findViewById(R.id.inputSearch);
        mToolbarText.setText(R.string.search);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.searchResult);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Kanji> temp = new ArrayList<>();
        KanjiAdapter adapter = new KanjiAdapter(SearchActivity.this, temp);
        recyclerView.setAdapter(adapter);

        //Search Function
        new SearchTask().execute();

    }

    public class SearchTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(Void... voids) {
            prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            String data = prefs.getString("All_KANJI", null);
            JSONArray AllKanji = utils.convertDataToSearch(data);
            return AllKanji;
        }

        @Override
        protected void onPostExecute(final JSONArray jsonArray) {
            super.onPostExecute(jsonArray);
            mSearchValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_SEARCH) {
                        utils.searchKanji(recyclerView, mSearchValue, jsonArray, SearchActivity.this);
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
