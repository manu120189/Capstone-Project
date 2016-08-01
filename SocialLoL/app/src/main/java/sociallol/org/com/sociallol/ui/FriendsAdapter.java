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
import sociallol.org.com.sociallol.database.models.Summoner;
import sociallol.org.com.sociallol.utils.GameUtils;
import sociallol.org.com.sociallol.utils.PicassoUtils;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MatchViewHolder>{
    public static final String TAG = "FriendsAdapter";
    private List<Summoner> friends;
    private Context context;
    private boolean isTwoPane;
    private String referenceTag = "";

    public class MatchViewHolder extends RecyclerView.ViewHolder{
        public TextView summonerName, region, level, leagueTitle, leaguePoints;
        public ImageView profileImage, leagueImage;
        public boolean isTwoPane;

        public MatchViewHolder(View view, boolean isTwoPane) {
            super(view);
            summonerName = (TextView) view.findViewById(R.id.champion_name);
            region = (TextView) view.findViewById(R.id.champion_role);
            leagueTitle = (TextView) view.findViewById(R.id.league_title);
            leaguePoints = (TextView) view.findViewById(R.id.league_points);
            level = (TextView) view.findViewById(R.id.level);
            profileImage = (ImageView) view.findViewById(R.id.champion_image);
            leagueImage = (ImageView) view.findViewById(R.id.league_image);
            this.isTwoPane = isTwoPane;
        }

        public void bind(final Summoner summoner) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    MainActivity contextMain = (MainActivity) context;
                    contextMain.startSummonerDetailActivity(summoner, isTwoPane, referenceTag);
                }
            });
        }
    }

    public FriendsAdapter(List<Summoner> friends, Context context, boolean isTwoPane, String tag) {
        this.friends = friends;
        this.context = context;
        this.isTwoPane = isTwoPane;
        this.referenceTag = tag;
    }

    public void swap(List<Summoner> friends){
        Log.d(TAG,"Swapping data for friends.");
        this.friends.clear();
        this.friends.addAll(friends);
        notifyDataSetChanged();
    }



    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_list_row, parent, false);
        return new MatchViewHolder(itemView, isTwoPane);
    }



    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.bind(friends.get(position));
        if (friends == null) return;
        Summoner summoner = friends.get(position);
        if (summoner == null) return;

        //items
        PicassoUtils.loadProfileIcon(context, summoner.getProfileIconId(), holder.profileImage);

        holder.summonerName.setText(summoner.getSummonerName());
        holder.region.setText(GameUtils.regions.get(summoner.getRegion()));
        holder.level.setText(context.getResources().getString(R.string.level) + " " + summoner.getSummonerLevel());

        if (summoner.getTier() != null)
            PicassoUtils.loadLeagueImage(context, GameUtils.leagues.get(summoner.getTier()), holder.leagueImage);
        else
            holder.leagueImage.setVisibility(View.GONE);

        if (summoner.getTier() != null && summoner.getDivision() != null) {
            holder.leagueTitle.setText(summoner.getTier() + " " + summoner.getDivision());
        }
        else
            holder.leagueTitle.setVisibility(View.GONE);

        if (summoner.getLeaguePoints() != null)
            holder.leaguePoints.setText(summoner.getLeaguePoints() + " Pts");
        else
            holder.leaguePoints.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        if (friends == null) return 0;
        return friends.size();
    }


}
