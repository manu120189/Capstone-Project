package sociallol.org.com.sociallol.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sociallol.org.com.sociallol.api.models.ChampionStaticInfo;
import sociallol.org.com.sociallol.api.models.RankedStatsDto;

public interface LoLDDragonEndpoints {
    //http://ddragon.leagueoflegends.com/cdn/
    @GET("en_US/champion.json")
    Observable<ChampionStaticInfo> getChampionStatic();
}
