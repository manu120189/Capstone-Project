package sociallol.org.com.sociallol.ui;
import android.app.LoaderManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
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

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.loaders.RecentMatchesLoader;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class MatchesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<RecentMatch>> {
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
        initRecentMatchesLoader();

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
        initRecentMatchesLoader();
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
            initRecentMatchesLoader();
        }
    };


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MainActivity context = (MainActivity)getActivity();
            context.hideSearchView();
        }
    }


    private void initRecentMatchesLoader(){
        getActivity().getLoaderManager().initLoader(2, null, this).forceLoad();
    }

    @Override
    public Loader<List<RecentMatch>> onCreateLoader(int id, Bundle args) {
        return new RecentMatchesLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<RecentMatch>> loader, List<RecentMatch> data) {
        mAdapter.swap(data);
    }

    @Override
    public void onLoaderReset(Loader<List<RecentMatch>> loader) {
        mAdapter.swap(null);
    }
}