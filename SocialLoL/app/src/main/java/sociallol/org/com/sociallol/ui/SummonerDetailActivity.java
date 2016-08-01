package sociallol.org.com.sociallol.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sociallol.org.com.sociallol.R;

public class SummonerDetailActivity extends AppCompatActivity {
    public static final int NONE = -1;
    public static final int DELETED = 0;
    public static final int ADDED = 1;
    public static final String FRIEND_ADDED = "FRIEND_ADDED";
    public static final String SUMMONER_ID_TAG = "summonerId";
    public static final String RESULT_TAG = "result";
    public static int userAddedOrDeleted = NONE;

    private String summonerId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoner_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Long summonerIdExtra = getIntent().getLongExtra(SUMMONER_ID_TAG, 0L);
        if (summonerIdExtra == 0) return;
        summonerId = summonerIdExtra.toString();
    }

    @Override
    public void onBackPressed() {
        if (userAddedOrDeleted == ADDED || userAddedOrDeleted == DELETED){
            Intent returnIntent = new Intent();
            returnIntent.putExtra(RESULT_TAG, FRIEND_ADDED);
            setResult(MainActivity.SUMMONER_PROFILE_FRIEND_ADDED, returnIntent);
            userAddedOrDeleted = NONE;
        }
        super.onBackPressed();
    }

    public String getSummonerId() {
        return summonerId;
    }
}
