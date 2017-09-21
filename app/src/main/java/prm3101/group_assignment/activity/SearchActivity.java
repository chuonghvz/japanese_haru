package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;

public class SearchActivity extends AppCompatActivity {

    private final String TAG = "SearchActivity";
    private TextView mToolbarText;
    private TextView mChoose;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mChoose = (TextView) findViewById(R.id.choose);
        mToolbarText.setText("Tìm kiếm");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                Dialog dialog = new Dialog(SearchActivity.this);
                dialog.setContentView(R.layout.dialog_choose_search);
                dialog.show();
            }
        });
        //Read Json file
        Log.e(TAG, "-----------------------------------------------");
//        try {
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            JSONArray m_jArry = obj.getJSONArray("KanjiData");
//            for (int i = 0; i < m_jArry.length(); i++) {
//                JSONObject jo_inside = m_jArry.getJSONObject(i);
////                Get meaning string
////                Log.e(TAG, jo_inside.getJSONObject("kanji").getJSONObject("meaning").getString("english"));
//                if (jo_inside.getJSONObject("kanji").getJSONObject("meaning").getString("english")
//                        .equalsIgnoreCase("eat")) {
//                    Log.e(TAG, jo_inside.getJSONObject("kanji").getString("character"));
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.searchResult);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        // Tạo dữ liệu để hiển thị lên Listview
        ArrayList<Kanji> data = new ArrayList<>();
        Kanji test = new Kanji("abc", "abc", "abc", "abc");
        Kanji test2 = new Kanji("abc", "abc", "abc", "abc");
        Kanji test3 = new Kanji("abc", "abc", "abc", "abc");
        Kanji test4 = new Kanji("abc", "abc", "abc", "abc");
        Kanji test5 = new Kanji("abc", "abc", "abc", "abc");
        Kanji test6 = new Kanji("abc", "abc", "abc", "abc");
        data.add(test);
        data.add(test2);
        data.add(test3);
        data.add(test4);
        data.add(test5);
        data.add(test6);
        // Tạo đối tượng adapter và set adapter cho ListView
        KanjiAdapter adapter = new KanjiAdapter(SearchActivity.this, data);
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
