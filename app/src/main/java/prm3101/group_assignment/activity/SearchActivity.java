package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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

public class SearchActivity extends AppCompatActivity {

    private final String TAG = "SearchActivity";
    private TextView mToolbarText, mSearchValue;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mSearchValue = (TextView) findViewById(R.id.inputSearch);
        mToolbarText.setText(R.string.search);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mSearchValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

    }

    private void performSearch() {
        mSearchValue.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(SearchActivity.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(mSearchValue.getWindowToken(), 0);

        // Search method
        String searchValue = mSearchValue.getText().toString().trim();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchResult);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Kanji> data = new ArrayList<>();
        KanjiAdapter adapter = new KanjiAdapter(SearchActivity.this, data);

        if (searchValue.isEmpty()) {
            Toast.makeText(SearchActivity.this, R.string.no_result, Toast.LENGTH_LONG);
        } else {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                JSONArray m_jArry = obj.getJSONArray("KanjiData");
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject kanjiData = m_jArry.getJSONObject(i);
                    if (kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english")
                            .contains(searchValue) || kanjiData.getJSONObject("kanji").getJSONObject("meaning")
                            .getString("english").equalsIgnoreCase(searchValue)) {
                        Kanji test = new Kanji(kanjiData.getJSONObject("kanji").getString("character"),
                                kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english"),
                                kanjiData.toString());
                        data.add(test);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(adapter);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = SearchActivity.this.getAssets().open("response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
