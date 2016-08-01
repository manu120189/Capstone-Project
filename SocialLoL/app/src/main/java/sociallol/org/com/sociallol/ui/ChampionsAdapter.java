package sociallol.org.com.sociallol.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.utils.PicassoUtils;

public class ChampionsAdapter extends RecyclerView.Adapter<ChampionsAdapter.ChampionViewHolder>{
    public static final String TAG = "ChampionsAdapter";
    private List<Champion> champions;
    private Context context;
    private boolean isTwoPane;

    public class ChampionViewHolder extends RecyclerView.ViewHolder{
        public TextView championName, championRole;
        public ImageView championImage;
        public boolean isTwoPane;
        public ChampionViewHolder(View view, boolean isTwoPane) {
            super(view);
            championName = (TextView) view.findViewById(R.id.champion_name);
            championRole = (TextView) view.findViewById(R.id.champion_role);
            championImage = (ImageView) view.findViewById(R.id.champion_image);
            this.isTwoPane = isTwoPane;
        }

        public void bind(final Champion champion) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    //check if tablet
                    MainActivity mainActivity = (MainActivity)context;
                    mainActivity.startChampionDetailActivity(champion, isTwoPane);
                }
            });
        }
    }

    public ChampionsAdapter(List<Champion> champions, Context context, boolean isTwoPane) {
        this.champions = champions;
        this.context = context;
        this.isTwoPane = isTwoPane;
    }

    public void swap(List<Champion> champions){
        Log.d(TAG,"Swapping data for champions.");
        this.champions.clear();
        this.champions.addAll(champions);
        notifyDataSetChanged();
    }

    @Override
    public ChampionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.champion_list_row, parent, false);
        return new ChampionViewHolder(itemView, isTwoPane);
    }



    @Override
    public void onBindViewHolder(ChampionViewHolder holder, int position) {
        holder.bind(champions.get(position));
        if (champions == null) return;
        Champion champion = champions.get(position);
        if (champion == null) return;

        //items
        PicassoUtils.loadChampionThumbnail(context, champion.getChampionKey(), holder.championImage);
        holder.championName.setText(champion.getChampionName());
        holder.championRole.setText(champion.getRole());

    }

    @Override
    public int getItemCount() {
        if (champions == null) return 0;
        return champions.size();
    }


}
