package prm3101.group_assignment.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import prm3101.group_assignment.R;

public class SearchActivity extends AppCompatActivity {

    private TextView mToolbarText;
    private TextView mChoose;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mChoose = (TextView) findViewById(R.id.choose);
        mToolbarText.setText("Tìm kiếm");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                Dialog dialog = new Dialog(SearchActivity.this);
                dialog.setContentView(R.layout.dialog_choose_search);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
