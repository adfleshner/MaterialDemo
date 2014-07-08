package caps.ua.edu.materialdemo.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import caps.ua.edu.materialdemo.Models.User;
import caps.ua.edu.materialdemo.R;
import caps.ua.edu.materialdemo.Utils.RegistrationUtils;
import caps.ua.edu.materialdemo.Utils.mat_constants;

public class RegisterActivity extends BaseActivity {

    private EditText etRegUsername;
    private EditText etRegEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshActionBar();
        setContentView(R.layout.activity_register);
        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshActionBar();
    }

    private void refreshActionBar() {
        getActionBar().setTitle("Register!");
    }

    //Links all of the view to the xml layout file
    private void initialize() {
        etRegUsername = (EditText) findViewById(R.id.et_reg_username);
        etRegEmail = (EditText) findViewById(R.id.et_reg_email);
        FillInUI();
    }

    private void FillInUI() {
        String username = getIntent().getStringExtra(mat_constants.ET_ME_TEXT_KEY);
        if (username != null) {
            etRegUsername.setText(username);
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
            if (RegistrationUtils.isRegistered(preferences, etRegUsername.getText().toString())) {
                Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_SHORT).show();
            } else {
                //new user to be registered
                User newUser = new User(etRegUsername.getText().toString(), etRegEmail.getText().toString());

                if(RegistrationUtils.registerUser(preferences,newUser)) {
                    //Floating UI between activities.
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, etRegUsername, "awesomeText");
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    i.putExtra(mat_constants.ET_ME_TEXT_KEY, etRegUsername.getText().toString());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i, options.toBundle());
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
