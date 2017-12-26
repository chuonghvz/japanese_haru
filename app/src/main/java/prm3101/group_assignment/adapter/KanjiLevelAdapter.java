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
 * Created by chuonghv on 12/26/17.
 */

public class KanjiLevelAdapter extends RecyclerView.Adapter<KanjiLevelAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Integer> mData;
    private LayoutInflater mLayoutInflater;

    public KanjiLevelAdapter(Context mContext, ArrayList<Integer> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_kanji_level, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        final Kanji course = mData.get(position);
//        holder.kanji.setText(course.getKanji());
//        holder.meaning.setText(course.getMeaning());
//        holder.item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, KanjiDetailActivity.class);
//                intent.putExtra("kanjiData", course.getKanjiData());
//                mContext.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView kanji;
        TextView meaning;
        View item;


        public ViewHolder(View itemView) {
            super(itemView);
//            kanji = (TextView) itemView.findViewById(R.id.kanji);
//            meaning = (TextView) itemView.findViewById(R.id.kanjiMeaning);
//            item = itemView;
        }

        @Override
        public void onClick(View view) {
            //to do
        }
    }
}
