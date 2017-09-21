package prm3101.group_assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import prm3101.group_assignment.R;
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

    // Tạo viewholder hiển thị 1 item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_kanji, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // bind dữ liệu
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Kanji course = mData.get(position);
        holder.kanji.setText(course.getKanji());
        holder.meaning.setText(course.getMeaning());
    }

    // Số phần tử sẽ được hiển thị lên ListView
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // Holder giúp ListView scroll mượt hơn
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView kanji;
        TextView meaning;


        public ViewHolder(View itemView) {
            super(itemView);
            kanji = (TextView) itemView.findViewById(R.id.kanji);
            meaning = (TextView) itemView.findViewById(R.id.kanjiMeaning);
        }

        // xử lý sự kiện click
        @Override
        public void onClick(View view) {
            //To do
        }
    }
}
