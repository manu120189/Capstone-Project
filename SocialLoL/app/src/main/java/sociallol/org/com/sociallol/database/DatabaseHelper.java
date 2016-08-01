package sociallol.org.com.sociallol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.database.models.SummonerFriend;
import sociallol.org.com.sociallol.database.models.SummonerSpell;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    public static final String DATABASE_NAME = BuildConfig.DATABASE_NAME;
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;
    public static final String TAG = "DatabaseHelper";

    private Dao<RecentMatch, Integer> recentMatchesDao = null;
    private RuntimeExceptionDao<RecentMatch, Integer> recentMatchesRuntimeDao = null;

    private Dao<Summoner, Integer> summonerDao = null;
    private RuntimeExceptionDao<Summoner, Integer> summonerRuntimeDao = null;

    private Dao<SummonerSpell, Integer> summonerSpellDao = null;
    private RuntimeExceptionDao<SummonerSpell, Integer> summonerSpellRuntimeDao = null;


    private Dao<Champion, Integer> championDao = null;
    private RuntimeExceptionDao<Champion, Integer> championRuntimeDao = null;

    private Dao<SummonerFriend, Integer> summonerFriendDao = null;
    private RuntimeExceptionDao<SummonerFriend, Integer> summonerFriendRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            connectionSource = connectionSource;
            TableUtils.createTable(connectionSource, RecentMatch.class);
            TableUtils.createTable(connectionSource, Summoner.class);
            TableUtils.createTable(connectionSource, SummonerSpell.class);
            TableUtils.createTable(connectionSource, Champion.class);
            TableUtils.createTable(connectionSource, SummonerFriend.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, RecentMatch.class, true);
            TableUtils.dropTable(connectionSource, Summoner.class, true);
            TableUtils.dropTable(connectionSource, SummonerSpell.class, true);
            TableUtils.dropTable(connectionSource, Champion.class, true);
            TableUtils.dropTable(connectionSource, SummonerFriend.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<RecentMatch, Integer> getRecentMatchDao() {
        if (recentMatchesRuntimeDao == null) {
            recentMatchesRuntimeDao = getRuntimeExceptionDao(RecentMatch.class);
        }
        return recentMatchesRuntimeDao;
    }

    public RuntimeExceptionDao<Summoner, Integer> getSummonerDao() {
        if (summonerRuntimeDao == null) {
            summonerRuntimeDao = getRuntimeExceptionDao(Summoner.class);
        }
        return summonerRuntimeDao;
    }

    public RuntimeExceptionDao<SummonerSpell, Integer> getSummonerSpellDao() {
        if (summonerSpellRuntimeDao == null) {
            summonerSpellRuntimeDao = getRuntimeExceptionDao(SummonerSpell.class);
        }
        return summonerSpellRuntimeDao;
    }

    public RuntimeExceptionDao<Champion, Integer> getChampionDao() {
        if (championRuntimeDao == null) {
            championRuntimeDao = getRuntimeExceptionDao(Champion.class);
        }
        return championRuntimeDao;
    }

    public RuntimeExceptionDao<SummonerFriend, Integer> getSummonerFriendDao() {
        if (summonerFriendRuntimeDao == null) {
            summonerFriendRuntimeDao = getRuntimeExceptionDao(SummonerFriend.class);
        }
        return summonerFriendRuntimeDao;
    }


    public void clearRecentMatches(){
        try {
            TableUtils.clearTable(getConnectionSource(), RecentMatch.class);
        } catch (SQLException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }
    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        recentMatchesDao = null;
        recentMatchesRuntimeDao = null;
        summonerDao = null;
        summonerRuntimeDao = null;
        summonerSpellDao = null;
        summonerSpellRuntimeDao = null;
        championDao = null;
        championRuntimeDao = null;
        summonerFriendDao = null;
        summonerFriendRuntimeDao = null;
    }
}