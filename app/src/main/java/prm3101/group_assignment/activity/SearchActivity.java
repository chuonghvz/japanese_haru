package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import prm3101.group_assignment.R;

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
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("KanjiData");
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
//                Get meaning string
//                Log.e(TAG, jo_inside.getJSONObject("kanji").getJSONObject("meaning").getString("english"));
                if (jo_inside.getJSONObject("kanji").getJSONObject("meaning").getString("english")
                        .equalsIgnoreCase("eat")) {
                    Log.e(TAG, jo_inside.getJSONObject("kanji").getString("character"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
