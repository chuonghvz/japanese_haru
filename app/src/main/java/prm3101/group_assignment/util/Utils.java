package prm3101.group_assignment.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.SearchActivity;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.data.KanjiLevel;

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
    public void searchKanji(RecyclerView recyclerView, EditText searchView, Context context) {
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
                    String character = kanjiData.getJSONObject("kanji").getString("character");
                    if (meanOfKanji.contains(searchValue) || meanOfKanji.equalsIgnoreCase(searchValue)) {
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

    // Get Kanji each level
    public HashMap<String, ArrayList<KanjiLevel>> getKanjiLevel(Context context) {
        ArrayList<KanjiLevel> N4_level = new ArrayList<>();
        ArrayList<KanjiLevel> N3_level = new ArrayList<>();
        ArrayList<KanjiLevel> N2_level = new ArrayList<>();
        ArrayList<KanjiLevel> N1_level = new ArrayList<>();
        ArrayList<KanjiLevel> N5_level = new ArrayList<>();
        HashMap<String, ArrayList<KanjiLevel>> result = new HashMap<>();
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray AllKanji = obj.getJSONArray("KanjiData");
            for (int i = 0; i < AllKanji.length(); i++) {
                JSONObject kanjiData = AllKanji.getJSONObject(i);
                String level = kanjiData.getJSONObject("references").getString("grade");
                String mean = kanjiData.getJSONObject("kanji").getJSONObject("meaning")
                        .getString("english");
                String character = kanjiData.getJSONObject("kanji").getString("character");
                String audio = ""; // don't have in API - do later
                String onyomi = kanjiData.getJSONObject("kanji").getJSONObject("onyomi")
                        .getString("katakana") + " (" +
                        kanjiData.getJSONObject("kanji").getJSONObject("onyomi").getString("romaji") + ")";
                String kunyomi = kanjiData.getJSONObject("kanji").getJSONObject("kunyomi")
                        .getString("hiragana") + " (" +
                        kanjiData.getJSONObject("kanji").getJSONObject("kunyomi").getString("romaji") + ")";
                String ex_1_hira = "a";
                String ex_1_mean = "a";
                String ex_2_hira = "a";
                String ex_2_mean = "a";
                KanjiLevel kanji = new KanjiLevel(character, audio, mean, onyomi, kunyomi,
                        ex_1_hira, ex_1_mean, ex_2_hira, ex_2_mean);
                switch (level) {
                    case "1":
                        N5_level.add(kanji);
                        break;
                    case "2":
                        N4_level.add(kanji);
                        break;
                    case "3":
                        N3_level.add(kanji);
                        break;
                    case "4":
                        N2_level.add(kanji);
                        break;
                    case "5":
                        N1_level.add(kanji);
                        break;
                    case "6":
                        N1_level.add(kanji);
                        break;
                }
            }
            result.put("N5_level", N5_level);
            result.put("N4_level", N4_level);
            result.put("N3_level", N3_level);
            result.put("N2_level", N2_level);
            result.put("N1_level", N1_level);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}




