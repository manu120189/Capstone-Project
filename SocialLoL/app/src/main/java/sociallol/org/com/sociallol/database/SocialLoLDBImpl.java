package sociallol.org.com.sociallol.database;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.database.models.SummonerFriend;
import sociallol.org.com.sociallol.database.models.SummonerSpell;

public class SocialLoLDBImpl implements SocialLoLDB {
    public static final String TAG = "SocialLoLDBImpl";
    private Context context;
    private DatabaseHelper databaseHelper;
//    public static SocialLoLDBImpl socialLoLDB;
    public SocialLoLDBImpl(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    private SocialLoLDBImpl(){}

//    public static SocialLoLDBImpl getInstance(Context context){
//        if (socialLoLDB == null)
//            socialLoLDB = new SocialLoLDBImpl(context);
//        return socialLoLDB;
//    }

    public void saveRecentMatch(RecentMatch recentMatch){
        // get our dao
        getRecentMatchesDAO().createOrUpdate(recentMatch);
    }

    public void saveSummoner(Summoner summoner){
        // get our dao
        if (!summonerExistsInDatabase(summoner.getSummonerId()))
            getSummonersDAO().createOrUpdate(summoner);
    }

    public void saveSummonerFriend(SummonerFriend summonerFriend){
        // get our dao
        if (!summonerFriendExistsInDatabase(summonerFriend.getSummonerId()))
            getSummonerFriendDAO().createOrUpdate(summonerFriend);
    }

    public void saveChampion(Champion champion){
        // get our dao
        if (!championExistsInDatabase(champion.getChampionId()))
            getChampionDAO().createOrUpdate(champion);
    }
    public void saveSummonerSpell(SummonerSpell summonerSpell){
        // get our dao
        if (!summonerSpellExistsInDatabase(summonerSpell.getSummonerSpellId()))
            getSummonerSpellDAO().createOrUpdate(summonerSpell);
    }

    private RuntimeExceptionDao<RecentMatch, Integer> getRecentMatchesDAO() {
        return databaseHelper.getRecentMatchDao();
    }

    private RuntimeExceptionDao<Summoner, Integer> getSummonersDAO() {
        return databaseHelper.getSummonerDao();
    }

    private RuntimeExceptionDao<SummonerFriend, Integer> getSummonerFriendDAO() {
        return databaseHelper.getSummonerFriendDao();
    }

    private RuntimeExceptionDao<Champion, Integer> getChampionDAO() {
        return databaseHelper.getChampionDao();
    }

    private RuntimeExceptionDao<SummonerSpell, Integer> getSummonerSpellDAO() {
        return databaseHelper.getSummonerSpellDao();
    }

    public List<RecentMatch> getRecentMatches(){
        try {
            return getRecentMatchesDAO().queryBuilder().orderBy("created", false).query();
        } catch (SQLException e) {
            Log.e("", "Error getting the list of recent matches");
            return null;
        }
    }
    public List<Summoner> getFriends(){
        try {
            return getSummonersDAO().queryBuilder().orderBy("summonerName", true).query();
        } catch (SQLException e) {
            Log.e("", "Error getting the list of friends");
            return null;
        }
    }
    public List<SummonerFriend> getSummonerFriends(){
        try {
            return getSummonerFriendDAO().queryBuilder().query();
        } catch (SQLException e) {
            Log.e("", "Error getting the list of friends friends");
            return null;
        }
    }
    public List<Champion> getChampions(){
        try {
            return getChampionDAO().queryBuilder().orderBy("championName", true).query();
        } catch (SQLException e) {
            Log.e("", "Error getting the list of champions friends");
            return null;
        }
    }
    public boolean summonerExistsInDatabase(Long summonerId){
        return getSummoner(summonerId) != null;
    }

    public boolean summonerFriendExistsInDatabase(Long summonerId){
        return getSummonerFriend(summonerId) != null;
    }

    public boolean recentMatchExistsInDatabase(Long matchId){
        return getRecentMatch(matchId) != null;
    }

    public boolean championExistsInDatabase(Integer championId){
        return getChampion(championId) != null;
    }

    public boolean summonerSpellExistsInDatabase(Integer summonerSpellId){
        return getSummonerSpell(summonerSpellId) != null;
    }

    public Summoner getSummoner(Long summonerId){
        try {
            return getSummonersDAO().queryBuilder().where().eq("summonerId", summonerId).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting summoner", e);
            return null;
        }
    }
    public void deleteSummonerFriend(Long summonerId){
        try {
            getSummonerFriendDAO().delete(getSummonerFriend(summonerId));
        } catch (Exception e) {
            Log.e(TAG, "Error getting summoner", e);
        }
    }
    public void deleteSummoner(Long summonerId){
        try {
            getSummonersDAO().delete(getSummoner(summonerId));
        } catch (Exception e) {
            Log.e(TAG, "Error getting summoner", e);
        }
    }
    public SummonerFriend getSummonerFriend(Long summonerId){
        try {
            return getSummonerFriendDAO().queryBuilder().where().eq("summonerId", summonerId).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting summoner", e);
            return null;
        }
    }

    public Champion getChampion(Integer championId){
        try {
            return getChampionDAO().queryBuilder().where().eq("championId", championId).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting champion", e);
            return null;
        }
    }

    public int getFirstChampionId(){
        try {
            Champion champion = getChampionDAO().queryBuilder().orderBy("championName", true).queryForFirst();
            if (champion == null) return  0;
            return champion.getChampionId();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting champion", e);
            return 0;
        }
    }

    public Long getFirstSummonerId(){
        try {
            Summoner summoner = getSummonersDAO().queryBuilder().orderBy("summonerName", true).queryForFirst();
            if (summoner == null) return null;
            return summoner.getSummonerId();
        } catch (SQLException e) {
            Log.e("", "Error getting the list of friends friends");
            return null;
        }
    }

    public RecentMatch getRecentMatch(Long recentMatchId){
        try {
            return getRecentMatchesDAO().queryBuilder().where().eq("gameId", recentMatchId).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting recent match", e);
            return null;
        }
    }

    public SummonerSpell getSummonerSpell(Integer summonerSpellId){
        try {
            return getSummonerSpellDAO().queryBuilder().where().eq("summonerSpellId", summonerSpellId).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, "Error getting summonerSpell", e);
            return null;
        }
    }
    public String getSummonerName(Long summonerId){
        try {
            Summoner summoner = getSummonersDAO().queryBuilder().where().eq("summonerId", summonerId).queryForFirst();
            if (summoner != null)
                return summoner.getSummonerName();
            else return null;
        } catch (SQLException e) {
            Log.e(TAG, "Error getting summoner", e);
            return null;
        }
    }

    public void clearRecentMatches(){
        databaseHelper.clearRecentMatches();
    }

//    public boolean deleteMovieFromDatabase(String movieId){
//        try {
//            // get our dao
//            Movie movieToDelete = getSummoners(movieId);
//            getRecentMatchesDAO().delete(movieToDelete);
//            return true;
//        } catch (Exception e) {
//            Log.e("", "Error deleting movie");
//            return false;
//        }
//
//    }
//
//    public boolean movieExistInDatabase(String movieId){
//        return getSummoners(movieId) != null;
//    }
//
//    public Movie getSummoners(String movieId){
//        try {
//            return getRecentMatchesDAO().queryBuilder().where().eq(Movie.Keys.MOVIE_ID, movieId).queryForFirst();
//        } catch (SQLException e) {
//            Log.e("", "Error getting movie");
//            return null;
//        }
//    }
//
//    public List<Movie> getFavoriteMovies(){
//        try {
//            return getRecentMatchesDAO().queryBuilder().where().isNotNull(Movie.Keys.MOVIE_ID).query();
//        } catch (SQLException e) {
//            Log.e("", "Error getting movie");
//            return null;
//        }
//    }
//
//    public void clearMovieTable(){
//        context.deleteDatabase(DatabaseHelper.DATABASE_NAME);
//    }
}
