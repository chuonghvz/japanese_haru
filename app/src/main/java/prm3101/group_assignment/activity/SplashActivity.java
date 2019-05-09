package prm3101.group_assignment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import prm3101.group_assignment.R;
import prm3101.group_assignment.util.Utilities;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    private Utilities utils = new Utilities();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                    String allKanji = sharedpreferences.getString("All_KANJI", null);
                    if (allKanji == null) {
                        allKanji = utils.readJSONdata(SplashActivity.this);
                        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("All_KANJI", allKanji);
                        editor.apply();
                    }
                } catch (Exception ignored) {
                } finally {
                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                }
            }
        };
        splashTread.start();

    }
}
