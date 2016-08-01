package sociallol.org.com.sociallol.ui;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class FriendsFragment extends Fragment{

    public static final String TAG = "FriendsFragment";
    private EmptyRecyclerView mRecyclerView;
    private TextView emptyView;
    private boolean isTwoPane;
    private FriendsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Summoner> friends;
    private SocialLoLDB socialLoLDB;

    public void setIsTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public FriendsFragment() {
        // Required empty public constructor
    }

    public static FriendsFragment create(boolean isTwoPane){
        FriendsFragment friendsFragment = new FriendsFragment();
        friendsFragment.setIsTwoPane(isTwoPane);
        return friendsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getContext());//SocialLoLDBImpl.getInstance(getContext());
        loadFriends();

        mRecyclerView = (EmptyRecyclerView) getView().findViewById(R.id.friends_recycler_view);
        emptyView = (TextView) getView().findViewById(R.id.recyclerview_friends_empty);
        mRecyclerView.setEmptyView(emptyView);

        friends = new ArrayList<>();
        mAdapter = new FriendsAdapter(friends, getActivity(), isTwoPane, TAG);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        loadFriends();
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
        Log.d(TAG, "Data changed?" + intent.getAction());
        loadFriends();
        }
    };

    private void loadFriends() {
        Log.d(TAG,"Loading friends from DB");
        Observable.create(new Observable.OnSubscribe<List<Summoner>>() {
            @Override
            public void call(Subscriber<? super List<Summoner>> subscriber) {
                try {
                    friends = socialLoLDB.getFriends();
                    //completeMatchesInformation(recentMatches);
                    subscriber.onNext(friends);    // Pass on the data to subscriber
                    subscriber.onCompleted();     // Signal about the completion subscriber
                } catch (Exception e) {
                    subscriber.onError(e);        // Signal about the error to subscriber
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Summoner>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Load of recent matches completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onNext(List<Summoner> friends) {
                        Log.d(TAG, "Recent friends fetched from DB, count: " + friends.size());
                        mAdapter.swap(friends);
                    }
                });
    }


}