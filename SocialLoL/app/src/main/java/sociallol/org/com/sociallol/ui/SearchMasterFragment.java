package sociallol.org.com.sociallol.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;

public class SearchMasterFragment extends Fragment {
    public static final String SEARCH_FRAGMENT_TAG = "SearchFragment";
    public static final String SEARCH_DETAIL_FRAGMENT_TAG = "SearchDetailFragment";
    public static boolean isTwoPane;
    private SocialLoLDB socialLoLDB;

    public SearchMasterFragment() {
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
        return inflater.inflate(R.layout.fragment_search_master, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getActivity());
        if (getView().findViewById(R.id.fragment_search_detail_container) != null) {
            isTwoPane = true;
            replaceDetailFragment(null);
        } else {
            isTwoPane = false;
        }

        SearchFragment searchFragment = SearchFragment.create(isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_search_container, searchFragment, SEARCH_FRAGMENT_TAG)
                .commit();
    }

    public void replaceDetailFragment(String summonerId) {
        SummonerDetailActivityFragment summonerDetailActivityFragment =
                SummonerDetailActivityFragment.create(summonerId, isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_search_detail_container, summonerDetailActivityFragment, SEARCH_DETAIL_FRAGMENT_TAG)
                .commit();
    }

    public void queryApiForSummoners(String query){
        SearchFragment searchFragment =
                (SearchFragment)getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag(SEARCH_FRAGMENT_TAG);
        if (searchFragment != null){
            searchFragment.queryApiForSummoners(query);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MainActivity context = (MainActivity)getActivity();
            context.showSearchView();
            //context.expandSearchView();
        }
    }
}
