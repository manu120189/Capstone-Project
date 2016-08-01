package sociallol.org.com.sociallol.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLAPI;
import sociallol.org.com.sociallol.api.LoLStaticAPI;
import sociallol.org.com.sociallol.api.models.ChampionDetail;
import sociallol.org.com.sociallol.api.models.ChampionDto;
import sociallol.org.com.sociallol.api.models.ChampionListDto;
import sociallol.org.com.sociallol.api.models.ChampionStaticInfo;
import sociallol.org.com.sociallol.api.models.LeagueEntryDto;
import sociallol.org.com.sociallol.api.models.Game;
import sociallol.org.com.sociallol.api.models.LeagueDto;
import sociallol.org.com.sociallol.api.models.RecentGamesDto;
import sociallol.org.com.sociallol.api.models.SummonerDto;
import sociallol.org.com.sociallol.api.models.SummonerSpellDto;
import sociallol.org.com.sociallol.api.models.SummonerSpellListDto;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.database.models.SummonerFriend;
import sociallol.org.com.sociallol.database.models.SummonerSpell;
import sociallol.org.com.sociallol.utils.GameUtils;

public class SocialLoLSyncAdapter extends AbstractThreadedSyncAdapter {

    private static final String TAG = "SocialLoLSyncAdapter";
    public static final String LAST_STATIC_SYNC_TAG = "LAST_STATIC_SYNC_TAG";
    public static final String SYNC_DATA_ERROR = "SYNC_DATA";
    public static final String NO_FRIENDS_WARNING = "NO_FRIENDS";
    public static final String TYPE_TAG = "TYPE";
    private final AccountManager mAccountManager;
    private SocialLoLDB socialLoLDB;
    public static LoLAPI lolService;
    public static LoLStaticAPI lolStaticService;
    private Context context;

    // Interval at which to sync with the Lol, in seconds.
    // 60 seconds (1 minute) * 180 = 3 hours
    public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
    private static final long WEEK_IN_MILLIS = 1000 * 60 * 60 * 24 * 7;

    private int currentCountOfFriendsFetched = 0;
    private int expectedFriendsFetchedToSendNotification = 0;

    public SocialLoLSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        this.context = context;
        mAccountManager = AccountManager.get(context);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority,
                              ContentProviderClient provider, SyncResult syncResult) {

        lolService = LoLAPI.create();
        lolStaticService = LoLStaticAPI.create();
        socialLoLDB = new SocialLoLDBImpl(getContext());//SocialLoLDBImpl.getInstance(context);

        Log.d(TAG, "############################Data sync has started!########################################");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        long lastStaticSync = prefs.getLong(LAST_STATIC_SYNC_TAG, 0);

        //load first time or once a week
        //check if has passed 1 week or if is the first time
        if (System.currentTimeMillis() - lastStaticSync >= WEEK_IN_MILLIS) {
            // Last static sync was more than 1 week ago, let's resync static information.
            loadStaticData();
        }else {
            //load information and progress about friends
            loadDynamicDataFromFriendsList();
        }

    }

    private void refreshEmptySummonerList(){
        socialLoLDB.clearRecentMatches();
        sendUpdatedDataBroadcast();
        sendEndSyncBroadcast();
    }

    private void loadDynamicDataFromFriendsList(){
        List<SummonerFriend> summonerFriends = socialLoLDB.getSummonerFriends();
        List<Long> summonerIds = new ArrayList<>();
        if (summonerFriends == null || summonerFriends.size() == 0){
            refreshEmptySummonerList();
            return;
        }
        for (SummonerFriend summonerFriend: summonerFriends){
            summonerIds.add(summonerFriend.getSummonerId());
        }
        Long[] summonerIdsArray = summonerIds.toArray(new Long[summonerIds.size()]);
        loadDynamicData(summonerIdsArray);
    }

    private void refreshLastStaticSync(){
        //refreshing last sync
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(LAST_STATIC_SYNC_TAG, System.currentTimeMillis());
        editor.commit();
    }


    private void loadStaticData(){
        sendStartSyncBroadcast();
        Observable
                .zip(lolStaticService.getSummonerSpellInfo(),
                        lolStaticService.getChampionStatic(),
                        new Func2<SummonerSpellListDto, ChampionStaticInfo, Boolean>() {
                            @Override
                            public Boolean call(
                                    SummonerSpellListDto summonerSpellListDto,
                                    ChampionStaticInfo championStaticInfo) {
                                handleSummonerSpellResponse(summonerSpellListDto);
                                handleChampionsResponse(championStaticInfo);
                                loadDynamicDataFromFriendsList();
                                refreshLastStaticSync();
                                return true;
                            }
                        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadStaticData completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error loadStaticData", e);
                        sendErrorSyncBroadcast(SYNC_DATA_ERROR);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                    }
                });
    }
    private void loadDynamicData(final Long... summonerIds) {
        String summonerIdsString = "";
        int count = 0;
        for (final Long summonerId:summonerIds){
            if (count > 0)
                summonerIdsString += ",";
            summonerIdsString += summonerId.toString();
            count++;
        }
        sendStartSyncBroadcast();
        Observable
                .zip(lolService.getSummoners(summonerIdsString),
                        lolService.getLeagues(summonerIdsString),
                        new Func2<Map<String, SummonerDto>, Map<String, List<LeagueDto>>, Boolean>() {
                            @Override
                            public Boolean call(Map<String, SummonerDto> summonerInfo,
                                                Map<String, List<LeagueDto>> leagueDtoMap
                            ) {
                                handleSummonerInformation(summonerInfo, leagueDtoMap, summonerIds);
                                loadRecentMatches(summonerIds);
                                Log.d(TAG, "############################Data sync has ended!########################################");
                                return true;
                            }
                        })
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadDynamicData completed!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error loadDynamicData", e);
                        sendErrorSyncBroadcast(SYNC_DATA_ERROR);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                    }
                });
    }

    private void loadRecentMatches(final Long... summonerIds){
        currentCountOfFriendsFetched = 0;
        expectedFriendsFetchedToSendNotification = summonerIds.length;
        socialLoLDB.clearRecentMatches();
        for (Long summonerId: summonerIds){
            Observable<RecentGamesDto> recentMatches = lolService.getRecentMatches(summonerId);
            recentMatches.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<RecentGamesDto>() {
                        @Override
                        public void onCompleted() {
                            Log.d(TAG,"loadRecentMatches Completed");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, e.getLocalizedMessage(),e);
                        }

                        @Override
                        public void onNext(RecentGamesDto recentGamesDto) {
                            handleRecentGamesResponse(recentGamesDto);
                            currentCountOfFriendsFetched++;
                            if (currentCountOfFriendsFetched == expectedFriendsFetchedToSendNotification){
                                sendUpdatedDataBroadcast();
                                sendEndSyncBroadcast();
                            }
                        }
                    });
        }
    }
    private void handleRecentGamesResponse(RecentGamesDto gamesDto) {
        //Save in DB
        List<Game> games = gamesDto.getGames();
        Long summonerId = gamesDto.getSummonerId();
        Log.d(TAG, "Recent matches fetched for user: " + summonerId + ", count: " + games.size());
        for (Game game : games) {
            if (!socialLoLDB.recentMatchExistsInDatabase(game.getGameId())){
                RecentMatch recentMatch = new RecentMatch();
                recentMatch.setGameId(game.getGameId());
                recentMatch.setAssists(game.getStats().getAssists());
                recentMatch.setCreated(game.getCreateDate());
                recentMatch.setDeaths(game.getStats().getNumDeaths());
                recentMatch.setGold(game.getStats().getGoldEarned());
                recentMatch.setMinions(game.getStats().getMinionsKilled());
                recentMatch.setItem0(game.getStats().getItem0());
                recentMatch.setItem1(game.getStats().getItem1());
                recentMatch.setItem2(game.getStats().getItem2());
                recentMatch.setItem3(game.getStats().getItem3());
                recentMatch.setItem4(game.getStats().getItem4());
                recentMatch.setItem5(game.getStats().getItem5());
                recentMatch.setItem6(game.getStats().getItem6());
                recentMatch.setKills(game.getStats().getChampionsKilled());
                recentMatch.setWin(game.getStats().getWin());
                recentMatch.setTimePlayed(game.getStats().getTimePlayed());
                recentMatch.setSummonerId(summonerId);
                recentMatch.setChampionId(game.getChampionId());
                recentMatch.setSpell1(game.getSpell1());
                recentMatch.setSpell2(game.getSpell2());
                recentMatch.setGameType(game.getSubType());
                socialLoLDB.saveRecentMatch(recentMatch);
            }

        }

        Log.d(TAG, "Recent matches count? " + socialLoLDB.getRecentMatches().size());
    }

    private void handleSummonerSpellResponse(SummonerSpellListDto summonerSpellListDto) {
        Map<String, SummonerSpellDto> data = summonerSpellListDto.getData();
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String summonerSpellName = (String) pair.getKey();
            SummonerSpellDto summonerSpellInformation = (SummonerSpellDto) pair.getValue();
            Integer summonerSpellId = summonerSpellInformation.getId();
            SummonerSpell summonerSpell = new SummonerSpell();
            summonerSpell.setSummonerSpellId(summonerSpellId);
            summonerSpell.setSummonerSpellName(summonerSpellName);
            socialLoLDB.saveSummonerSpell(summonerSpell);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    private void handleSummonerInformation(Map<String, SummonerDto> summonerMap,
                                           Map<String, List<LeagueDto>> leagueDtoMap,
                                           Long[] summonerIds) {

        Log.d(TAG, "leagueDtoMap = " + leagueDtoMap);
        for (Long summonerId: summonerIds){
            //Save in DB
            SummonerDto summonerDto = summonerMap.get(summonerId.toString());
            Summoner summoner = new Summoner();
            summoner.setSummonerId(summonerDto.getId());
            summoner.setSummonerName(summonerDto.getName());
            summoner.setProfileIconId(summonerDto.getProfileIconId());
            summoner.setRevisionDate(summonerDto.getRevisionDate());
            summoner.setSummonerLevel(summonerDto.getSummonerLevel());
            summoner.setRegion(BuildConfig.LOL_DEFAULT_REGION);

            //league information
            List<LeagueDto> leagueDtoList = leagueDtoMap.get(summonerId.toString());
            //we want the first
            LeagueDto leagueDto = leagueDtoList.get(0);
            summoner.setLeagueName(leagueDto.getName());
            summoner.setTier(leagueDto.getTier());
            summoner.setQueue(leagueDto.getQueue());

            List<LeagueEntryDto> entries = leagueDto.getEntries();
            if (entries != null && entries.size() > 0){
                summoner.setDivision(entries.get(0).getDivision());
                summoner.setLeaguePoints(entries.get(0).getLeaguePoints());
            }
            Log.d(TAG, "Summoner = " + summoner);
            socialLoLDB.saveSummoner(summoner);
        }
    }

    private void handleChampionsResponse(ChampionStaticInfo championListDto) {
        championListDto.getData();
        Map<String, ChampionDetail> data = championListDto.getData();
        Iterator it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //ChampionDto championDto = (ChampionDto)pair.getValue();
            ChampionDetail championDetail = (ChampionDetail)pair.getValue();
            Champion champion = new Champion();
            champion.setChampionId(Integer.parseInt(championDetail.getKey()));
            champion.setChampionKey(championDetail.getId());
            champion.setChampionName(championDetail.getName());
            champion.setLore(championDetail.getBlurb());
            champion.setChampionTitle(championDetail.getTitle());
            List<String> tags = championDetail.getTags();
            StringBuilder role = new StringBuilder();
            if (tags != null){
                int count = 0;
                for (String tag: tags){
                    if (count > 0) {
                        role.append(", ");
                    }
                    role.append(tag);
                    count++;
                }
            }
            champion.setRole(role.toString());
            socialLoLDB.saveChampion(champion);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void sendUpdatedDataBroadcast(){
        Intent i = new Intent("updated_data");
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    public void sendStartSyncBroadcast(){
        Intent i = new Intent("start_sync");
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    public void sendEndSyncBroadcast(){
        Intent i = new Intent("end_sync");
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    public void sendErrorSyncBroadcast(String extra){
        Intent i = new Intent("error_sync");
        i.putExtra(TYPE_TAG, extra);
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager =
                (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(
                context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
        SocialLoLSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), false);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }


    /**
     * Helper method to have the sync adapter sync immediately
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        ContentResolver.requestSync(getSyncAccount(context),
                context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            ContentResolver.requestSync(request);
        } else {
            ContentResolver.addPeriodicSync(account,
                    authority, new Bundle(), syncInterval);
        }
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
}
