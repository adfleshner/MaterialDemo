package caps.ua.edu.materialdemo.Activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import caps.ua.edu.materialdemo.R;
import caps.ua.edu.materialdemo.Utils.mat_constants;

public class LoginAct extends Activity {

    private Button btnLogin;
    private EditText etMe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        etMe = (EditText) findViewById(R.id.etMe);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginAct.this,MyActivity.class);
                //adding multiple views to be used between activities
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginAct.this,
                        Pair.create((View)btnLogin, "awesomeButton"),
                        Pair.create((View)etMe,"awesomeText"));
                i.putExtra(mat_constants.ET_ME_TEXT_KEY,etMe.getText().toString());
                startActivity(i, options.toBundle());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(getActionBar().isShowing()){
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
}
