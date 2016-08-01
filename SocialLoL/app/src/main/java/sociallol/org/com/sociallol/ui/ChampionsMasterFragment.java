package sociallol.org.com.sociallol.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;

public class ChampionsMasterFragment extends Fragment {
    public static final String CHAMPIONS_FRAGMENT_TAG = "ChampionsFragment";
    public static final String CHAMPIONS_DETAIL_FRAGMENT_TAG = "ChampionsDetailFragment";
    public static boolean isTwoPane;
    private SocialLoLDB socialLoLDB;

    public ChampionsMasterFragment() {
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
        return inflater.inflate(R.layout.fragment_champions_master, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getActivity());
        if (getView().findViewById(R.id.fragment_champion_detail_container) != null) {
            isTwoPane = true;
            int firstChampionId = socialLoLDB.getFirstChampionId();
            replaceDetailFragment(firstChampionId);
        } else {
            isTwoPane = false;
        }

        //LinearLayout fragmentChampionContainer = (LinearLayout)getView().findViewById(R.id.fragment_champion_container);
        ChampionsFragment championsFragment = ChampionsFragment.create(isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_champion_container, championsFragment, CHAMPIONS_FRAGMENT_TAG)
                .commit();



    }

    public void replaceDetailFragment(int championId) {
        ChampionDetailActivityFragment championDetailActivityFragment =
                ChampionDetailActivityFragment.create(championId, isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_champion_detail_container, championDetailActivityFragment, CHAMPIONS_DETAIL_FRAGMENT_TAG)
                .commit();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            MainActivity context = (MainActivity)getActivity();
            context.hideSearchView();
            context.collapseSearchView();
        }
    }
}
