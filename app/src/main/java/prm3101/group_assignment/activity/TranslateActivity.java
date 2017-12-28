package prm3101.group_assignment.activity;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import prm3101.group_assignment.R;
import prm3101.group_assignment.data.translate.TranslateResponse;
import prm3101.group_assignment.util.APIService;
import prm3101.group_assignment.util.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranslateActivity extends AppCompatActivity {

    private final String TAG = "TranslateActivity";
    private APIService mService;
    private TextToSpeech speech;
    private ImageView mCompareArrow;
    private RelativeLayout mOutputView, mResultView;
    private TextView mFromLanguage;
    private TextView mToLanguage;
    private TextView mInputText;
    private TextView mOutputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        TextView mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mToolbarText.setText(R.string.translate);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mCompareArrow = (ImageView) findViewById(R.id.compareArrow);
        ImageView mTranslate = (ImageView) findViewById(R.id.translate);
        ImageView mClear = (ImageView) findViewById(R.id.clear);
        ImageView mInputVolume = (ImageView) findViewById(R.id.inputVolume);
        ImageView mOutputVolume = (ImageView) findViewById(R.id.outputVolume);
        mFromLanguage = (TextView) findViewById(R.id.fromLanguage);
        mToLanguage = (TextView) findViewById(R.id.toLanguage);
        mInputText = (TextView) findViewById(R.id.inputText);
        mOutputText = (TextView) findViewById(R.id.outputText);
        mOutputView = (RelativeLayout) findViewById(R.id.output);
        mResultView = (RelativeLayout) findViewById(R.id.result);

        mOutputView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mService = APIUtils.getAPIService();

        // Switch language
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
                } else {
                    mFromLanguage.setText(R.string.english);
                }
                mToLanguage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.to_language_move));
                if (mToLanguage.getText().toString().equalsIgnoreCase("japanese")) {
                    mToLanguage.setText(R.string.english);
                } else {
                    mToLanguage.setText(R.string.japanese);
                }
            }
        });

        //Translate function
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call Api
                Map<String, String> data = new HashMap<>();
                data.put("q", mInputText.getText().toString().trim());
                switch (mFromLanguage.getText().toString()) {
                    case "English":
                        data.put("langpair", "en|ja");
                        mService.getTranslateResponse(data).enqueue(new Callback<TranslateResponse>() {
                            @Override
                            public void onResponse(Call<TranslateResponse> call,
                                                   Response<TranslateResponse> response) {
                                if (response.isSuccessful()) {
                                    mOutputView.setVisibility(View.VISIBLE);
                                    mResultView.setVisibility(View.VISIBLE);
                                    mOutputText.setText(response.body().getResponseData().getTranslatedText());
                                }
                            }

                            @Override
                            public void onFailure(Call<TranslateResponse> call, Throwable t) {
                                Log.e(TAG, "Fail - " + t.toString());
                            }
                        });
                        break;
                    case "Japanese":
                        data.put("langpair", "ja|en");
                        mService.getTranslateResponse(data).enqueue(new Callback<TranslateResponse>() {
                            @Override
                            public void onResponse(Call<TranslateResponse> call,
                                                   Response<TranslateResponse> response) {
                                if (response.isSuccessful()) {
                                    mOutputView.setVisibility(View.VISIBLE);
                                    mResultView.setVisibility(View.VISIBLE);
                                    mOutputText.setText(response.body().getResponseData().getTranslatedText());
                                }
                            }

                            @Override
                            public void onFailure(Call<TranslateResponse> call, Throwable t) {
                                Log.e(TAG, "Fail - " + t.toString());
                            }
                        });
                        break;
                }
            }
        });

        // Clear input text
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOutputView.setVisibility(View.GONE);
                mResultView.setVisibility(View.GONE);
                mInputText.setText("");
                mInputText.setHint("Tap to enter text");
            }
        });

        // Speech text
        mInputVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech = new TextToSpeech(TranslateActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            speech.setLanguage(Locale.US);
                            speech.speak(mInputText.getText().toString(), TextToSpeech.QUEUE_FLUSH,
                                    null, null);
                        }
                    }
                });
            }
        });

        mOutputVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speech = new TextToSpeech(TranslateActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            speech.setLanguage(Locale.JAPANESE);
                            speech.speak(mOutputText.getText().toString(), TextToSpeech.QUEUE_FLUSH,
                                    null, null);
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onPause() {
        if (speech != null) {
            speech.stop();
            speech.shutdown();
        }
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
