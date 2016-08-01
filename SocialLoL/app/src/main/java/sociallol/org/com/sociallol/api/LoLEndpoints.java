package sociallol.org.com.sociallol.api;


import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sociallol.org.com.sociallol.api.models.ChampionFreeList;
import sociallol.org.com.sociallol.api.models.LeagueDto;
import sociallol.org.com.sociallol.api.models.MatchDetail;
import sociallol.org.com.sociallol.api.models.MatchList;
import sociallol.org.com.sociallol.api.models.RankedStatsDto;
import sociallol.org.com.sociallol.api.models.RecentGamesDto;
import sociallol.org.com.sociallol.api.models.Summoner;
import sociallol.org.com.sociallol.api.models.SummonerDto;
import sociallol.org.com.sociallol.api.models.SummonerSpellListDto;

public interface LoLEndpoints {

    //https://lan.api.pvp.net/api/lol/lan/v1.4/summoner/by-name/lokopunk?api_key=
    @GET("v1.4/summoner/by-name/{summonerName}")
    Observable<Map<String, Summoner>> getSummonerData(@Path("summonerName") String summonerName,
                                                      @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v2.2/matchlist/by-summoner/5562585?beginIndex=0&endIndex=10&api_key=
    @GET("v2.2/matchlist/by-summoner/{summonerId}")
    Observable<MatchList> getMatchList(@Path("summonerId") String summonerId,
                                       @Query("api_key") String apiKey,
                                       @Query("beginIndex") int beginIndex,
                                       @Query("endIndex") int endIndex);

    //https://lan.api.pvp.net/api/lol/lan/v2.2/match/309688291?includeTimeline=false&api_key=
    @GET("v2.2/match/{matchId}")
    Observable<MatchDetail> getMatchDetail(@Path("matchId") String matchId,
                                           @Query("api_key") String apiKey,
                                           @Query("includeTimeline") boolean includeTimeline);

    //v1.3/game/by-summoner/5562585/recent
    @GET("v1.3/game/by-summoner/{summonerId}/recent")
    Observable<RecentGamesDto> getRecentMatches(@Path("summonerId") Long summonerId,
                                           @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v1.4/summoner/5562585/name?api_key=
    @GET("v1.4/summoner/{summonerId}/name")
    Observable<Map<String, String>> getSummonerName(@Path("summonerId") Long summonerId,
                                                      @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v1.4/summoner/5562585,5350970?api_key=
    @GET("v1.4/summoner/{summonerIds}")
    Observable<Map<String, SummonerDto>> getSummoners(@Path("summonerIds") String summonerIds,
                                                    @Query("api_key") String apiKey);


    //https://lan.api.pvp.net/api/lol/lan/v2.5/league/by-summoner/5562585/entry?api_key=
    @GET("v2.5/league/by-summoner/{summonerIds}/entry")
    Observable<Map<String, List<LeagueDto>>> getLeague(@Path("summonerIds") String summonerIds,
                                                      @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v1.4/summoner/by-name/lokopunk?api_key=
    @GET("v1.4/summoner/by-name/{summonerNames}")
    Observable<Map<String, SummonerDto>> getSummonersByName(@Path("summonerNames") String summonerNames,
                                                      @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v1.3/stats/by-summoner/5562585/ranked?season=SEASON2016&api_key=
    @GET("v1.3/stats/by-summoner/{summonerId}/ranked")
    Observable<RankedStatsDto> getSummonerStats(@Path("summonerId") String summonerId,
                                                          @Query("season") String season,
                                                          @Query("api_key") String apiKey);

    //https://lan.api.pvp.net/api/lol/lan/v1.2/champion?freeToPlay=true&api_key=
    @GET("v1.2/champion")
    Observable<ChampionFreeList> getFreeChampions(
                                                @Query("freeToPlay") boolean freeToPlay,
                                                @Query("api_key") String apiKey);

}
