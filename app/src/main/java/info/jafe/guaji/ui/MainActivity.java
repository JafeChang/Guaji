package info.jafe.guaji.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import info.jafe.guaji.R;
import info.jafe.guaji.app.App;
import info.jafe.guaji.ui.fragment.BuildingFragment;
import info.jafe.guaji.ui.fragment.NewsFragment;
import info.jafe.guaji.ui.fragment.SettingsFragment;
import info.jafe.guaji.ui.fragment.SuppliesFragment;
import info.jafe.guaji.ui.listeners.OnFragmentInteractionListener;
import info.jafe.guaji.ui.listeners.Swapper;
import info.jafe.guaji.utils.Hand;
import info.jafe.guaji.utils.Logs;


public class MainActivity extends Activity implements View.OnClickListener, OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static MainActivity instance;
    private App app;
    private FragmentManager fragmentManager;
    private Fragment fBuildings, fSupplies,fSettings, fNews;
    private RelativeLayout rlBuildings, rlSupplies, rlSettings, rlNews;
    private TextView tvBuildings, tvSupplies, tvSettings, tvNews;
    private int currentFragment = -1;

    private Hand hand;
    private int currentFragmentLayout = R.id.building_layout;

    private DrawerLayout drawer;
    private ListView drawerListView;
    private Swapper swapper;

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
//        Logs.d("MainActivity");
//        Intent intent = new Intent(this, ServiceImp.class);
//        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        App.get().startTimer(2000);
        setTabSelection(currentFragmentLayout);
    }

    private void initData() {
        App.get().read();
    }

    private void init() {
        hand = new Hand();
        Hand.bind(hand);
        drawer = (DrawerLayout) findViewById(R.id.drawer_start);
    }

    /**
     * 所有Fragment及其组件初始化
     */
    private void initFragment() {
        fragmentManager = getFragmentManager();
        rlBuildings = (RelativeLayout) findViewById(R.id.building_layout);
        rlSupplies = (RelativeLayout) findViewById(R.id.supplies_layout);
        rlNews = (RelativeLayout) findViewById(R.id.news_layout);
        rlSettings = (RelativeLayout) findViewById(R.id.settings_layout);

        tvBuildings = (TextView) findViewById(R.id.building_text);
        tvSupplies = (TextView) findViewById(R.id.supplies_text);
        tvNews = (TextView) findViewById(R.id.news_text);
        tvSettings = (TextView) findViewById(R.id.settings_text);

        rlBuildings.setOnClickListener(this);
        rlSupplies.setOnClickListener(this);
        rlNews.setOnClickListener(this);
        rlSettings.setOnClickListener(this);

        drawerListView = (ListView) findViewById(R.id.left_drawer);

        swapper = new Swapper();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Logs.d(currentFragment+"");
        swapper.onTouch(ev);
        return super.dispatchTouchEvent(ev);
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
        int to = 0;
        switch (id) {
            case R.id.building_layout: {
                to = 0;
                break;
            }
            case R.id.supplies_layout:{
                to = 1;
                break;
            }
            case R.id.news_layout:{
                to = 2;
                break;
            }
            case R.id.settings_layout:{
                to = 3;
                break;
            }
            default:{break;}
        }
        if(to == currentFragment){
            return;
        }
        // 每次选中之前先清楚掉上次的选中状态
        clearSelectionStyle();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if(to<currentFragment){
            transaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right);
        }else if(to>currentFragment){
            transaction.setCustomAnimations(R.animator.slide_in_right,R.animator.slide_out_left);
        }
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
                currentFragment = 0;
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
                currentFragment = 1;
                break;
            }
            case R.id.news_layout:{
//                Logs.d("");
//                drawer.openDrawer(Gravity.LEFT);
                setSelectionStyle(rlNews,tvNews);
                if(fNews == null){
                    fNews = NewsFragment.getInstance("", "");
                    transaction.add(R.id.content,fNews);
                }else {
                    transaction.show(fNews);
                }
                currentFragment = 2;
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
                currentFragment = 3;
                break;
            }
            default:{break;}
        }
        transaction.commit();
        currentFragmentLayout = id;
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
        rlNews.setBackgroundColor(c0);
        tvNews.setTextColor(c1);
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
        if(fNews != null) {
            transaction.hide(fNews);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.get().stopTimer();
        App.get().save();
//        Intent intent = new Intent(this,ServiceImp.class);
//        stopService(intent);
    }

    public void refresh() {
        hand.post(new Runnable() {
            @Override
            public void run() {
                BuildingFragment.get().refresh();
                SuppliesFragment.get().refresh();

            }
        });

    }

    /**
     * <--- -  ||  +  ---><br>
     * drawer || building_layout || supplies_layout || settings_layout
     * @param direction greater than 0 - right<br> otherwise - left
     */
    public void swap(int direction){
        switch (currentFragmentLayout){
            case R.id.building_layout: {
                if(direction>0){
                    if(drawer.isDrawerOpen(Gravity.LEFT)){
                        drawer.closeDrawer(Gravity.LEFT);
                    }else {
                        setTabSelection(R.id.supplies_layout);
                    }
                }else{
                    drawer.openDrawer(Gravity.LEFT);
                }
                break;
            }
            case R.id.supplies_layout:{
                if(direction>0){
                    setTabSelection(R.id.news_layout);
                }else{
                    setTabSelection(R.id.building_layout);
                }
                break;
            }
            case R.id.news_layout:{
                if(direction<0){
                    setTabSelection(R.id.supplies_layout);
                }else {
                    setTabSelection(R.id.settings_layout);
                }
                break;
            }
            case R.id.settings_layout:{
                if(direction<0){
                    setTabSelection(R.id.news_layout);
                }
                break;
            }
            default:break;
        }

    }

}

