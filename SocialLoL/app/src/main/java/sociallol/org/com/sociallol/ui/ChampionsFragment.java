package sociallol.org.com.sociallol.ui;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.loaders.ChampionsLoader;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.utils.EmptyRecyclerView;

public class ChampionsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Champion>> {

    public static final String TAG = "ChampionsFragment";
    private EmptyRecyclerView mRecyclerView;
    private TextView emptyView;
    private ChampionsAdapter mAdapter;
    private List<Champion> champions;
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

        mRecyclerView = (EmptyRecyclerView) getActivity().findViewById(R.id.champions_recycler_view);
        emptyView = (TextView) getActivity().findViewById(R.id.recyclerview_champions_empty);
        mRecyclerView.setEmptyView(emptyView);

        champions = new ArrayList<>();
        mAdapter = new ChampionsAdapter(champions, getActivity(), isTwoPane);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        mRecyclerView.setAdapter(mAdapter);

        initChampionsLoader();

    }

    private void initChampionsLoader(){
        getActivity().getLoaderManager().initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<List<Champion>> onCreateLoader(int id, Bundle args) {
        return new ChampionsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Champion>> loader, List<Champion> data) {
        mAdapter.swap(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Champion>> loader) {
        mAdapter.swap(null);
    }
}