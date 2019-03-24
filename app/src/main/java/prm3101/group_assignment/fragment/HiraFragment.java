package prm3101.group_assignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.HiraganaAdapter;
import prm3101.group_assignment.util.Utilities;

public class HiraFragment extends Fragment {

    private TextToSpeech speech;

    public static HiraFragment newInstance() {
        return new HiraFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hira_kata, container, false);
        final GridView mGridView = v.findViewById(R.id.gridView);
        RadioGroup group = v.findViewById(R.id.radioGroup);

        HiraganaAdapter adapter = new HiraganaAdapter(Utilities.plainHiragana, getActivity().getApplicationContext());
        mGridView.setAdapter(adapter);

        group.setOnCheckedChangeListener((radioGroup, i) -> {
            switch (i) {
                case R.id.rdHiragana:
                    adapter.setHiras(Utilities.plainHiragana);
                    mGridView.setAdapter(adapter);
                    break;
                case R.id.rdKatakana:
                    adapter.setHiras(Utilities.plainKatakana);
                    mGridView.setAdapter(adapter);
                    break;
            }
        });

        // Text to speech
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Speech this word
                speech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            speech.setLanguage(Locale.JAPANESE);
                        }
                    }
                });
            }
        });
        return v;
    }
}
