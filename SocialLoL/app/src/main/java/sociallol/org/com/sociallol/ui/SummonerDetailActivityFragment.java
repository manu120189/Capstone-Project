package sociallol.org.com.sociallol.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLAPI;
import sociallol.org.com.sociallol.api.models.Champion;
import sociallol.org.com.sociallol.api.models.LeagueDto;
import sociallol.org.com.sociallol.api.models.LeagueEntryDto;
import sociallol.org.com.sociallol.api.models.RankedStatsDto;
import sociallol.org.com.sociallol.api.models.StatsRanked;
import sociallol.org.com.sociallol.api.models.SummonerDto;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.SummonerFriend;
import sociallol.org.com.sociallol.utils.GameUtils;
import sociallol.org.com.sociallol.utils.LeagueHelper;
import sociallol.org.com.sociallol.utils.NumberUtils;
import sociallol.org.com.sociallol.utils.PicassoUtils;
import sociallol.org.com.sociallol.utils.SummonerProfileHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class SummonerDetailActivityFragment extends Fragment {

    public static final String TAG = "SummonerDetailActivity";
    public static final String MANUAL_SYNC = "manual_sync";
    public static final String RANKED_SOLO_5_X_5 = "RANKED_SOLO_5x5";
    public static final String RANKED_TEAM_3_X_3 = "RANKED_TEAM_3x3";
    public static final String RANKED_TEAM_5_X_5 = "RANKED_TEAM_5x5";
    public static final String API_ERROR = "API_ERROR";
    public static final String RESULT_TAG = "result";
    public static final String AD_KEY = "ca-app-pub-3940256099942544~3347511713";
    public static LoLAPI lolService;
    private SocialLoLDB socialLoLDB;
    private ProgressDialog progress;
    private FloatingActionButton fab;
    private Target mTarget;
    private String summonerId;
    private boolean isTwoPane;
    private AdView mAdView;
    public SummonerDetailActivityFragment() {
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public void setIsTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lolService = LoLAPI.create();
        socialLoLDB = new SocialLoLDBImpl(getContext());
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(getActivity(), AD_KEY);
    }

    public static SummonerDetailActivityFragment create(String summonerId,
                                                        boolean isTwoPane){
        SummonerDetailActivityFragment summonerDetailActivityFragment =
                new SummonerDetailActivityFragment();
        summonerDetailActivityFragment.setIsTwoPane(isTwoPane);
        summonerDetailActivityFragment.setSummonerId(summonerId);
        return summonerDetailActivityFragment;
    }

    public static SummonerDetailActivityFragment create(boolean isTwoPane){
        SummonerDetailActivityFragment summonerDetailActivityFragment =
                new SummonerDetailActivityFragment();
        summonerDetailActivityFragment.setIsTwoPane(isTwoPane);
        return summonerDetailActivityFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summoner_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) getView().findViewById(R.id.ad_view);
        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);

        if (getActivity() instanceof  SummonerDetailActivity){
            SummonerDetailActivity summonerDetailActivity = (SummonerDetailActivity) getActivity();
            summonerId = summonerDetailActivity.getSummonerId();
        }
        if (summonerId == null)
            return;

        loadSummonerInfo(summonerId);
        setFabButton(Long.valueOf(summonerId));

    }
    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }
    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }
    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    private void setFabButton(final Long summonerId) {
        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        if (socialLoLDB.summonerFriendExistsInDatabase(summonerId)){
            fab.setImageDrawable(ContextCompat.getDrawable(getContext(),
                    android.support.design.R.drawable.abc_btn_rating_star_on_mtrl_alpha));
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(getContext(),
                    android.support.design.R.drawable.abc_btn_rating_star_off_mtrl_alpha));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (socialLoLDB.summonerFriendExistsInDatabase(summonerId)) {
                    socialLoLDB.deleteSummonerFriend(summonerId);
                    socialLoLDB.deleteSummoner(summonerId);
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(),
                            android.support.design.R.drawable.abc_btn_rating_star_off_mtrl_alpha));
                    Snackbar.make(view, getActivity().getResources().getString(R.string.summoner_removed),
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    SummonerDetailActivity.userAddedOrDeleted = SummonerDetailActivity.DELETED;
                } else {
                    SummonerFriend summonerFriend = new SummonerFriend();
                    summonerFriend.setSummonerId(summonerId);
                    socialLoLDB.saveSummonerFriend(summonerFriend);
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(),
                            android.support.design.R.drawable.abc_btn_rating_star_on_mtrl_alpha));
                    Snackbar.make(view, getActivity().getResources().getString(R.string.summoner_added),
                            Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    SummonerDetailActivity.userAddedOrDeleted = SummonerDetailActivity.ADDED;
                }
                if (isTwoPane)
                    sendDataUpdatedBroadcast();
            }
        });


    }

    public void sendDataUpdatedBroadcast(){
        Intent i = new Intent(MANUAL_SYNC);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(i);
    }

    public void loadSummonerDataInUI(final RankedStatsDto rankedStatsResponse,
                                     final Map<String, List<LeagueDto>> leagueResponse,
                                     final Map<String, SummonerDto> summonerResponse,
                                     final String summonerId){

        Observable.create(new Observable.OnSubscribe<SummonerProfileHelper>() {
            @Override
            public void call(Subscriber<? super SummonerProfileHelper> subscriber) {
                subscriber.onNext(handleSummonerData(rankedStatsResponse, leagueResponse, summonerResponse, summonerId));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SummonerProfileHelper>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadSummonerDataInUI completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onNext(SummonerProfileHelper summonerProfileHelper) {
                        updateUI(summonerProfileHelper);
                    }
                });
    }

    public void updateUI(SummonerProfileHelper sph){
        LinearLayout linearLayout = (LinearLayout)
                getView().findViewById(R.id.summoner_detail_container);
        linearLayout.setVisibility(View.VISIBLE);
        TextView summonerDetailEmptyView = (TextView)
                getView().findViewById(R.id.summoner_detail_empty);
        summonerDetailEmptyView.setVisibility(View.GONE);
        ImageView championImageView = (ImageView) getView().findViewById(R.id.champion_image);
        TextView summonerNameView = (TextView) getView().findViewById(R.id.champion_name);
        TextView regionView = (TextView) getView().findViewById(R.id.champion_role);
        TextView levelView = (TextView) getView().findViewById(R.id.level);
        TextView avgKdaView = (TextView) getView().findViewById(R.id.average_kda);
        TextView avgGoldView = (TextView) getView().findViewById(R.id.average_gold);
        TextView avgCreepScoreView = (TextView) getView().findViewById(R.id.creep_score);
        TextView winRateView = (TextView) getView().findViewById(R.id.average_win_rate);
        final RelativeLayout genericInfoLayout = (RelativeLayout) getView();

        ImageView champ1View = (ImageView) getView().findViewById(R.id.champ1);
        TextView kda1View = (TextView) getView().findViewById(R.id.kda1);
        TextView winrate1View = (TextView) getView().findViewById(R.id.winrate1);

        ImageView champ2View = (ImageView) getView().findViewById(R.id.champ2);
        TextView kda2View = (TextView) getView().findViewById(R.id.kda2);
        TextView winrate2View = (TextView) getView().findViewById(R.id.winrate2);

        ImageView champ3View = (ImageView) getView().findViewById(R.id.champ3);
        TextView kda3View = (TextView) getView().findViewById(R.id.kda3);
        TextView winrate3View = (TextView) getView().findViewById(R.id.winrate3);

        ImageView champ4View = (ImageView) getView().findViewById(R.id.champ4);
        TextView kda4View = (TextView) getView().findViewById(R.id.kda4);
        TextView winrate4View = (TextView) getView().findViewById(R.id.winrate4);

        ImageView leagueTeam5vs5View = (ImageView) getView().findViewById(R.id.team_5vs5_league);
        ImageView leagueTeam3vs3View = (ImageView) getView().findViewById(R.id.team_3vs3_league);
        ImageView leagueSolo5vs5View = (ImageView) getView().findViewById(R.id.solo_5vs5_league);

        TextView league5vs5TierView = (TextView) getView().findViewById(R.id.league_5vs5_tier);
        TextView leagueSolo5vs5TierView = (TextView) getView().findViewById(R.id.league_solo_5vs5_tier);
        TextView league3vs3TierView = (TextView) getView().findViewById(R.id.league_3vs3_tier);

        TextView league5vs5PointsView = (TextView) getView().findViewById(R.id.league_team_5vs5_points);
        TextView league3vs3PointsView = (TextView) getView().findViewById(R.id.league_team_3vs3_points);
        TextView leagueSolo5vs5PointsView = (TextView) getView().findViewById(R.id.league_solo_5vs5_points);

        PicassoUtils.loadProfileIcon(getContext(), sph.getProfileIconId(), championImageView);
        summonerNameView.setText(sph.getName());
        regionView.setText("Latin America North");
        levelView.setText("Level " + sph.getSummonerLevel());
        mTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d(TAG, "Bitmap Loaded");
                if (genericInfoLayout != null)
                    genericInfoLayout.setBackground(new BitmapDrawable(getContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d(TAG, "Bitmap FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d(TAG, "Bitmap Prepare Load");
            }
        };
        PicassoUtils.loadChampionSplashLoading(getContext(), sph.getChampionKey1(), mTarget);
        Integer kills = sph.getAveragesGeneral()[0];
        Integer deaths = sph.getAveragesGeneral()[1];
        Integer assists = sph.getAveragesGeneral()[2];
        Integer winRate = sph.getAveragesGeneral()[3];
        avgKdaView.setText(kills + "   /   " + deaths + "   /   " + assists);
        Double averageGoldGeneral = sph.getAverageGoldGeneral();
        avgGoldView.setText(NumberUtils.format(Long.valueOf(Math.round(averageGoldGeneral))));
        avgCreepScoreView.setText(sph.getAveragesGeneral()[4] + "");
        winRateView.setText(winRate + "%");

        PicassoUtils.loadChampionThumbnail(getContext(), sph.getChampionKey1(), champ1View);
        PicassoUtils.loadChampionThumbnail(getContext(), sph.getChampionKey2(), champ2View);
        PicassoUtils.loadChampionThumbnail(getContext(), sph.getChampionKey3(), champ3View);
        PicassoUtils.loadChampionThumbnail(getContext(), sph.getChampionKey4(), champ4View);

        Integer[] championAverages1 = sph.getChampionAverages1();
        if (championAverages1 != null){
            kda1View.setText(championAverages1[0] + "/" + championAverages1[1] + "/" +
                    championAverages1[2]);
            winrate1View.setText(getActivity().getResources().getString(R.string.winrate) + " " + championAverages1[3] + "%");
        }
        Integer[] championAverages2 = sph.getChampionAverages2();
        if (championAverages2 != null){
            kda2View.setText(championAverages2[0] + "/" + championAverages2[1] + "/" +
                    championAverages2[2]);
            winrate2View.setText(getActivity().getResources().getString(R.string.winrate) + " " + championAverages2[3] + "%");
        }
        Integer[] championAverages3 = sph.getChampionAverages3();
        if (championAverages3 != null){
            kda3View.setText(championAverages3[0] + "/" + championAverages3[1] + "/" +
                    championAverages3[2]);
            winrate3View.setText(getActivity().getResources().getString(R.string.winrate) + " " + championAverages3[3] + "%");
        }
        Integer[] championAverages4 = sph.getChampionAverages4();
        if (championAverages4 != null){
            kda4View.setText(championAverages4[0] + "/" + championAverages4[1] + "/" +
                    championAverages4[2]);
            winrate4View.setText(getActivity().getResources().getString(R.string.winrate)  + " " + championAverages4[3] + "%");
        }

        //The league's queue type. (Legal values: RANKED_SOLO_5x5, RANKED_TEAM_3x3, RANKED_TEAM_5x5)
        List<LeagueHelper> leagues = sph.getLeagues();
        if (leagues != null && leagues.size() > 0){
            for (LeagueHelper leagueHelper: leagues){
                if (leagueHelper.getQueue().equals(RANKED_SOLO_5_X_5)){
                    if (leagueHelper.getTier() != null){
                        PicassoUtils.loadLeagueImage(getContext(),
                                GameUtils.leagues.get(leagueHelper.getTier()), leagueSolo5vs5View);
                        leagueSolo5vs5TierView.setText(leagueHelper.getTier() + " " + leagueHelper.getDivision());
                        leagueSolo5vs5PointsView.setText(leagueHelper.getLeaguePoints() + " Pts.");
                    }
                }else if(leagueHelper.getQueue().equals(RANKED_TEAM_3_X_3)){
                    if (leagueHelper.getTier() != null){
                        PicassoUtils.loadLeagueImage(getContext(),
                                GameUtils.leagues.get(leagueHelper.getTier()), leagueTeam3vs3View);
                        league3vs3TierView.setText(leagueHelper.getTier() + " " + leagueHelper.getDivision());
                        league3vs3PointsView.setText(leagueHelper.getLeaguePoints() + " Pts.");
                    }
                }else if(leagueHelper.getQueue().equals(RANKED_TEAM_5_X_5)){
                    if (leagueHelper.getTier() != null){
                        PicassoUtils.loadLeagueImage(getContext(),
                                GameUtils.leagues.get(leagueHelper.getTier()), leagueTeam5vs5View);
                        league5vs5TierView.setText(leagueHelper.getTier() + " " + leagueHelper.getDivision());
                        league5vs5PointsView.setText(leagueHelper.getLeaguePoints() + " Pts.");
                    }

                }
            }
        }
        if (progress != null && progress.isShowing())
            progress.dismiss();
    }

    public SummonerProfileHelper handleSummonerData(RankedStatsDto rankedStatsResponse,
                                   Map<String, List<LeagueDto>> leagueResponse,
                                   Map<String, SummonerDto> summonerResponse,
                                   String summonerId){

        SummonerProfileHelper summonerProfileHelper = new SummonerProfileHelper();
        SummonerDto summonerDto = summonerResponse.get(summonerId);
        String name = summonerDto.getName();
        summonerProfileHelper.setName(name);
        Integer summonerLevel = summonerDto.getSummonerLevel();
        summonerProfileHelper.setSummonerLevel(summonerLevel);
        Integer profileIconId = summonerDto.getProfileIconId();
        summonerProfileHelper.setProfileIconId(profileIconId);
        //averages
        if (rankedStatsResponse == null)
            return null;

        //order champions by most played
        List<Champion> champions = rankedStatsResponse.getChampions();
        if (champions == null)
            return null;

        Collections.sort(champions, new Comparator<Champion>() {
            @Override
            public int compare(Champion o1, Champion o2) {
                return o2.getStats().getTotalSessionsPlayed()
                        .compareTo(o1.getStats().getTotalSessionsPlayed());
            }
        });

        //we get the first position with id == 0
        //From the API doc: Note that champion ID 0 represents the combined stats for all champions.
        //For static information correlating to champion IDs, please refer to the LoL Static Data API.
        Champion champion0 = champions.get(0);
        StatsRanked stats0 = champion0.getStats();
        if (stats0 == null)
            return null;

        Integer totalSessionsPlayed = stats0.getTotalSessionsPlayed();
        Integer totalGoldEarned = stats0.getTotalGoldEarned();
        Double averageGoldGeneral = Double.valueOf(totalGoldEarned) / Double.valueOf(totalSessionsPlayed);
        summonerProfileHelper.setAverageGoldGeneral(averageGoldGeneral);
        Integer[] averagesGeneral = calculateGenericAverages(stats0);
        summonerProfileHelper.setAveragesGeneral(averagesGeneral);

        //get first champion info
        Integer[] championAverages1 = handleChampionStats(champions, 1, summonerProfileHelper);
        summonerProfileHelper.setChampionAverages1(championAverages1);

        //get second champion info
        Integer[] championAverages2 = handleChampionStats(champions, 2, summonerProfileHelper);
        summonerProfileHelper.setChampionAverages2(championAverages2);
        //get third champion info
        Integer[] championAverages3 = handleChampionStats(champions, 3, summonerProfileHelper);
        summonerProfileHelper.setChampionAverages3(championAverages3);
        //get fourth champion info
        Integer[] championAverages4 = handleChampionStats(champions, 4, summonerProfileHelper);
        summonerProfileHelper.setChampionAverages4(championAverages4);
        //league info
        List<LeagueDto> leagueDtos = leagueResponse.get(summonerId);
        if (leagueDtos.size() > 0){
            List<LeagueHelper> leagueHelpers = new ArrayList<>();
            for (LeagueDto leagueDto: leagueDtos){
                LeagueHelper leagueHelper = new LeagueHelper();
                LeagueEntryDto leagueEntryDto = leagueDto.getEntries().get(0);
                String tier = leagueDto.getTier();
                leagueHelper.setTier(tier);
                String division = leagueEntryDto.getDivision();
                leagueHelper.setDivision(division);
                Integer leaguePoints = leagueEntryDto.getLeaguePoints();
                leagueHelper.setLeaguePoints(leaguePoints);
                String queue = leagueDto.getQueue();
                leagueHelper.setQueue(queue);
                leagueHelpers.add(leagueHelper);
            }
            summonerProfileHelper.setLeagues(leagueHelpers);
        }
        return summonerProfileHelper;

    }

    private void setChampionKey(SummonerProfileHelper summonerProfileHelper, Champion champion, int position) {
        sociallol.org.com.sociallol.database.models.Champion championDB = socialLoLDB.getChampion(champion.getId());
        if (championDB != null){
            if (position == 1)
                summonerProfileHelper.setChampionKey1(championDB.getChampionKey());
            if (position == 2)
                summonerProfileHelper.setChampionKey2(championDB.getChampionKey());
            if (position == 3)
                summonerProfileHelper.setChampionKey3(championDB.getChampionKey());
            if (position == 4)
                summonerProfileHelper.setChampionKey4(championDB.getChampionKey());
        }
    }

    private Integer[] handleChampionStats(List<Champion> champions, int position, SummonerProfileHelper summonerProfileHelper) {
        try{
            Champion champion = champions.get(position);
            StatsRanked stats1 = champion.getStats();
            Integer[] champ1Stats = calculateGenericAverages(stats1);

            setChampionKey(summonerProfileHelper, champion, position);

            return champ1Stats;
        }catch (Exception e){
            return null;
        }
    }

    private Integer[] calculateGenericAverages(StatsRanked stats0) {
        Integer totalChampionKills = stats0.getTotalChampionKills();
        Integer totalSessionsPlayed = stats0.getTotalSessionsPlayed();
        Integer averageKills = totalChampionKills / totalSessionsPlayed;

        Integer totalDeathsPerSession = stats0.getTotalDeathsPerSession();
        Integer averageDeaths = totalDeathsPerSession / totalSessionsPlayed;

        Integer totalAssists = stats0.getTotalAssists();
        Integer averageAssists = totalAssists / totalSessionsPlayed;

        Integer totalSessionsWon = stats0.getTotalSessionsWon();
        Integer winRate = (totalSessionsWon * 100) / totalSessionsPlayed;

        Integer totalMinionKills = stats0.getTotalMinionKills();
        Integer averageMinionKillsGeneral = totalMinionKills / totalSessionsPlayed;

        return new Integer[]{averageKills, averageDeaths, averageAssists, winRate, averageMinionKillsGeneral};
    }

    public void loadSummonerInfo(final String summonerId){

        final long startTime = System.nanoTime();
        if (!isTwoPane)
            progress = ProgressDialog.show(getContext(),
                    getActivity().getResources().getString(R.string.getting_summoner_data),
                    getActivity().getResources().getString(R.string.please_wait), true);
        Observable.zip(lolService.getSummonerStats(summonerId),
                lolService.getLeagues(summonerId),
                lolService.getSummoners(summonerId),
                new Func3<RankedStatsDto, Map<String,List<LeagueDto>>, Map<String,SummonerDto>, Boolean>() {
                    @Override
                    public Boolean call(RankedStatsDto rankedStatsResponse,
                                        Map<String, List<LeagueDto>> leagueResponse,
                                        Map<String, SummonerDto> summonerResponse) {
                        long endTime = System.nanoTime();
                        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
                        Log.d(TAG,"Duration of loadSummonerInfo: " + duration);
                        loadSummonerDataInUI(rankedStatsResponse, leagueResponse, summonerResponse, summonerId);
                        return true;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadSummonerInfo completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (progress != null)
                            progress.dismiss();
                        FragmentActivity activity = getActivity();
                        Log.e(TAG, "Error", e);
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(RESULT_TAG, API_ERROR);
                        activity.setResult(MainActivity.SUMMONER_PROFILE_ERROR, returnIntent);
                        activity.finish();
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {}
                });

    }
}
