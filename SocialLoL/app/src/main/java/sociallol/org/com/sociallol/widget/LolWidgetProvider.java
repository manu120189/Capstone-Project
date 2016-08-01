package sociallol.org.com.sociallol.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import sociallol.org.com.sociallol.R;
import sociallol.org.com.sociallol.api.LoLAPI;
import sociallol.org.com.sociallol.api.models.ChampionFree;
import sociallol.org.com.sociallol.api.models.ChampionFreeList;
import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;
import sociallol.org.com.sociallol.utils.PicassoUtils;

/**
 * Created by predator on 7/31/2016.
 */
public class LolWidgetProvider extends AppWidgetProvider {
    private SocialLoLDB socialLoLDB;
    private LoLAPI apiService;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int count = appWidgetIds.length;

        for (int i = 0; i < count; i++) {
            int widgetId = appWidgetIds[i];
            updateWidget(context, appWidgetManager, appWidgetIds, widgetId);
        }
    }

    private void updateWidget(final Context context,final AppWidgetManager appWidgetManager,final int[] appWidgetIds, final int widgetId) {
        socialLoLDB = new SocialLoLDBImpl(context);
        apiService = new LoLAPI();
        apiService.getFreeChampions()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ChampionFreeList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ChampionFreeList championFreeList) {
                        List<ChampionFree> champions = championFreeList.getChampions();
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                                R.layout.widget_free_champions);
                        int i = 0;
                        for (ChampionFree championFree: champions){
                            Champion champion = socialLoLDB.getChampion(championFree.getId());
                            int[] imageViews = new int[]{
                                    R.id.imageView1,
                                    R.id.imageView2,
                                    R.id.imageView3,
                                    R.id.imageView4,
                                    R.id.imageView5,
                                    R.id.imageView6,
                                    R.id.imageView7,
                                    R.id.imageView8,
                                    R.id.imageView9,
                                    R.id.imageView10
                            };
                            PicassoUtils.loadChampionThumbnailWidget(
                                    context,
                                    champion.getChampionKey(),
                                    remoteViews,
                                    imageViews[i],
                                    widgetId);
                            i++;
                            }
                            Intent intent = new Intent(context, LolWidgetProvider.class);
                            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
                            appWidgetManager.updateAppWidget(widgetId, remoteViews);
                    }
                });

    }
}
