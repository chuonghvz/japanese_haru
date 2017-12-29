package prm3101.group_assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import prm3101.group_assignment.R;
import prm3101.group_assignment.data.KanjiExample;

/**
 * Created by chuonghv on 12/23/17.
 */

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<KanjiExample> mData;
    private LayoutInflater mLayoutInflater;

    public ExampleAdapter(Context mContext, ArrayList<KanjiExample> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.items_kanji_example, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final KanjiExample data = mData.get(position);
        holder.hira.setText(data.getHira());
        holder.meaning.setText(data.getMeaning());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri myUri = Uri.parse(data.getAudio());
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(mContext, myUri);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView hira;
        TextView meaning;
        View item;


        public ViewHolder(View itemView) {
            super(itemView);
            hira = (TextView) itemView.findViewById(R.id.hira);
            meaning = (TextView) itemView.findViewById(R.id.mean);
            item = itemView;
        }

        @Override
        public void onClick(View view) {
            //to do
        }
    }
}
