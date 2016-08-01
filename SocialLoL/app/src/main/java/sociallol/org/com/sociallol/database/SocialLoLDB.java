package sociallol.org.com.sociallol.database;

import java.util.List;

import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.database.models.SummonerFriend;
import sociallol.org.com.sociallol.database.models.SummonerSpell;


public interface SocialLoLDB {
    void saveRecentMatch(RecentMatch match);
    List<RecentMatch> getRecentMatches();
    List<Summoner> getFriends();
    void saveSummoner(Summoner summoner);
    void saveChampion(Champion champion);
    void saveSummonerSpell(SummonerSpell summonerSpell);
    void clearRecentMatches();
    String getSummonerName(Long summonerId);
    SummonerSpell getSummonerSpell(Integer summonerSpellId);
    Champion getChampion(Integer championId);
    boolean recentMatchExistsInDatabase(Long matchId);
    boolean summonerFriendExistsInDatabase(Long summonerId);
    void saveSummonerFriend(SummonerFriend summonerFriend);
    void deleteSummonerFriend(Long summonerId);
    void deleteSummoner(Long summonerId);
    List<SummonerFriend> getSummonerFriends();
    List<Champion> getChampions();
    int getFirstChampionId();
    Long getFirstSummonerId();
}
