package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import prm3101.group_assignment.R;
import prm3101.group_assignment.fragment.HiraFragment;
import prm3101.group_assignment.fragment.KanjiFragment;
import prm3101.group_assignment.util.Utilities;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.nav_action);
//        TextView mToolbarText = (TextView) findViewById(R.id.toolbar_text);
//        mToolbarText.setText(R.string.home);
//        setSupportActionBar(mToolbar);

        navigationView = findViewById(R.id.bottom_navigation);
        Utilities.disableShiftMode(navigationView);
        initView();
    }

    public void initView(){
        navigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utilities.startFragment(getSupportFragmentManager(), HiraFragment.newInstance());
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.basic:
                Utilities.startFragment(getSupportFragmentManager(), HiraFragment.newInstance());
                break;
            case R.id.kanji:
                Utilities.startFragment(getSupportFragmentManager(), KanjiFragment.newInstance());
                break;
            case R.id.chatbot:
//                Utilities.startFragment(getSupportFragmentManager(), SearchFragment.newInstance());
                Intent goToNextActivity = new Intent(getApplicationContext(), ChatbotActivity.class);
                startActivity(goToNextActivity);
                break;
        }
        return true;
    }


}
