package caps.ua.edu.materialdemo.Activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.Slide;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import caps.ua.edu.materialdemo.R;
import caps.ua.edu.materialdemo.Utils.mat_constants;

public class LoginAct extends Activity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText etMe;
    private Button buttonLogin;
    private Button buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //needed for ActivityOptions.makeSceneTransitionAnimation to work.
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        initialize();
        initializeCallbacks();
    }

    private void initializeCallbacks() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferences.contains(mat_constants.USER_PREF_KEY + etMe.getText().toString())) {
                    Intent i = new Intent(LoginAct.this, MyActivity.class);
                    //adding multiple views to be used between activities
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginAct.this,
                            Pair.create((View) buttonLogin, "awesomeButton"),
                            Pair.create((View) etMe, "awesomeText"));
                    i.putExtra(mat_constants.ET_ME_TEXT_KEY, etMe.getText().toString());
                    startActivity(i, options.toBundle());
                } else {
                    Toast.makeText(LoginAct.this, "User not registered", Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preferences.contains(mat_constants.USER_PREF_KEY + etMe.getText().toString())) {
                    Toast.makeText(LoginAct.this, "User already registered", Toast.LENGTH_LONG).show();
                }else {
                    Intent i = new Intent(LoginAct.this, RegisterAct.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginAct.this, etMe, "awesomeText");
                    i.putExtra(mat_constants.ET_ME_TEXT_KEY, etMe.getText().toString());
                    startActivity(i, options.toBundle());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getActionBar().isShowing()) {
            getActionBar().hide();
        }
    }

    @Override
    public void finish() {
        super.finish();
        getWindow().setExitTransition(new Slide());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize() {
        etMe = (EditText) findViewById(R.id.etMe);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        fillInUI();
    }

    private void fillInUI() {
        String username = getIntent().getStringExtra(mat_constants.ET_ME_TEXT_KEY);
        if(username!=null){
            etMe.setText(username);
        }
    }

}
