package prm3101.group_assignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

import prm3101.group_assignment.R;
import prm3101.group_assignment.adapter.HiraganaAdapter;


public class KataFragment extends Fragment {

    private static ArrayList<String[]> plainKatakana = new ArrayList<String[]>(Arrays.asList(new String[][]{
            {"ア", "a"},{"イ", "i"},{"ウ", "u"},{"エ", "e"},{"オ", "o"},
            {"カ", "ka"},{"キ", "ki"},{"ク", "ku"},{"ケ", "ke"},{"コ", "ko"},
            {"サ", "sa"},{"シ", "shi"},{"ス", "su"},{"セ", "se"},{"ソ", "so"},
            {"タ", "ta"},{"チ", "chi"},{"ツ", "tsu"},{"テ", "te"},{"ト", "to"},
            {"ナ", "na"},{"ニ", "ni"},{"ヌ", "nu"},{"ネ", "ne"},{"ノ", "no"},
            {"ハ", "ha"},{"ヒ", "hi"},{"フ", "fu"},{"ヘ", "he"},{"ホ", "ho"},
            {"マ", "ma"},{"ミ", "mi"},{"ム", "mu"},{"メ", "me"},{"モ", "mo"},
            {"ヤ", "ya"},{"",""},{"ユ", "yu"},{"",""},{"ヨ", "yo"},
            {"ラ", "ra"},{"リ", "ri"},{"ル", "ru"},{"レ", "re"},{"ロ", "ro"},
            {"ワ", "wa"},{"",""},{"",""},{"",""},{"ヲ", "wo"},
            {"ン", "n"},{"",""},{"",""},{"",""},{"",""},
            {"ガ", "ga"},{"ギ", "gi"},{"グ", "gu"},{"ゲ", "ge"},{"ゴ", "go"},
            {"ザ", "za"},{"ジ", "ji"},{"ズ", "zu"},{"ゼ", "ze"},{"ゾ", "zo"},
            {"ダ", "da"},{"ヂ", "ji"},{"ヅ", "zu"},{"デ", "de"},{"ド", "do"},
            {"バ", "ba"},{"ビ", "bi"},{"ブ", "bu"},{"ベ", "be"},{"ボ", "bo"},
            {"パ", "pa"},{"ピ", "pi"},{"プ", "pu"},{"ペ", "pe"},{"ポ", "po"}}));

    private OnFragmentInteractionListener mListener;

    public KataFragment() {
        // Required empty public constructor
    }

    public static KataFragment newInstance(String param1, String param2) {
        KataFragment fragment = new KataFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // to do
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frag1 = inflater.inflate(R.layout.fragment_hira_kata, container, false);
        GridView mGridView = (GridView) frag1.findViewById(R.id.gridView);
        HiraganaAdapter adapter = new HiraganaAdapter(plainKatakana, getActivity().getApplicationContext());
        mGridView.setAdapter(adapter);
        return frag1;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
