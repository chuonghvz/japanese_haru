package prm3101.group_assignment.activity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Objects;
import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.ExampleAdapter;
import prm3101.group_assignment.data.KanjiExample;

public class KanjiDetailActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_detail);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        TextView mTitle = findViewById(R.id.toolbar_text);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView mMean = findViewById(R.id.mean);
        TextView mOnHira = findViewById(R.id.onHira);
        TextView mOnRead = findViewById(R.id.onRead);
        TextView mKuHira = findViewById(R.id.kuHira);
        TextView mKuRead = findViewById(R.id.kuRead);
        mVideoView = findViewById(R.id.videoView);
        ImageView mReplay = findViewById(R.id.replay);


        // Set data
        try {
            JSONObject kanjiData = new JSONObject(getIntent().getStringExtra("kanjiData"));
            String videoLink = kanjiData.getJSONObject("kanji").getJSONObject("video").getString("mp4");
            Uri video = Uri.parse(videoLink);
            mVideoView.setVideoURI(video);
            mVideoView.setOnPreparedListener(mp -> {
                mp.setLooping(false);
                mVideoView.start();
            });
            mReplay.setOnClickListener(view -> mVideoView.start());
            String title = kanjiData.getJSONObject("kanji").getString("character");
            String mean = kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english");
            String onyomi_romaji = kanjiData.getJSONObject("kanji").getJSONObject("onyomi")
                    .getString("romaji");
            String onyomi_katakana = kanjiData.getJSONObject("kanji").getJSONObject("onyomi")
                    .getString("katakana");
            String kunyomi_romaji = kanjiData.getJSONObject("kanji").getJSONObject("kunyomi")
                    .getString("romaji");
            String kunyomi_hiragana = kanjiData.getJSONObject("kanji").getJSONObject("kunyomi")
                    .getString("hiragana");
            mTitle.setText(title);
            mMean.setText(mean);
            mOnHira.setText(onyomi_katakana);
            mOnRead.setText(onyomi_romaji);
            mKuHira.setText(kunyomi_hiragana);
            mKuRead.setText(kunyomi_romaji);

            //Example list
            JSONArray listExample = kanjiData.getJSONArray("examples");
            ArrayList<KanjiExample> data = new ArrayList<>();
            RecyclerView recyclerView = findViewById(R.id.exampleList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KanjiDetailActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            ExampleAdapter adapter = new ExampleAdapter(KanjiDetailActivity.this, data);
            for (int i = 0; i < listExample.length(); i++) {
                JSONObject exampleData = listExample.getJSONObject(i);
                String hira = exampleData.getString("japanese");
                String exMean = exampleData.getJSONObject("meaning").getString("english");
                String audio = exampleData.getJSONObject("audio").getString("mp3");
                KanjiExample ex = new KanjiExample(hira, exMean, audio);
                data.add(ex);
            }
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
