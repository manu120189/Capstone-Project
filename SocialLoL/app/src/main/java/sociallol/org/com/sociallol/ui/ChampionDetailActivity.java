package sociallol.org.com.sociallol.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sociallol.org.com.sociallol.R;

public class ChampionDetailActivity extends AppCompatActivity {

    private int championId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int championIdExtra = getIntent().getIntExtra("championId", 0);
        if (championIdExtra == 0) return;
        championId = championIdExtra;
    }

    public int getChampionId() {
        return championId;
    }
}
