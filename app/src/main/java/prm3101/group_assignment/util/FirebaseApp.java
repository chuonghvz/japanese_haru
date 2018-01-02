package prm3101.group_assignment.util;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by chuonghv on 1/2/18.
 */

public class FirebaseApp extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
