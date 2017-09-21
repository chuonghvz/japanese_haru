package prm3101.group_assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.data.Sentence;

/**
 * Created by ASUS on 21/09/2017.
 */

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Sentence> mData;
    private LayoutInflater mLayoutInflater;

    public SentenceAdapter(Context mContext, ArrayList<Sentence> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    // Tạo viewholder hiển thị 1 item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_sentence, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // bind dữ liệu
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sentence course = mData.get(position);
        holder.meaning.setText(course.getVietnameseMeaning());
        holder.hiragana.setText(course.getHiragana());
        holder.romaji.setText(course.getRomaji());
    }

    // Số phần tử sẽ được hiển thị lên ListView
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView meaning;
        TextView hiragana;
        TextView romaji;
        ImageView sound;
        ImageView like;

        public ViewHolder(View itemView) {
            super(itemView);
            meaning = (TextView) itemView.findViewById(R.id.vietnameseMean);
            hiragana = (TextView) itemView.findViewById(R.id.hiragana);
            romaji = (TextView) itemView.findViewById(R.id.romaji);
            sound = (ImageView) itemView.findViewById(R.id.sound);
            sound = (ImageView) itemView.findViewById(R.id.bookmark);
        }

        // xử lý sự kiện click
        @Override
        public void onClick(View view) {
            //To do
        }
    }
}
