package caps.ua.edu.materialdemo.Activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

public class BaseActivity extends Activity {

    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //needed for ActivityOptions.makeSceneTransitionAnimation to work.
        //allows for fun animating view between activities
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        //Initialized here so that any activity that inherits BaseActivity will also get shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    }
}
