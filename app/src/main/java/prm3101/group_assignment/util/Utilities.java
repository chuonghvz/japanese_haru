package prm3101.group_assignment.util;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.SearchActivity;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.data.KanjiLevel;

/**
 * Created by chuonghv on 12/24/17.
 */

public class Utilities {

    public static ArrayList<String[]> plainHiragana = new ArrayList<>(Arrays.asList(new String[][]{
            {"あ", "a"}, {"い", "i"}, {"う", "u"}, {"え", "e"}, {"お", "o"},
            {"か", "ka"}, {"き", "ki"}, {"く", "ku"}, {"け", "ke"}, {"こ", "ko"},
            {"さ", "sa"}, {"し", "shi"}, {"す", "su"}, {"せ", "se"}, {"そ", "so"},
            {"た", "ta"}, {"ち", "chi"}, {"つ", "tsu"}, {"て", "te"}, {"と", "to"},
            {"な", "na"}, {"に", "ni"}, {"ぬ", "nu"}, {"ね", "ne"}, {"の", "no"},
            {"は", "ha"}, {"ひ", "hi"}, {"ふ", "fu"}, {"へ", "he"}, {"ほ", "ho"},
            {"ま", "ma"}, {"み", "mi"}, {"む", "mu"}, {"め", "me"}, {"も", "mo"},
            {"や", "ya"}, {"", ""}, {"ゆ", "yu"}, {"", ""}, {"よ", "yo"},
            {"ら", "ra"}, {"り", "ri"}, {"る", "ru"}, {"れ", "re"}, {"ろ", "ro"},
            {"わ", "wa"}, {"", ""}, {"", ""}, {"", ""}, {"を", "wo"},
            {"ん", "n"}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
            {"が", "ga"}, {"ぎ", "gi"}, {"ぐ", "gu"}, {"げ", "ge"}, {"ご", "go"},
            {"ざ", "za"}, {"じ", "ji"}, {"ず", "zu"}, {"ぜ", "ze"}, {"ぞ", "zo"},
            {"だ", "da"}, {"ぢ", "ji"}, {"づ", "zu"}, {"で", "de"}, {"ど", "do"},
            {"ば", "ba"}, {"び", "bi"}, {"ぶ", "bu"}, {"べ", "be"}, {"ぼ", "bo"},
            {"ぱ", "pa"}, {"ぴ", "pi"}, {"ぷ", "pu"}, {"ぺ", "pe"}, {"ぽ", "po"}}));

    public static ArrayList<String[]> plainKatakana = new ArrayList<>(Arrays.asList(new String[][]{
            {"ア", "a"}, {"イ", "i"}, {"ウ", "u"}, {"エ", "e"}, {"オ", "o"},
            {"カ", "ka"}, {"キ", "ki"}, {"ク", "ku"}, {"ケ", "ke"}, {"コ", "ko"},
            {"サ", "sa"}, {"シ", "shi"}, {"ス", "su"}, {"セ", "se"}, {"ソ", "so"},
            {"タ", "ta"}, {"チ", "chi"}, {"ツ", "tsu"}, {"テ", "te"}, {"ト", "to"},
            {"ナ", "na"}, {"ニ", "ni"}, {"ヌ", "nu"}, {"ネ", "ne"}, {"ノ", "no"},
            {"ハ", "ha"}, {"ヒ", "hi"}, {"フ", "fu"}, {"ヘ", "he"}, {"ホ", "ho"},
            {"マ", "ma"}, {"ミ", "mi"}, {"ム", "mu"}, {"メ", "me"}, {"モ", "mo"},
            {"ヤ", "ya"}, {"", ""}, {"ユ", "yu"}, {"", ""}, {"ヨ", "yo"},
            {"ラ", "ra"}, {"リ", "ri"}, {"ル", "ru"}, {"レ", "re"}, {"ロ", "ro"},
            {"ワ", "wa"}, {"", ""}, {"", ""}, {"", ""}, {"ヲ", "wo"},
            {"ン", "n"}, {"", ""}, {"", ""}, {"", ""}, {"", ""},
            {"ガ", "ga"}, {"ギ", "gi"}, {"グ", "gu"}, {"ゲ", "ge"}, {"ゴ", "go"},
            {"ザ", "za"}, {"ジ", "ji"}, {"ズ", "zu"}, {"ゼ", "ze"}, {"ゾ", "zo"},
            {"ダ", "da"}, {"ヂ", "ji"}, {"ヅ", "zu"}, {"デ", "de"}, {"ド", "do"},
            {"バ", "ba"}, {"ビ", "bi"}, {"ブ", "bu"}, {"ベ", "be"}, {"ボ", "bo"},
            {"パ", "pa"}, {"ピ", "pi"}, {"プ", "pu"}, {"ペ", "pe"}, {"ポ", "po"}}));

    public static void startFragment(FragmentManager manager, Fragment fragment) {
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.content, fragment).commit();
    }

    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
    }

    // Read file json and Convert data tpo search
    public String readJSONdata(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("response.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // Convert data to search
    public JSONArray convertDataToSearch(String json) {
        JSONObject obj = null;
        JSONArray AllKanji = null;
        try {
            obj = new JSONObject(json);
            AllKanji = obj.getJSONArray("KanjiData");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return AllKanji;
    }

    //Search Kanji
    public void searchKanji(RecyclerView recyclerView, EditText searchView, JSONArray AllKanji, Context context) {
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
            Toast.makeText(context, R.string.no_result, Toast.LENGTH_LONG).show();
        } else {
            try {
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
        if (data.size() == 0) {
            Toast.makeText(context, R.string.no_result, Toast.LENGTH_LONG).show();
        }
        recyclerView.setAdapter(adapter);
    }

    // Get Kanji each level
    public HashMap<String, ArrayList<KanjiLevel>> getKanjiLevel(Context context, JSONArray AllKanji) {
        ArrayList<KanjiLevel> N4_level = new ArrayList<>();
        ArrayList<KanjiLevel> N3_level = new ArrayList<>();
        ArrayList<KanjiLevel> N2_level = new ArrayList<>();
        ArrayList<KanjiLevel> N1_level = new ArrayList<>();
        ArrayList<KanjiLevel> N5_level = new ArrayList<>();
        HashMap<String, ArrayList<KanjiLevel>> result = new HashMap<>();
        try {
            for (int i = 0; i < AllKanji.length(); i++) {
                JSONObject kanjiData = AllKanji.getJSONObject(i);
                String data = kanjiData.toString();
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
                KanjiLevel kanji = new KanjiLevel(character, audio, mean, onyomi, kunyomi, data);
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




