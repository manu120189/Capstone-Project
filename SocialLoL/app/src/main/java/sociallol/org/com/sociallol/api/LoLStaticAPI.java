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

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.api.models.ChampionDto;
import sociallol.org.com.sociallol.api.models.ChampionListDto;
import sociallol.org.com.sociallol.api.models.ChampionStaticInfo;
import sociallol.org.com.sociallol.api.models.SummonerSpellListDto;

public class LoLStaticAPI {

    public static final String TAG = "LoLStaticAPI";
    public static final String CHAMP_DATA = "allytips,enemytips,lore,passive,spells,tags";
    public static final String DDRAGON_URL = "http://ddragon.leagueoflegends.com/cdn/6.15.1/data/";
    private LoLStaticEndpoints lolStaticService;
    private LoLDDragonEndpoints lolStaticDDragonService;

    public static LoLStaticAPI create(){
        return new LoLStaticAPI();
    }

    public LoLStaticAPI() {
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

            Retrofit retrofitGlobal = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(BuildConfig.LOL_API_STATIC_LAN_URL)
                    .build();

            Retrofit retrofitDDragon = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(DDRAGON_URL)
                    .build();

            lolStaticService = retrofitGlobal.create(LoLStaticEndpoints.class);
            lolStaticDDragonService = retrofitDDragon.create(LoLDDragonEndpoints.class);
        }catch (Exception e){
            Log.e(TAG, e.getLocalizedMessage());
        }

    }

    public Observable<SummonerSpellListDto> getSummonerSpellInfo(){
        return lolStaticService.getSummonerSpellInfo(BuildConfig.LOL_API_KEY);
    }

    public Observable<ChampionListDto> getChampionsInfo(){
        return lolStaticService.getChampionsInfo(BuildConfig.LOL_API_KEY);
    }

    public Observable<ChampionDto> getChampion(Integer id){
        return lolStaticService.getChampion(id, CHAMP_DATA, BuildConfig.LOL_API_KEY);
    }

    public Observable<ChampionStaticInfo> getChampionStatic(){
        return lolStaticDDragonService.getChampionStatic();
    }
}
