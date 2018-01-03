package prm3101.group_assignment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import prm3101.group_assignment.R;

/**
 * Created by chuonghv on 1/2/18.
 */

public class ChatbotAdapter extends RecyclerView.ViewHolder  {

    public TextView leftText,rightText;

    public ChatbotAdapter(View itemView){
        super(itemView);
        leftText = (TextView)itemView.findViewById(R.id.leftText);
        rightText = (TextView)itemView.findViewById(R.id.rightText);
    }

}
