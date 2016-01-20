package info.jafe.guaji.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import info.jafe.guaji.Entity.Display;
import info.jafe.guaji.R;
import info.jafe.guaji.app.App;
import info.jafe.guaji.constant.Msg;
import info.jafe.guaji.utils.Logs;


public class MainActivity extends Activity implements View.OnClickListener{
    private static class Hand extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1){
                case Msg.ADD_TO_LIST:{
//                    App.get().addToList((Display)msg.obj);
                    App.get().foo();
                    break;
                }
            }
        }
    }
    public static MainActivity instance;
    private App app;
    private Handler handler;
    private ListView displayListView;
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_top:{
//                Logs.d("onclick");
                App.get().foo();
                break;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        initNewly();
        initList();
        Logs.d("MainActivity");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initNewly(){
        findViewById(R.id.bt_top).setOnClickListener(this);
        app = App.get();
        handler = new Hand();
    }

    private void initList(){
        displayListView = (ListView) findViewById(R.id.display_list);
        app.bindDisplayList(displayListView);
    }
}

