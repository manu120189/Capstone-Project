package sociallol.org.com.sociallol.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sociallol.org.com.sociallol.api.models.ChampionDto;
import sociallol.org.com.sociallol.api.models.ChampionListDto;
import sociallol.org.com.sociallol.api.models.SummonerSpellListDto;

public interface LoLStaticEndpoints {
    //https://global.api.pvp.net/api/lol/static-data/lan/v1.2/summoner-spell?api_key=
    @GET("v1.2/summoner-spell")
    Observable<SummonerSpellListDto> getSummonerSpellInfo(@Query("api_key") String apiKey);

    //https://global.api.pvp.net/api/lol/static-data/lan/v1.2/champion?api_key=
    @GET("v1.2/champion")
    Observable<ChampionListDto> getChampionsInfo(@Query("api_key") String apiKey);

    //v1.2/champion/1?champData=allytips,enemytips,lore,passive,spells,tags&api_key=
    @GET("v1.2/champion/{championId}")
    Observable<ChampionDto> getChampion(@Path("championId") Integer championId,
                                        @Query("champData") String champData,
                                        @Query("api_key") String apiKey
                                        );
}
