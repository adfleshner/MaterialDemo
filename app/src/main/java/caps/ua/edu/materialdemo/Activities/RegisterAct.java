package caps.ua.edu.materialdemo.Activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import caps.ua.edu.materialdemo.Models.User;
import caps.ua.edu.materialdemo.R;
import caps.ua.edu.materialdemo.Utils.mat_constants;

public class RegisterAct extends Activity {


    private EditText etregusername;
    private EditText etregemail;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //needed for ActivityOptions.makeSceneTransitionAnimation to work.
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("Register!");
        setContentView(R.layout.activity_register);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        initialize();

    }

    //Links all of the view to the xml layout file
    private void initialize() {
        etregusername = (EditText) findViewById(R.id.et_reg_username);
        etregemail = (EditText) findViewById(R.id.et_reg_email);
        FillInUI();
    }

    private void FillInUI() {
        String username = getIntent().getStringExtra(mat_constants.ET_ME_TEXT_KEY);
        if(username!=null){
            etregusername.setText(username);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_register) {
            editor = preferences.edit();
            if(preferences.contains(mat_constants.USER_PREF_KEY+ etregusername.getText().toString())){
                Toast.makeText(RegisterAct.this,"User Already Exists",Toast.LENGTH_SHORT).show();
            }else{
                editor.putString(mat_constants.USER_PREF_KEY+etregusername.getText().toString(), User.toJson(new User(etregusername.getText().toString(),etregemail.getText().toString())));
                Intent i = new Intent(RegisterAct.this,LoginAct.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation( RegisterAct.this , etregusername, "awesomeText");
                i.putExtra(mat_constants.ET_ME_TEXT_KEY, etregusername.getText().toString());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i, options.toBundle());
                editor.commit();
                finish();

            }

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
