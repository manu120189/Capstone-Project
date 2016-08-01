package sociallol.org.com.sociallol.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.models.RecentMatch;
import sociallol.org.com.sociallol.utils.GameUtils;
import sociallol.org.com.sociallol.utils.NumberUtils;
import sociallol.org.com.sociallol.utils.PicassoUtils;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchViewHolder>{
    public static final String TAG = "MatchesAdapter";
    private List<RecentMatch> recentMatches;
    private Context context;
    public class MatchViewHolder extends RecyclerView.ViewHolder {
        public TextView gameStatusWin, gameStatusLoss, title, scoreView, goldScoreView,
                minionScoreView, timePlayed, timeAgo;
        public ImageView item0, item1, item2, item3, item4, item5, item6, spell1, spell2, championImage;
        public MatchViewHolder(View view) {
            super(view);
            gameStatusWin = (TextView) view.findViewById(R.id.game_status_win);
            gameStatusLoss = (TextView) view.findViewById(R.id.game_status_loss);
            item0 = (ImageView) view.findViewById(R.id.item0);
            item1 = (ImageView) view.findViewById(R.id.item1);
            item2 = (ImageView) view.findViewById(R.id.item2);
            item3 = (ImageView) view.findViewById(R.id.item3);
            item4 = (ImageView) view.findViewById(R.id.item4);
            item5 = (ImageView) view.findViewById(R.id.item5);
            item6 = (ImageView) view.findViewById(R.id.item6);

            spell1 = (ImageView) view.findViewById(R.id.passive_image);
            spell2 = (ImageView) view.findViewById(R.id.spell2);

            scoreView = (TextView) view.findViewById(R.id.score_view);
            goldScoreView = (TextView) view.findViewById(R.id.gold_score_view);
            minionScoreView = (TextView) view.findViewById(R.id.minion_score_view);
            title = (TextView) view.findViewById(R.id.title);
            timePlayed = (TextView) view.findViewById(R.id.time_played);
            timeAgo = (TextView) view.findViewById(R.id.time_ago);

            championImage = (ImageView) view.findViewById(R.id.champion_image);

        }
    }

    public MatchesAdapter(List<RecentMatch> recentMatches, Context context) {
        this.recentMatches = recentMatches;
        this.context = context;
    }

    public void swap(List<RecentMatch> recentMatches){
        Log.d(TAG,"Swapping data for matches.");
        this.recentMatches.clear();
        this.recentMatches.addAll(recentMatches);
        notifyDataSetChanged();
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_list_row, parent, false);

        return new MatchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        if (recentMatches == null) return;
        RecentMatch recentMatch = recentMatches.get(position);
        if (recentMatch == null) return;

        //win or loss the recentMatch
        if (recentMatch.getWin()){
            holder.gameStatusWin.setVisibility(View.VISIBLE);
            holder.gameStatusLoss.setVisibility(View.GONE);
        }else{
            holder.gameStatusWin.setVisibility(View.GONE);
            holder.gameStatusLoss.setVisibility(View.VISIBLE);
        }

        //items
        PicassoUtils.loadItem(context, recentMatch.getItem0(), holder.item0);
        PicassoUtils.loadItem(context, recentMatch.getItem1(), holder.item1);
        PicassoUtils.loadItem(context, recentMatch.getItem2(), holder.item2);
        PicassoUtils.loadItem(context, recentMatch.getItem3(), holder.item3);
        PicassoUtils.loadItem(context, recentMatch.getItem4(), holder.item4);
        PicassoUtils.loadItem(context, recentMatch.getItem5(), holder.item5);
        PicassoUtils.loadItem(context, recentMatch.getItem6(), holder.item6);


        //statistics
        Integer kills = recentMatch.getKills() == null ? 0 : recentMatch.getKills();
        Integer deaths = recentMatch.getDeaths() == null ? 0 : recentMatch.getDeaths();
        Integer assists = recentMatch.getAssists() == null ? 0 : recentMatch.getAssists();
        holder.scoreView.setText(kills + "/" + deaths + "/" + assists);
        holder.goldScoreView.setText(NumberUtils.format(recentMatch.getGold()));
        holder.minionScoreView.setText(recentMatch.getMinions() + "");

        String timePlayed = NumberUtils.secondsToString(recentMatch.getTimePlayed());
        holder.timePlayed.setText(context.getResources().getString(R.string.duration)  + " " + timePlayed);

        holder.timeAgo.setText(NumberUtils.getTimeAgo(recentMatch.getCreated().getTime(), context));

        //Title
        holder.title.setText(recentMatch.getSummonerName() + " - " + GameUtils.gameTypes.get(recentMatch.getGameType()));

        // update summoner spells with recentMatch.getSpell1() with this id query the DB to get the
        // spell key, then build the url to get the spell thumbnail,
        // There is not a better way of doing this, thanks RIOT
        PicassoUtils.loadSpell(context, recentMatch.getSpell1Name(), holder.spell1, true);
        PicassoUtils.loadSpell(context, recentMatch.getSpell2Name(), holder.spell2, true);

        // update champion photo with recentMatch.getChampionId() with this id query the DB to get the
        // Champion key, then build the url to get the champion thumbnail,
        // There is not a better way of doing this, thanks RIOT
        PicassoUtils.loadChampionThumbnail(context, recentMatch.getChampionKey(), holder.championImage);

    }

    @Override
    public int getItemCount() {
        if (recentMatches == null) return 0;
        return recentMatches.size();
    }
}
