package prm3101.group_assignment.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class TranslateFragment extends Fragment {

    private APIService mService;
    private TextToSpeech speech;
    private RelativeLayout mOutputView, mResultView;
    private TextView mFromLanguage, mToLanguage, mInputText, mOutputText, mToLanguage_2, mFromLanguage_2;
    private ImageView mCompareArrow, mTranslate, mClear, mInputVolume, mOutputVolume, mCopy;
    private ClipboardManager myClipboard;
    private ClipData myClip;


    public static TranslateFragment newInstance() {
        return new TranslateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);

        mCompareArrow = view.findViewById(R.id.compareArrow);
        mTranslate = view.findViewById(R.id.translate);
        mClear = view.findViewById(R.id.clear);
        mCopy = view.findViewById(R.id.copy);
        mInputVolume = view.findViewById(R.id.inputVolume);
        mOutputVolume = view.findViewById(R.id.outputVolume);
        mFromLanguage = view.findViewById(R.id.fromLanguage);
        mFromLanguage_2 = view.findViewById(R.id.fromLanguage_2);
        mToLanguage = view.findViewById(R.id.toLanguage);
        mToLanguage_2 = view.findViewById(R.id.toLanguage_2);
        mInputText = view.findViewById(R.id.inputText);
        mOutputText = view.findViewById(R.id.outputText);
        mOutputView = view.findViewById(R.id.output);
        mResultView = view.findViewById(R.id.result);

        mOutputView.setVisibility(View.GONE);
        mResultView.setVisibility(View.GONE);
        mService = APIUtils.getAPIService();

        // Switch language
        mCompareArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation startRotateAnimation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.android_rotate_animation);
                mCompareArrow.startAnimation(startRotateAnimation);
                mFromLanguage.startAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.from_language_move));
                if (mFromLanguage.getText().toString().equalsIgnoreCase("english")) {
                    mFromLanguage.setText(R.string.japanese);
                    mFromLanguage_2.setText(R.string.japanese);
                } else {
                    mFromLanguage.setText(R.string.english);
                    mFromLanguage_2.setText(R.string.english);
                }
                mToLanguage.startAnimation(AnimationUtils.loadAnimation(getContext(),
                        R.anim.to_language_move));
                if (mToLanguage.getText().toString().equalsIgnoreCase("japanese")) {
                    mToLanguage.setText(R.string.english);
                    mToLanguage_2.setText(R.string.english);
                } else {
                    mToLanguage.setText(R.string.japanese);
                    mToLanguage_2.setText(R.string.japanese);
                }
            }
        });

        //Translate function
        mTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOutputView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.VISIBLE);
                mOutputText.setText("Translating...");

                InputMethodManager inputManager =
                        (InputMethodManager)
                                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                //Call Api
                Map<String, String> data = new HashMap<>();
                data.put("q", mInputText.getText().toString().trim());
                switch (mFromLanguage.getText().toString()) {
                    case "English":
                        data.put("langpair", "en|ja");
                        break;
                    case "Japanese":
                        data.put("langpair", "ja|en");
                        break;
                }
                mService.getTranslateResponse(data).enqueue(new Callback<TranslateResponse>() {
                    @Override
                    public void onResponse(Call<TranslateResponse> call,
                                           Response<TranslateResponse> response) {
                        if (response.isSuccessful()) {
                            mOutputText.setText(response.body().getResponseData().getTranslatedText());
                        }
                    }

                    @Override
                    public void onFailure(Call<TranslateResponse> call, Throwable t) {
                    }
                });
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
                speech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
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
                speech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
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

        //Copy Translate text
        myClipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        mCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = mOutputText.getText().toString();
                myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        if (speech != null) {
            speech.stop();
            speech.shutdown();
        }
        super.onPause();
    }

}
