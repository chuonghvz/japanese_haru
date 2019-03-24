package prm3101.group_assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.KanjiDetailActivity;
import prm3101.group_assignment.data.Kanji;

/**
 * Created by ASUS on 21/09/2017.
 */

public class KanjiAdapter extends RecyclerView.Adapter<KanjiAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Kanji> mData;
    private LayoutInflater mLayoutInflater;

    public KanjiAdapter(Context mContext, ArrayList<Kanji> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_kanji, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Kanji course = mData.get(position);
        holder.kanji.setText(course.getKanji());
        holder.meaning.setText(course.getMeaning());
        holder.item.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, KanjiDetailActivity.class);
            intent.putExtra("kanjiData", course.getKanjiData());
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView kanji;
        TextView meaning;
        View item;

        private ViewHolder(View itemView) {
            super(itemView);
            kanji = itemView.findViewById(R.id.kanji);
            meaning = itemView.findViewById(R.id.kanjiMeaning);
            item = itemView;
        }
    }
}
