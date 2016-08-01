package sociallol.org.com.sociallol.utils;

import java.util.HashMap;
import java.util.Map;

import sociallol.org.com.sociallol.R;

public class GameUtils {
    public static final Map<String, String> gameTypes = new HashMap<>();
    public static final Map<String, String> regions = new HashMap<>();
    public static final Map<String, Integer> leagues = new HashMap<>();
    public static final Long[] testSummonerIds = new Long[]{5562585L, 5350970L};

    static {
        gameTypes.put("NONE", "None");
        gameTypes.put("NORMAL", "Normal");
        gameTypes.put("BOT", "Bots");
        gameTypes.put("RANKED_SOLO_5x5", "Ranked Solo 5vs5");
        gameTypes.put("RANKED_PREMADE_3x3", "Ranked Premade 3vs3");
        gameTypes.put("RANKED_PREMADE_5x5", "Ranked Premade 5vs5");
        gameTypes.put("ODIN_UNRANKED", "Odin");
        gameTypes.put("RANKED_TEAM_3x3", "Ranked Team 3vs3");
        gameTypes.put("RANKED_TEAM_5x5", "Ranked Team 5vs5");
        gameTypes.put("NORMAL_3x3", "Normal 3vs3");
        gameTypes.put("BOT_3x3", "Bots 3vs3");
        gameTypes.put("CAP_5x5", "Cap 5vs5");
        gameTypes.put("ARAM_UNRANKED_5x5", "Aram 5vs5");
        gameTypes.put("ONEFORALL_5x5", "One For All");
        gameTypes.put("FIRSTBLOOD_1x1", "First Blood");
        gameTypes.put("FIRSTBLOOD_2x2", "First Bloods 2vs2");
        gameTypes.put("SR_6x6", "SR 6vs6");
        gameTypes.put("URF", "URF");
        gameTypes.put("URF_BOT", "URF Bots");
        gameTypes.put("NIGHTMARE_BOT", "Nightmare Bots");
        gameTypes.put("SR_6x6", "SR 6vs6");
        gameTypes.put("ASCENSION", "Ascension");
        gameTypes.put("HEXAKILL", "Hexakill");
        gameTypes.put("KING_PORO", "King Poro");
        gameTypes.put("COUNTER_PICK", "Counter Pick");
        gameTypes.put("BILGEWATER", "Bilgewater");

        //regions
        regions.put("NA","North America");
        regions.put("EUW","Europe West");
        regions.put("EUNE","Europe Nordic & East");
        regions.put("BR","Brazil");
        regions.put("KR","Korea");
        regions.put("TR","Turkey");
        regions.put("RU","Russia");
        regions.put("LAN","Latin America North");
        regions.put("LAS","Latin America South");
        regions.put("OCE","Oceania");

        //The league's tier. (Legal values: CHALLENGER, MASTER, DIAMOND, PLATINUM, GOLD, SILVER, BRONZE)
        //leagues
        leagues.put("BRONZE", R.drawable.bronze);
        leagues.put("SILVER", R.drawable.silver);
        leagues.put("GOLD", R.drawable.gold);
        leagues.put("PLATINUM", R.drawable.platinum);
        leagues.put("DIAMOND", R.drawable.diamond);
        leagues.put("MASTER", R.drawable.master);
        leagues.put("CHALLENGER", R.drawable.challenger);
    }
}
