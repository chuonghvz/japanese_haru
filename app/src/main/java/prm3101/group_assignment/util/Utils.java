package prm3101.group_assignment.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.SearchActivity;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;

/**
 * Created by chuonghv on 12/24/17.
 */

public class Utils {

    // Read file json
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("response.json");
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

    //Search Kanji
    public void searchKanji(RecyclerView recyclerView, TextView searchView, Context context) {
        searchView.clearFocus();
        InputMethodManager in = (InputMethodManager) context.getSystemService(SearchActivity.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchView.getWindowToken(), 0);

        // Search method
        String searchValue = searchView.getText().toString().trim();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Kanji> data = new ArrayList<>();
        KanjiAdapter adapter = new KanjiAdapter(context, data);

        if (searchValue.isEmpty()) {
            Toast.makeText(context, R.string.no_result, Toast.LENGTH_LONG);
        } else {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset(context));
                JSONArray AllKanji = obj.getJSONArray("KanjiData");
                for (int i = 0; i < AllKanji.length(); i++) {
                    JSONObject kanjiData = AllKanji.getJSONObject(i);
                    String meanOfKanji = kanjiData.getJSONObject("kanji").getJSONObject("meaning")
                            .getString("english");
                    String level = kanjiData.getJSONObject("references").getString("grade");
                    String character = kanjiData.getJSONObject("kanji").getString("character");
                    if (level.equalsIgnoreCase("1")){
                        Log.e("aaaa", character);
                    }
                    if (meanOfKanji.contains(searchValue)|| meanOfKanji.equalsIgnoreCase(searchValue)) {
                        Kanji result = new Kanji(character, meanOfKanji, kanjiData.toString());
                        data.add(result);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(adapter);
    }
}




