package info.jafe.guaji.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import info.jafe.guaji.R;
import info.jafe.guaji.app.App;
import info.jafe.guaji.ui.fragment.BuildingFragment;
import info.jafe.guaji.ui.fragment.SettingsFragment;
import info.jafe.guaji.ui.fragment.SuppliesFragment;
import info.jafe.guaji.ui.interfaces.OnFragmentInteractionListener;
import info.jafe.guaji.utils.Hand;
import info.jafe.guaji.utils.Logs;


public class MainActivity extends Activity implements View.OnClickListener, OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static MainActivity instance;
    private App app;
    private FragmentManager fragmentManager;
    private Fragment fBuildings, fSupplies,fSettings;
    private RelativeLayout rlBuildings, rlSupplies, rlSettings;
    private TextView tvBuildings, tvSupplies, tvSettings;
    private Hand hand;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default: {
                setTabSelection(view.getId());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;
        init();
        initFragment();
        Logs.d("MainActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        App.get().read();
    }

    private void init() {
        hand = new Hand();
        Hand.bind(hand);
    }

    /**
     * 所有Fragment及其组件初始化
     */
    private void initFragment() {
        fragmentManager = getFragmentManager();
        rlBuildings = (RelativeLayout) findViewById(R.id.building_layout);
        rlSupplies = (RelativeLayout) findViewById(R.id.supplies_layout);
        rlSettings = (RelativeLayout) findViewById(R.id.settings_layout);

        tvBuildings = (TextView) findViewById(R.id.building_text);
        tvSupplies = (TextView) findViewById(R.id.supplies_text);
        tvSettings = (TextView) findViewById(R.id.settings_text);

        rlBuildings.setOnClickListener(this);
        rlSupplies.setOnClickListener(this);
        rlSettings.setOnClickListener(this);

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
                setSelectionStyle(rlBuildings, tvBuildings);
                if (fBuildings == null) {
                    fBuildings = BuildingFragment.getInstance("", "");
                    transaction.add(R.id.content, fBuildings);
                }else{
                    transaction.show(fBuildings);
                }
                break;
            }
            case R.id.supplies_layout:{
                setSelectionStyle(rlSupplies,tvSupplies);
                if(fSupplies == null){
                    fSupplies = SuppliesFragment.getInstance("", "");
                    transaction.add(R.id.content,fSupplies);
                }else {
                    transaction.show(fSupplies);
                }
                break;
            }
            case R.id.settings_layout:{
                setSelectionStyle(rlSettings,tvSettings);
                if(fSettings == null){
                    fSettings = SettingsFragment.getInstance("", "");
                    transaction.add(R.id.content,fSettings);
                }else {
                    transaction.show(fSettings);
                }
                break;
            }
            default:{break;}
        }
        transaction.commit();
    }

    /**
     * 还原Tab的样子
     */
    private void clearSelectionStyle() {
        int c0 = getResources().getColor(R.color.table_noselect_back);
        int c1 = getResources().getColor(R.color.table_notselect_text);

        rlBuildings.setBackgroundColor(c0);
        tvBuildings.setTextColor(c1);
        rlSupplies.setBackgroundColor(c0);
        tvSupplies.setTextColor(c1);
        rlSettings.setBackgroundColor(c0);
        tvSettings.setTextColor(c1);
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
        if (fBuildings != null) {
            transaction.hide(fBuildings);
        }
        if (fSupplies != null) {
            transaction.hide(fSupplies);
        }
        if (fSettings != null) {
            transaction.hide(fSettings);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.get().save();
    }

    public void refreshAdapter() {
        BuildingFragment.getInstance("","").refresh();
        SuppliesFragment.getInstance("","").refresh();
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

