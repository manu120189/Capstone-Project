package sociallol.org.com.sociallol.ui;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLAPI;
import sociallol.org.com.sociallol.api.models.RecentGamesDto;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.SummonerSpell;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class MatchesFragment extends Fragment{
    public static final String TAG = "MatchesFragment";
    private EmptyRecyclerView mRecyclerView;
    private TextView emptyView;

    private MatchesAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<RecentMatch> recentMatches;
    private SocialLoLDB socialLoLDB;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getContext());//SocialLoLDBImpl.getInstance(getContext());
        loadRecentMatches();

        mRecyclerView = (EmptyRecyclerView) getActivity().findViewById(R.id.matches_recycler_view);
        emptyView = (TextView) getActivity().findViewById(R.id.recyclerview_recent_matches_empty);
        mRecyclerView.setEmptyView(emptyView);

        recentMatches = new ArrayList<>();
        mAdapter = new MatchesAdapter(recentMatches, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadRecentMatches();
        // the filter matches the broadcast
        IntentFilter filter = new IntentFilter("updated_data");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(myReceiver, filter);
    }

    @Override
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(myReceiver);
    }

    // and of course you have to create a BroadcastReceiver
    private BroadcastReceiver myReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent){
            // here you know that your data have changed, so it's time to reload it
            //reloadData = new ReloadData().execute(); // you should cancel this task onPause()
            Log.d(TAG,"Data changed?" + intent.getAction());
            loadRecentMatches();
        }
    };
    private void completeMatchesInformation(List<RecentMatch> recentMatches){
        for (RecentMatch recentMatch: recentMatches){
            String summonerName = socialLoLDB.getSummonerName(recentMatch.getSummonerId());
            if (summonerName != null)
                recentMatch.setSummonerName(summonerName);
            else
                recentMatch.setSummonerName("Player " + recentMatch.getSummonerId());
            SummonerSpell summonerSpell1 = socialLoLDB.getSummonerSpell(recentMatch.getSpell1());
            if (summonerSpell1 != null)
                recentMatch.setSpell1Name(summonerSpell1.getSummonerSpellName());
            else
                recentMatch.setSpell1Name("SummonerFlash");

            SummonerSpell summonerSpell2 = socialLoLDB.getSummonerSpell(recentMatch.getSpell2());
            if (summonerSpell2 != null)
                recentMatch.setSpell2Name(summonerSpell2.getSummonerSpellName());
            else
                recentMatch.setSpell2Name("SummonerTeleport");

            Champion champion = socialLoLDB.getChampion(recentMatch.getChampionId());
            if (champion != null)
                recentMatch.setChampionKey(champion.getChampionKey());
        }
    }
    private void loadRecentMatches() {
        Log.d(TAG,"Loading recent matches from DB");
        Observable.create(new Observable.OnSubscribe<List<RecentMatch>>() {
            @Override
            public void call(Subscriber<? super List<RecentMatch>> subscriber) {
                try {
                    recentMatches = socialLoLDB.getRecentMatches();
                    completeMatchesInformation(recentMatches);
                    subscriber.onNext(recentMatches);    // Pass on the data to subscriber
                    subscriber.onCompleted();     // Signal about the completion subscriber
                } catch (Exception e) {
                    subscriber.onError(e);        // Signal about the error to subscriber
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RecentMatch>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG,"Load of recent matches completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onNext(List<RecentMatch> recentMatches) {
                        Log.d(TAG, "Recent matches fetched from DB, count: " + recentMatches.size());
                        mAdapter.swap(recentMatches);
                    }
                });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MainActivity context = (MainActivity)getActivity();
            context.hideSearchView();
        }
    }
}