package info.jafe.guaji.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import info.jafe.guaji.R;
import info.jafe.guaji.app.App;
import info.jafe.guaji.constant.Msg;
import info.jafe.guaji.ui.fragment.BuildingFragment;
import info.jafe.guaji.ui.fragment.SuppliesFragment;
import info.jafe.guaji.ui.interfaces.OnFragmentInteractionListener;
import info.jafe.guaji.utils.Logs;


public class MainActivity extends Activity implements View.OnClickListener, OnFragmentInteractionListener {
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private static class Hand extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case Msg.ADD_TO_LIST: {
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
    private FragmentManager fragmentManager;
    private Fragment fBuilding, fSupplies;
    private RelativeLayout rlBuilding, rlSupplies;
    private TextView tvBuilding, tvSupplies;

    //    private
    @Override
    public void onClick(View view) {
        Logs.d("onclick");
        switch (view.getId()) {
            default: {
                setTabSelection(view.getId());
            }
//            case R.id.bt_top:{
////                Logs.d("onclick");
//                App.get().foo();
//                break;
//            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        initFragment();
//        initNewly();
//        initList();
        Logs.d("MainActivity");
    }

    /**
     * 所有Fragment及其组件初始化
     */
    private void initFragment() {
        fragmentManager = getFragmentManager();
        rlBuilding = (RelativeLayout) findViewById(R.id.building_layout);
        rlSupplies = (RelativeLayout) findViewById(R.id.supplies_layout);
        tvBuilding = (TextView) findViewById(R.id.building_text);
        tvSupplies = (TextView) findViewById(R.id.supplies_text);

        rlBuilding.setOnClickListener(this);
        rlSupplies.setOnClickListener(this);

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

    /**
     * 选中Tab
     *
     * @param id
     */
    private void setTabSelection(int id) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelectionStyle();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (id) {
            case R.id.building_layout: {
                setSelectionStyle(rlBuilding, tvBuilding);
                if (fBuilding == null) {
                    fBuilding = new BuildingFragment();
                    transaction.add(R.id.content,fBuilding);
                }else{
                    transaction.show(fBuilding);
                }
                break;
            }
            case R.id.supplies_layout:{
                setSelectionStyle(rlSupplies,tvSupplies);
                if(fSupplies == null){
                    fSupplies = new SuppliesFragment();
                    transaction.add(R.id.content,fSupplies);
                }else {
                    transaction.show(fSupplies);
                }
                break;
            }
        }
        transaction.commit();
    }

    /**
     * 还原Tab的样子
     */
    private void clearSelectionStyle() {
        rlBuilding.setBackgroundResource(R.color.table_noselect_back);
        tvBuilding.setTextColor(getResources().getColor(R.color.table_notselect_text));
        rlSupplies.setBackgroundResource(R.color.table_noselect_back);
        tvSupplies.setTextColor(getResources().getColor(R.color.table_notselect_text));
    }

    /**
     * 设置选中Tab的样子
     *
     * @param rl
     * @param tv
     */
    private void setSelectionStyle(RelativeLayout rl, TextView tv) {
        rl.setBackgroundResource(R.color.table_select_back);
        tv.setTextColor(getResources().getColor(R.color.table_select_text));
    }

    /**
     * 隐藏Fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fBuilding != null) {
            transaction.hide(fBuilding);
        }
        if (fSupplies != null) {
            transaction.hide(fSupplies);
        }
    }


//    private void initNewly(){
////        findViewById(R.id.bt_top).setOnClickListener(this);
//        app = App.get();
//        handler = new Hand();
//    }
//
//    private void initList(){
//        displayListView = (ListView) findViewById(R.id.display_list);
//        app.bindDisplayList(displayListView);
//    }
}

