package prm3101.group_assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import prm3101.group_assignment.R;
import prm3101.group_assignment.fragment.HomeFragment;
import prm3101.group_assignment.fragment.JLPTFragment;
import prm3101.group_assignment.fragment.SearchFragment;
import prm3101.group_assignment.fragment.TranslateFragment;
import prm3101.group_assignment.fragment.WordListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();
        setNavigationViewListner();


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    return true;
                case R.id.bottom_search:
                    transaction.replace(R.id.content, new SearchFragment()).commit();
                    return true;
                case R.id.bottom_translate:
                    transaction.replace(R.id.content, new TranslateFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    public void setting(View view) {
//        Intent intent = new Intent(this, SettingsActivity.class);
//        startActivity(intent);
//    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {

            case R.id.setting: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.word_list: {
                fragmentManager.beginTransaction().replace(R.id.content, new WordListFragment()).commit();
                break;
            }
            case R.id.jlpt: {
                fragmentManager.beginTransaction().replace(R.id.content, new JLPTFragment()).commit();
                break;
            }
//            case R.id.search_word: {
//                fragmentManager.beginTransaction().replace(R.id.content, new ItemFragment()).commit();
//                break;
//            }
        }
//        if (item.getItemId() == R.id.word_list) {
//            fragmentManager.beginTransaction().replace(R.id.content, new WordListFragment()).commit();
//            // Handle the camera action
//        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setNavigationViewListner() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
}
