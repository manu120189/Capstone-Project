package sociallol.org.com.sociallol.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLAPI;
import sociallol.org.com.sociallol.api.models.SummonerDto;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class SearchFragment extends Fragment {

    public static final String TAG = "SearchFragment";
    public static LoLAPI lolService;
    private EmptyRecyclerView mRecyclerView;
    private TextView emptyView;
    private FriendsAdapter mAdapter;
    private List<Summoner> summoners;
    private ProgressDialog progress;
    private boolean isTwoPane;


    public void setIsTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment create(boolean isTwoPane){
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setIsTwoPane(isTwoPane);
        return searchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lolService = LoLAPI.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        //loadFriends();

        mRecyclerView = (EmptyRecyclerView) getView().findViewById(R.id.search_recycler_view);
        emptyView = (TextView) getView().findViewById(R.id.recyclerview_search_empty);
        mRecyclerView.setEmptyView(emptyView);

        summoners = new ArrayList<>();
        mAdapter = new FriendsAdapter(summoners, getActivity(), isTwoPane, TAG);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
    }

    public void queryApiForSummoners(final String summonerName){
        progress = ProgressDialog.show(getContext(), getActivity().getResources().getString(R.string.searching),
                getActivity().getResources().getString(R.string.please_wait), true);
        lolService.getSummonersByName(summonerName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, SummonerDto>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "Search for summoners completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        progress.dismiss();
                        View parentLayout = getActivity().findViewById(R.id.coordinator_main);
                        Snackbar snackbar = Snackbar.make(parentLayout,
                                getActivity().getResources().getString(R.string.error_msg), Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Action", null).show();
                        Log.e(TAG, e.getLocalizedMessage(), e);
                    }

                    @Override
                    public void onNext(Map<String, SummonerDto> stringSummonerDtoMap) {
                        if (stringSummonerDtoMap.containsKey(summonerName)) {
                            SummonerDto summonerDto = stringSummonerDtoMap.get(summonerName);
                            handleSummonerResponse(summonerDto);
                        }
                        else {
                            View parentLayout = getActivity().findViewById(R.id.coordinator_main);
                            Snackbar snackbar = Snackbar.make(parentLayout,
                                    getActivity().getResources().getString(R.string.no_results) +
                                    summonerName, Snackbar.LENGTH_INDEFINITE);
                            snackbar.setAction("Action", null).show();
                            //handle no results find for that name
                            Log.d(TAG, "Results not found for summoner name: " + summonerName);
                        }
                        progress.dismiss();
                    }
                });
    }

    public void handleSummonerResponse(SummonerDto summonerDto){
        Summoner summoner = new Summoner();
        summoner.setSummonerId(summonerDto.getId());
        summoner.setSummonerName(summonerDto.getName());
        summoner.setProfileIconId(summonerDto.getProfileIconId());
        summoner.setRevisionDate(summonerDto.getRevisionDate());
        summoner.setSummonerLevel(summonerDto.getSummonerLevel());
        summoner.setRegion(BuildConfig.LOL_DEFAULT_REGION);
        List<Summoner> summoners = new ArrayList<>();
        summoners.add(summoner);
        mAdapter.swap(summoners);
    }
}
