package prm3101.group_assignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import prm3101.group_assignment.R;

/**
 * Created by ASUS on 18/09/2017.
 */

public class GridAdapter extends BaseAdapter {

    private String hiras[];
    private String romajis[];
    private Context mContext;
    private LayoutInflater mInflater;

    public GridAdapter(String[] hira, String[] romaji, Context context) {
        this.hiras = hira;
        this.romajis = romaji;
        mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if(convertView == null){
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = mInflater.inflate(R.layout.items_hiragana, null);
        }
        TextView hira = (TextView) gridView.findViewById(R.id.hira);
        TextView romaji = (TextView) gridView.findViewById(R.id.romaji);
        hira.setText(hiras[position]);
        romaji.setText(romajis[position]);
        return gridView;
    }
}
