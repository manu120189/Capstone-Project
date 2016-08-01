package sociallol.org.com.sociallol.api;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.api.models.ChampionFreeList;
import sociallol.org.com.sociallol.api.models.LeagueDto;
import sociallol.org.com.sociallol.api.models.RankedStatsDto;
import sociallol.org.com.sociallol.api.models.RecentGamesDto;
import sociallol.org.com.sociallol.api.models.Summoner;
import sociallol.org.com.sociallol.api.models.SummonerDto;

public class LoLAPI {

    public static final String TAG = "LoLAPI";
    public static final String CURRENT_SEASON = BuildConfig.CURRENT_SEASON;
    private LoLEndpoints lolService;

    public static LoLAPI create(){
        return new LoLAPI();
    }

    public LoLAPI() {
        try{
            // Creates the json object which will manage the information received
            GsonBuilder builder = new GsonBuilder();

            // Register an adapter to manage the date types as long values
            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new Date(json.getAsJsonPrimitive().getAsLong());
                }
            });

            Gson gson = builder.create();

            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BuildConfig.LOL_API_LAN_URL)

                    .build();

            lolService = retrofit.create(LoLEndpoints.class);
        }catch (Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }

    }

    public Observable<Map<String, Summoner>> getSummonerData(String summoner){
        return lolService.getSummonerData(summoner, BuildConfig.LOL_API_KEY);
    }

    public Observable<RecentGamesDto> getRecentMatches(Long summonerId){
        return lolService.getRecentMatches(summonerId, BuildConfig.LOL_API_KEY);
    }

    @Deprecated
    public Observable<Map<String, String>> getSummonerName(Long summonerId){
        return lolService.getSummonerName(summonerId, BuildConfig.LOL_API_KEY);
    }

    public Observable<Map<String, SummonerDto>> getSummoners(String summonerIds){
        return lolService.getSummoners(summonerIds, BuildConfig.LOL_API_KEY);
    }

    public Observable<Map<String, List<LeagueDto>>> getLeagues(String summonerIds){
        return lolService.getLeague(summonerIds, BuildConfig.LOL_API_KEY);
    }

    public Observable<Map<String, SummonerDto>> getSummonersByName(String summonerNames){
        return lolService.getSummonersByName(summonerNames, BuildConfig.LOL_API_KEY);
    }

    public Observable<RankedStatsDto> getSummonerStats(String summonerId){
        return lolService.getSummonerStats(summonerId, CURRENT_SEASON, BuildConfig.LOL_API_KEY);
    }

    public Observable<ChampionFreeList> getFreeChampions(){
        return lolService.getFreeChampions(true, BuildConfig.LOL_API_KEY);
    }
}
