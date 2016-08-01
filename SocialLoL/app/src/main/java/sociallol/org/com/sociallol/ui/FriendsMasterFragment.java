package sociallol.org.com.sociallol.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsMasterFragment extends Fragment {


    public static final String FRIENDS_FRAGMENT_TAG = "FriendsFragment";
    public static final String FRIENDS_DETAIL_FRAGMENT_TAG = "FriendsDetailFragment";
    public static boolean isTwoPane;
    private SocialLoLDB socialLoLDB;

    public FriendsMasterFragment() {
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
        return inflater.inflate(R.layout.fragment_friends_master, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        socialLoLDB = new SocialLoLDBImpl(getActivity());
        if (getView().findViewById(R.id.fragment_friend_detail_container) != null) {
            isTwoPane = true;
            Long firstSummonerId = socialLoLDB.getFirstSummonerId();
            if (firstSummonerId != null)
                replaceDetailFragment(firstSummonerId.toString());
        } else {
            isTwoPane = false;
        }

        //LinearLayout fragmentChampionContainer = (LinearLayout)getView().findViewById(R.id.fragment_champion_container);
        FriendsFragment friendsFragment = FriendsFragment.create(isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_friend_container, friendsFragment, FRIENDS_FRAGMENT_TAG)
                .commit();



    }

    public void replaceDetailFragment(String summonerId) {
        SummonerDetailActivityFragment summonerDetailActivityFragment =
                SummonerDetailActivityFragment.create(summonerId, isTwoPane);
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_friend_detail_container, summonerDetailActivityFragment, FRIENDS_DETAIL_FRAGMENT_TAG)
                .commit();
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
