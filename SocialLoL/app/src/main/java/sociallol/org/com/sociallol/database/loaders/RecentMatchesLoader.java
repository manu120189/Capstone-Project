package sociallol.org.com.sociallol.database.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.database.models.SummonerSpell;

public class RecentMatchesLoader extends AsyncTaskLoader<List<RecentMatch>> {
    private static final String TAG = "RecentMatchesLoader";
    private SocialLoLDB socialLoLDB;

    public RecentMatchesLoader(Context context) {
        super(context);
        socialLoLDB = new SocialLoLDBImpl(getContext());
    }

    @Override
    public List<RecentMatch> loadInBackground() {
        // this is just a simple query, could be anything that gets a cursor
        List<RecentMatch> recentMatches = socialLoLDB.getRecentMatches();
        completeMatchesInformation(recentMatches);
        return recentMatches;
    }

    private void completeMatchesInformation(List<RecentMatch> recentMatches){
        for (RecentMatch recentMatch: recentMatches){
            String summonerName = socialLoLDB.getSummonerName(recentMatch.getSummonerId());
            if (summonerName != null)
                recentMatch.setSummonerName(summonerName);
            else
                recentMatch.setSummonerName("Player " + recentMatch.getSummonerId());
            SummonerSpell summonerSpell1 = socialLoLDB.getSummonerSpell(recentMatch.getSpell1());
            if (summonerSpell1 != null)
                recentMatch.setSpell1Name(summonerSpell1.getSummonerSpellName());
            else
                recentMatch.setSpell1Name("SummonerFlash");

            SummonerSpell summonerSpell2 = socialLoLDB.getSummonerSpell(recentMatch.getSpell2());
            if (summonerSpell2 != null)
                recentMatch.setSpell2Name(summonerSpell2.getSummonerSpellName());
            else
                recentMatch.setSpell2Name("SummonerTeleport");

            Champion champion = socialLoLDB.getChampion(recentMatch.getChampionId());
            if (champion != null)
                recentMatch.setChampionKey(champion.getChampionKey());
        }
    }
}
