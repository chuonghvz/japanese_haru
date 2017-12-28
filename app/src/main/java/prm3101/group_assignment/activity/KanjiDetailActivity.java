package prm3101.group_assignment.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.ExampleAdapter;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;
import prm3101.group_assignment.data.KanjiExample;

public class KanjiDetailActivity extends AppCompatActivity {

    private final String TAG = "KanjiDetailActivity";
    private Toolbar mToolbar;
    private VideoView mVideoView;
    private ImageView mReplay;
    private TextView mTitle, mMean, mOnHira, mOnRead, mKuHira, mKuRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_detail);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) findViewById(R.id.toolbar_text);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mMean = (TextView) findViewById(R.id.mean);
        mOnHira = (TextView) findViewById(R.id.onHira);
        mOnRead = (TextView) findViewById(R.id.onRead);
        mKuHira = (TextView) findViewById(R.id.kuHira);
        mKuRead = (TextView) findViewById(R.id.kuRead);
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mReplay = (ImageView) findViewById(R.id.replay);


        // Set data
        try {
            JSONObject kanjiData = new JSONObject(getIntent().getStringExtra("kanjiData"));
            String videoLink = kanjiData.getJSONObject("kanji").getJSONObject("video").getString("mp4");
            Uri video = Uri.parse(videoLink);
            mVideoView.setVideoURI(video);
            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(false);
                    mVideoView.start();
                }
            });
            mReplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mVideoView.start();
                }
            });
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
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.exampleList);
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
