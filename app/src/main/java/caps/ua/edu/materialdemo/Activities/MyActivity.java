package caps.ua.edu.materialdemo.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;

import caps.ua.edu.materialdemo.Adapters.MyAdapter;
import caps.ua.edu.materialdemo.R;
import caps.ua.edu.materialdemo.Utils.mat_constants;


public class MyActivity extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText etMe;


    private String[] myDataset = new String[]{
            "alpha","beta","cupcake","donut","eclair","froyo","gingerbread","honeycomb","icecream sandwich","jellybean","kitkat","L"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        InitUI();
    }
    //initializes UI Components
    private void InitUI() {
        SetUpTheRecyler();
        etMe = (EditText) findViewById(R.id.etMe);
        FillInUI();
    }
    //fills in the information for all of the views that may need to be filled in.
    private void FillInUI() {
        //gets the text from the previous activity fills it in if it is there.
        String etMeText = getIntent().getStringExtra(mat_constants.ET_ME_TEXT_KEY);
        if(etMeText!=null){
            etMe.setText(etMeText);
        }
    }

    private void SetUpTheRecyler() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
