package prm3101.group_assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import prm3101.group_assignment.R;

/**
 * Created by ASUS on 18/09/2017.
 */

public class HiraganaAdapter extends BaseAdapter {

    private ArrayList<String[]> hiras;

    private Context mContext;

    public HiraganaAdapter(ArrayList<String[]> hiras, Context mContext) {
        this.hiras = hiras;
        this.mContext = mContext;
    }

    public void setHiras(ArrayList<String[]> hiras) {
        this.hiras = hiras;
    }

    @Override
    public int getCount() {
        return hiras.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if(convertView == null){
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert mInflater != null;
            gridView = mInflater.inflate(R.layout.items_hiragana, null);
        }
        TextView hira = gridView.findViewById(R.id.hira);
        TextView romaji = gridView.findViewById(R.id.romaji);
        hira.setText(hiras.get(position)[0]);
        romaji.setText(hiras.get(position)[1]);
        return gridView;
    }

}
