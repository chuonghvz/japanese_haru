package prm3101.group_assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import prm3101.group_assignment.R;
import prm3101.group_assignment.activity.KanjiDetailActivity;
import prm3101.group_assignment.data.KanjiLevel;

/**
 * Created by chuonghv on 12/26/17.
 */

public class KanjiLevelAdapter extends RecyclerView.Adapter<KanjiLevelAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<KanjiLevel> mData;
    private LayoutInflater mLayoutInflater;

    public KanjiLevelAdapter(Context mContext, ArrayList<KanjiLevel> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_kanji_level, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final KanjiLevel data = mData.get(position);
        holder.number.setText(String.valueOf((position + 1)));
        holder.character.setText(data.getCharacter());
        holder.mean.setText(data.getMean());
        holder.onyomi.setText(data.getOnyomi());
        holder.kunyomi.setText(data.getKunyomi());
        holder.bookmark_uncheck.setOnClickListener(view -> {
            if (holder.bookmark_check.getVisibility() == View.INVISIBLE) {
                holder.bookmark_check.setVisibility(View.VISIBLE);
            } else {
                holder.bookmark_check.setVisibility(View.INVISIBLE);
            }
        });
        holder.item.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, KanjiDetailActivity.class);
            intent.putExtra("kanjiData", data.getKanjiData());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView character, number, mean, onyomi, kunyomi;
        ImageView audio, bookmark_uncheck, bookmark_check;
        View item;

        private ViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            character = itemView.findViewById(R.id.character);
            audio = itemView.findViewById(R.id.volume);
            bookmark_uncheck = itemView.findViewById(R.id.bookmark_uncheck);
            bookmark_check = itemView.findViewById(R.id.bookmark_check);
            mean = itemView.findViewById(R.id.meanValue);
            onyomi = itemView.findViewById(R.id.onValue);
            kunyomi = itemView.findViewById(R.id.kunValue);
            item = itemView;
        }
    }
}
