package prm3101.group_assignment.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.SearchActivity;
import prm3101.group_assignment.adapter.KanjiAdapter;
import prm3101.group_assignment.data.Kanji;


public class SearchFragment extends Fragment {
    private TextView mSearchValue;


    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //to do
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.searchResult);
        mSearchValue = (TextView) v.findViewById(R.id.inputSearch);
        mSearchValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(recyclerView);
                    return true;
                }
                return false;
            }
        });
        return v;
    }

    private void performSearch(RecyclerView recyclerView) {
        mSearchValue.clearFocus();
        InputMethodManager in = (InputMethodManager) getContext().getSystemService(SearchActivity.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(mSearchValue.getWindowToken(), 0);

        // Search method
        String searchValue = mSearchValue.getText().toString().trim();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Kanji> data = new ArrayList<>();
        KanjiAdapter adapter = new KanjiAdapter(getContext(), data);
        if (searchValue.isEmpty()) {
            // to do - Show something when no result
        } else {
            try {
                JSONObject obj = new JSONObject(loadJSONFromAsset());
                JSONArray m_jArry = obj.getJSONArray("KanjiData");
                for (int i = 0; i < m_jArry.length(); i++) {
                    JSONObject kanjiData = m_jArry.getJSONObject(i);
                    if (kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english")
                            .contains(searchValue) || kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english")
                            .equalsIgnoreCase(searchValue)) {
                        Kanji test = new Kanji(kanjiData.getJSONObject("kanji").getString("character"),
                                kanjiData.getJSONObject("kanji").getJSONObject("meaning").getString("english"),
                                kanjiData.toString());
                        data.add(test);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerView.setAdapter(adapter);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getContext().getAssets().open("response.json");
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
