package sociallol.org.com.sociallol.ui;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.List;

import sociallol.org.com.sociallol.AnalyticsApplication;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.sync.SocialLoLSyncAdapter;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final int FRAGMENT_MATCHES_POSITION = 0;
    public static final int FRAGMENT_FRIENDS_POSITION = 1;
    public static final int FRAGMENT_CHAMPIONS_POSITION = 2;
    public static final int FRAGMENT_SEARCH_POSITION = 3;
    public static final String[] PAGER_TITLES = new String[]{"Recent Matches", "Friends", "Champions", "Search"};
    public static final int SUMMONER_PROFILE_ERROR = 15;
    public static final int SUMMONER_PROFILE_FRIEND_ADDED = 17;
    public static final int SUMMONER_PROFILE = 16;
    public static final String START_SYNC = "start_sync";
    public static final String END_SYNC = "end_sync";
    public static final String ERROR_SYNC = "error_sync";
    public static final String MANUAL_SYNC = "manual_sync";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private SearchView searchView;
    private MenuItem searchMenuItem;
    private ViewPagerAdapter adapter;
    private ProgressDialog progress;
    private Tracker tracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        tracker = application.getDefaultTracker();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // When the visible image changes, send a screen view hit.
        setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                sendScreenPageName();
            }
        });
        sendScreenPageName();

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        SocialLoLSyncAdapter.initializeSyncAdapter(this);


    }
    private int[] tabIcons = {
            R.drawable.ranking,
            R.drawable.ranking,
            R.drawable.champions,
            R.drawable.search
    };
    private void sendScreenPageName() {
        int position = viewPager.getCurrentItem();

        // [START screen_view_hit]
        Log.i(TAG, "Setting screen name: " + PAGER_TITLES[position]);
        tracker.setScreenName("Screen-" + PAGER_TITLES[position]);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
        // [END screen_view_hit]
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MatchesFragment(), getResources().getString(R.string.recent_matches));
        adapter.addFragment(new FriendsMasterFragment(),  getResources().getString(R.string.friends));
        adapter.addFragment(new ChampionsMasterFragment(),  getResources().getString(R.string.champions));
        adapter.addFragment(new SearchMasterFragment(),  getResources().getString(R.string.search));
        viewPager.setAdapter(adapter);
    }

    public void hideSearchView(){
        hideKeyboard();
        if (searchMenuItem != null) {
            searchMenuItem.setVisible(false);
        }
    }

    public void showSearchView(){
        if (searchMenuItem != null){
            searchMenuItem.setVisible(true);
        }
    }

    public void expandSearchView(){
        if (searchView != null){
            searchView.setIconified(false);
        }
    }

    public void collapseSearchView(){
        if (searchView != null){
            searchView.setIconified(true);
        }
    }

    public void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.history_header));

        searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchMasterFragment searchMasterFragment = (SearchMasterFragment)adapter.getItem(FRAGMENT_SEARCH_POSITION);
                searchMasterFragment.queryApiForSummoners(query);
                searchMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean queryTextFocused) {
                if (!queryTextFocused) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                }
            }
        });
        hideSearchView();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void startSummonerDetailActivity(Summoner summoner, boolean isTwoPane, String referenceTag){
        if (!isTwoPane){
            Intent intent = new Intent(MainActivity.this, SummonerDetailActivity.class);
            intent.putExtra("summonerId", summoner.getSummonerId());
            MainActivity.this.startActivityForResult(intent, SUMMONER_PROFILE);
        }else {
            if (referenceTag.equals(SearchFragment.TAG)){
                SearchMasterFragment searchMasterFragment =
                        (SearchMasterFragment) adapter.getItem(FRAGMENT_SEARCH_POSITION);
                searchMasterFragment.replaceDetailFragment(summoner.getSummonerId().toString());
            }else if(referenceTag.equals(FriendsFragment.TAG)){
                FriendsMasterFragment friendsFragment =
                        (FriendsMasterFragment) adapter.getItem(FRAGMENT_FRIENDS_POSITION);
                friendsFragment.replaceDetailFragment(summoner.getSummonerId().toString());
            }
        }
    }

    public void startChampionDetailActivity(Champion champion, boolean mTwoPane) {
        if (!mTwoPane){
            Intent intent = new Intent(this, ChampionDetailActivity.class);
            intent.putExtra("championId", champion.getChampionId());
            this.startActivity(intent);
        }else {
            ChampionsMasterFragment championsMasterFragment =
                    (ChampionsMasterFragment)adapter.getItem(FRAGMENT_CHAMPIONS_POSITION);
            championsMasterFragment.replaceDetailFragment(champion.getChampionId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == SUMMONER_PROFILE) {
            if (resultCode == SUMMONER_PROFILE_ERROR){
                if (data == null) return;
                String result = data.getStringExtra("result");
                if (result.equals("API_ERROR")){
                    //if error occurred.
                    View parentLayout = findViewById(R.id.coordinator_main);
                    Snackbar snackbar = Snackbar.make(parentLayout, getResources().getString(R.string.error_msg),
                            Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Action", null).show();
                }
            }else if(resultCode == SUMMONER_PROFILE_FRIEND_ADDED){
                //call sync adapter
                SocialLoLSyncAdapter.syncImmediately(MainActivity.this);
            }
        }
    }

    // and of course you have to create a BroadcastReceiver
    private BroadcastReceiver startSyncReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){

            //On sync data start
            MainActivity mainActivity = MainActivity.this;
            if (mainActivity == null) return;
            progress = ProgressDialog.show(mainActivity,
                    getResources().getString(R.string.loading),
                    getResources().getString(R.string.please_wait), true);
            Log.d(TAG, "Start data sync: " + intent.getAction());
        }
    };

    private BroadcastReceiver endSyncReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            //On sync data end
            if (progress != null)
                progress.dismiss();
            Log.d(TAG, "End data sync: " + intent.getAction());
        }
    };

    private BroadcastReceiver manualSyncReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            SocialLoLSyncAdapter.syncImmediately(MainActivity.this);
            Log.d(TAG, "Manual sync: " + intent.getAction());
        }
    };

    private BroadcastReceiver errorSyncReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            String typeError = "";
            if (intent != null)
                typeError = intent.getStringExtra(SocialLoLSyncAdapter.TYPE_TAG);

            //On sync data error
            progress.dismiss();

            if (!typeError.isEmpty()){
                View parentLayout = findViewById(R.id.coordinator_main);
                if (typeError.equals(SocialLoLSyncAdapter.SYNC_DATA_ERROR)){
                    if (parentLayout != null){
                        Snackbar snackbar =
                                Snackbar.make(parentLayout,
                                        getResources().getString(R.string.error_msg),
                                        Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Action", null).show();
                    }
                    Log.d(TAG, "Error data sync: " + intent.getAction());
                }
            }

        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter startFilter = new IntentFilter(START_SYNC);
        IntentFilter endFilter = new IntentFilter(END_SYNC);
        IntentFilter errorFilter = new IntentFilter(ERROR_SYNC);
        IntentFilter manualSyncFilter = new IntentFilter(MANUAL_SYNC);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(startSyncReceiver, startFilter);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(endSyncReceiver, endFilter);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(errorSyncReceiver, errorFilter);
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(manualSyncReceiver, manualSyncFilter);

    }



    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(startSyncReceiver);
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(endSyncReceiver);
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(errorSyncReceiver);
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(manualSyncReceiver);

    }

}
