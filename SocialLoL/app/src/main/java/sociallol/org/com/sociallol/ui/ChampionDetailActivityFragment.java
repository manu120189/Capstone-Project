package sociallol.org.com.sociallol.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLStaticAPI;
import sociallol.org.com.sociallol.api.models.ChampionDto;
import sociallol.org.com.sociallol.api.models.Image_;
import sociallol.org.com.sociallol.api.models.Passive;
import sociallol.org.com.sociallol.api.models.Spell;
import sociallol.org.com.sociallol.utils.PicassoUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChampionDetailActivityFragment extends Fragment {
    public static LoLStaticAPI lolStaticService;
    public static final String TAG = "ChampionDetailActivityF";
    private ProgressDialog progress;
    private Target mTarget;
    private int championId = 0;
    private boolean isTwoPane;

    public void setIsTwoPane(boolean isTwoPane) {
        this.isTwoPane = isTwoPane;
    }

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public static ChampionDetailActivityFragment create(int championId, boolean isTwoPane){
        ChampionDetailActivityFragment championDetailActivityFragment
                 = new ChampionDetailActivityFragment();
        championDetailActivityFragment.setChampionId(championId);
        championDetailActivityFragment.setIsTwoPane(isTwoPane);
        return championDetailActivityFragment;
    }

    public ChampionDetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_champion_detail, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lolStaticService = new LoLStaticAPI();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof ChampionDetailActivity){
            ChampionDetailActivity championDetailActivity = (ChampionDetailActivity) getActivity();
            championId = championDetailActivity.getChampionId();
        }
        if (championId == 0)
            return;
        loadChampionProfile(championId);
    }

    public void loadProfileInformationOnUI(ChampionDto championDto){
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.champion_detail_container);
        linearLayout.setVisibility(View.VISIBLE);
        TextView emptyProfile = (TextView) getView().findViewById(R.id.champion_detail_empty);
        emptyProfile.setVisibility(View.GONE);
        ImageView championImageView = (ImageView) getView().findViewById(R.id.champion_image);
        TextView championNameView = (TextView) getView().findViewById(R.id.champion_name);
        TextView championRoleView = (TextView) getView().findViewById(R.id.champion_role);
        final RelativeLayout championSplashLayout = (RelativeLayout) getView().findViewById(R.id.champion_splash);
        TextView tipsView = (TextView) getView().findViewById(R.id.tips);
        TextView counterTipsView = (TextView) getView().findViewById(R.id.counter_tips);
        TextView historyView = (TextView) getView().findViewById(R.id.history);
        ImageView passiveImageView = (ImageView) getView().findViewById(R.id.passive_image);
        TextView passiveNameView = (TextView) getView().findViewById(R.id.passive_name);
        TextView passiveDescriptionView = (TextView) getView().findViewById(R.id.passive_description);

        ImageView spellImage1View = (ImageView) getView().findViewById(R.id.spell_image_1);
        TextView spellName1View = (TextView) getView().findViewById(R.id.spell_name_1);
        TextView spellDescription1View = (TextView) getView().findViewById(R.id.spell_description_1);

        ImageView spellImage2View = (ImageView) getView().findViewById(R.id.spell_image_2);
        TextView spellName2View = (TextView) getView().findViewById(R.id.spell_name_2);
        TextView spellDescription2View = (TextView) getView().findViewById(R.id.spell_description_2);

        ImageView spellImage3View = (ImageView) getView().findViewById(R.id.spell_image_3);
        TextView spellName3View = (TextView) getView().findViewById(R.id.spell_name_3);
        TextView spellDescription3View = (TextView) getView().findViewById(R.id.spell_description_3);

        ImageView spellImage4View = (ImageView) getView().findViewById(R.id.spell_image_4);
        TextView spellName4View = (TextView) getView().findViewById(R.id.spell_name_4);
        TextView spellDescription4View = (TextView) getView().findViewById(R.id.spell_description_4);

        mTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d(TAG, "Bitmap Loaded");
                if (championSplashLayout != null) {
                    Context context = getContext();
                    if (context != null && bitmap != null)
                        championSplashLayout.setBackground(new BitmapDrawable(context.getResources(), bitmap));
                }
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {
                Log.d(TAG, "Bitmap FAILED");
            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {
                Log.d(TAG, "Bitmap Prepare Load");
            }
        };

        PicassoUtils.loadChampionSplashBig(getContext(), championDto.getKey(), mTarget);
        PicassoUtils.loadChampionThumbnail(getContext(), championDto.getKey(), championImageView);
        championNameView.setText(championDto.getName() + ", " + championDto.getTitle());
        List<String> tags = championDto.getTags();
        StringBuilder role = new StringBuilder();
        int count = 0;
        if (tags != null)
            for (String tag:tags){
                if (count > 0)
                    role.append(", ");
                role.append(tag);
                count++;
            }
        championRoleView.setText(role);
        StringBuilder tips = formatTips(championDto.getAllytips());
        StringBuilder counterTips = formatTips(championDto.getEnemytips());
        tipsView.setText(Html.fromHtml(tips.toString()));
        counterTipsView.setText(Html.fromHtml(counterTips.toString()));
        historyView.setText(Html.fromHtml(championDto.getLore()));

        Passive passive = championDto.getPassive();
        passiveNameView.setText(passive.getName());
        passiveDescriptionView.setText(Html.fromHtml(passive.getDescription()));
        Image_ image = passive.getImage();
        PicassoUtils.loadPassiveImage(getContext(), image.getFull(), passiveImageView);

        //spells
        List<Spell> spells = championDto.getSpells();
        if (spells != null){
            int spellsCount = 1;
            for (Spell spell: spells){
                if (spellsCount == 1){
                    spellName1View.setText(spell.getName());
                    spellDescription1View.setText(spell.getDescription());
                    PicassoUtils.loadSpell(getContext(), spell.getImage().getFull(), spellImage1View, false);
                }else if (spellsCount == 2){
                    spellName2View.setText(spell.getName());
                    spellDescription2View.setText(spell.getDescription());
                    PicassoUtils.loadSpell(getContext(), spell.getImage().getFull(), spellImage2View, false);
                }else if (spellsCount == 3){
                    spellName3View.setText(spell.getName());
                    spellDescription3View.setText(spell.getDescription());
                    PicassoUtils.loadSpell(getContext(), spell.getImage().getFull(), spellImage3View, false);
                }else if (spellsCount == 4){
                    spellName4View.setText(spell.getName());
                    spellDescription4View.setText(spell.getDescription());
                    PicassoUtils.loadSpell(getContext(), spell.getImage().getFull(), spellImage4View, false);
                }
                spellsCount++;
            }
        }

    }

    @NonNull
    private StringBuilder formatTips(List<String> allytips) {
        StringBuilder tips = new StringBuilder();
        if (allytips != null)
            for (String tip: allytips){
                tips.append("* " + tip);
                tips.append("<br><br>");
            }
        return tips;
    }

    public void loadChampionProfile(Integer championId){
        if (!isTwoPane)
            progress = ProgressDialog.show(getContext(),
                    getActivity().getResources().getString(R.string.getting_champion_information),
                    getActivity().getResources().getString(R.string.please_wait), true);


        final long startTime = System.nanoTime();
        lolStaticService.getChampion(championId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChampionDto>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadChampionProfile completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (progress != null)
                            progress.dismiss();
                        if (!isTwoPane)
                            getActivity().finish();
                        Log.e(TAG, "Error", e);
                    }

                    @Override
                    public void onNext(ChampionDto championDto) {
                        if (progress != null)
                            progress.dismiss();
                        loadProfileInformationOnUI(championDto);
                    }
                });

    }


}
