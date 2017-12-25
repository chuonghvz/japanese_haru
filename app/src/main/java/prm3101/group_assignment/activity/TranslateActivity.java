package prm3101.group_assignment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import prm3101.group_assignment.R;
import prm3101.group_assignment.util.APIService;

public class TranslateActivity extends AppCompatActivity {

    private final String TAG = "TranslateActivity";
    private APIService mService;
    private Toolbar mToolbar;
    private ImageView mCompareArrow;
    private TextView mToolbarText;
    private TextView mFromLanguage, mToLanguage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mToolbarText.setText(R.string.translate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCompareArrow = (ImageView) findViewById(R.id.compareArrow);
        mFromLanguage = (TextView) findViewById(R.id.fromLanguage);
        mToLanguage = (TextView) findViewById(R.id.toLanguage);

        mCompareArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.android_rotate_animation);
                mCompareArrow.startAnimation(startRotateAnimation);
                mFromLanguage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.from_language_move));
                if (mFromLanguage.getText().toString().equalsIgnoreCase("english")) {
                    mFromLanguage.setText(R.string.japanese);
                } else{
                    mFromLanguage.setText(R.string.english);
                }
                mToLanguage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.to_language_move));
                if (mToLanguage.getText().toString().equalsIgnoreCase("japanese")) {
                    mToLanguage.setText(R.string.english);
                } else{
                    mToLanguage.setText(R.string.japanese);
                }
            }
        });

        //Call Api
//        mService = APIUtils.getAPIService();
//        Map<String, String> data = new HashMap<>();
//        data.put("q", "Hello World");
//        data.put("langpair", "en|ja");
//        mService.getTranslateResponse(data).enqueue(new Callback<TranslateResponse>() {
//            @Override
//            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
//                Log.e(TAG, response.body().getResponseData().getTranslatedText());
//                Log.e(TAG, "co neeee");
//
//            }
//
//            @Override
//            public void onFailure(Call<TranslateResponse> call, Throwable t) {
//                Log.e(TAG, "Failllllll - " + t.toString());
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
