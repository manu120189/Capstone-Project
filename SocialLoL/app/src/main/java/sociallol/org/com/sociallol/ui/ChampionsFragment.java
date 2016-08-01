package sociallol.org.com.sociallol.ui;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class ChampionsFragment extends Fragment{

    public static final String TAG = "ChampionsFragment";
    private EmptyRecyclerView mRecyclerView;
    private TextView emptyView;
    private ChampionsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Champion> champions;
    private SocialLoLDB socialLoLDB;
    private boolean isTwoPane;

    public void setTwoPane(boolean twoPane) {
        this.isTwoPane = twoPane;
    }

    public ChampionsFragment() {
        // Required empty public constructor
    }

    public static ChampionsFragment create(boolean isTwoPane){
        ChampionsFragment championsFragment = new ChampionsFragment();
        championsFragment.setTwoPane(isTwoPane);
        return championsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champions, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getContext());
        loadChampions();

        mRecyclerView = (EmptyRecyclerView) getActivity().findViewById(R.id.champions_recycler_view);
        emptyView = (TextView) getActivity().findViewById(R.id.recyclerview_champions_empty);
        mRecyclerView.setEmptyView(emptyView);


        champions = new ArrayList<>();
        mAdapter = new ChampionsAdapter(champions, getActivity(), isTwoPane);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        //mRecyclerView.setLayoutManager(llm);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void loadChampions() {
        Log.d(TAG, "Loading champions from DB");
        Observable.create(new Observable.OnSubscribe<List<Champion>>() {
            @Override
            public void call(Subscriber<? super List<Champion>> subscriber) {
                try {
                    champions = socialLoLDB.getChampions();
                    subscriber.onNext(champions);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Champion>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Load of champions completed.");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onNext(List<Champion> champions) {
                        Log.d(TAG, "Champions fetched from DB, count: " + champions.size());
                        mAdapter.swap(champions);
                    }
                });
    }


}