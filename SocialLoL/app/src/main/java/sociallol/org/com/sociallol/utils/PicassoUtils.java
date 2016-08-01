package sociallol.org.com.sociallol.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

import sociallol.org.com.sociallol.BuildConfig;
import sociallol.org.com.sociallol.R;

public class PicassoUtils {


    public static final String DDRAGON_SERVICE_URL = BuildConfig.DDRAGON_API_URL;
    public static final String DRAGON_VERSION = BuildConfig.DDRAGON_API_VERSION;
    public static final String ITEM_PATH = DRAGON_VERSION + "/img/item/";
    public static final String SPELL_PATH = DRAGON_VERSION + "/img/spell/";
    public static final String SPELL_PASSIVE_PATH = DRAGON_VERSION + "/img/passive/";
    public static final String CHAMPION_PATH = DRAGON_VERSION + "/img/champion/";
    public static final String CHAMPION_LOADING_PATH = "/img/champion/loading/";
    public static final String CHAMPION_SPLASH_PATH = "/img/champion/splash/";
    public static final String PROFILE_ICON_PATH = DRAGON_VERSION + "/img/profileicon/";
    public static final String PNG_EXTENSION = ".png";
    public static final String JPG_EXTENSION = ".jpg";
    public static final String TAG = "PicassoUtils";

    public static void loadItem(Context context, Integer itemId, ImageView imageView){
        if (itemId == null){
            return;
        }
        Picasso.with(context)
                .load(DDRAGON_SERVICE_URL + ITEM_PATH + itemId + PNG_EXTENSION)
                .into(imageView);
    }

    public static void loadProfileIcon(Context context, Integer iconId, ImageView imageView){
        if (iconId == null){
            return;
        }
        Picasso.with(context)
                .load(DDRAGON_SERVICE_URL + PROFILE_ICON_PATH + iconId + PNG_EXTENSION)
                .into(imageView);
    }

    //http://ddragon.leagueoflegends.com/cdn/6.14.2/img/spell/SummonerFlash.png
    public static void loadSpell(Context context, String spellName, ImageView imageView, boolean withExtension){
        if (spellName == null){
            return;
        }
        String path = DDRAGON_SERVICE_URL + SPELL_PATH + spellName + PNG_EXTENSION;
        if (!withExtension)
            path = DDRAGON_SERVICE_URL + SPELL_PATH + spellName;
        Picasso.with(context)
                .load(path)
                .into(imageView);
    }

    //http://ddragon.leagueoflegends.com/cdn/6.14.2/img/champion/Aatrox.png
    public static void loadChampionThumbnail(Context context, String championKey, ImageView imageView){
        if (championKey == null){
            return;
        }
        Picasso.with(context)
                .load(DDRAGON_SERVICE_URL + CHAMPION_PATH + championKey + PNG_EXTENSION)
                .into(imageView);
    }

    public static void loadChampionThumbnailWidget(Context context,
                                                     String championKey,
                                                     RemoteViews remoteViews,
                                                     int resourceId,
                                                     int mAppWidgetId){
        if (championKey == null){
            return;
        }
        Picasso.with(context)
                .load(DDRAGON_SERVICE_URL + CHAMPION_PATH + championKey + PNG_EXTENSION)
                .into(remoteViews, resourceId, new int[] {mAppWidgetId});
    }

    public static void loadLeagueImage(Context context, Integer resourceId, ImageView imageView){
        if (resourceId == null){
            return;
        }
        Picasso.with(context)
                .load(resourceId)
                .placeholder(R.drawable.provisional)
                .into(imageView);
    }


    //http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg
    public static void loadChampionSplashLoading(final Context context, String championKey, Target target){
        if (championKey == null){
            return;
        }
        championKey = championKey + "_0";

        loadSplashArt(context, DDRAGON_SERVICE_URL + CHAMPION_LOADING_PATH + championKey + JPG_EXTENSION, target);
    }

    public static void loadChampionSplashBig(final Context context, String championKey, final Target target){
        if (championKey == null){
            return;
        }
        championKey = championKey + "_0";

        loadSplashArt(context, DDRAGON_SERVICE_URL + CHAMPION_SPLASH_PATH + championKey + JPG_EXTENSION, target);
    }

    private static void loadSplashArt(final Context context, String path, final Target target) {
        Picasso.with(context)
                .load(path)
                .into(target);
    }

    //http://ddragon.leagueoflegends.com/cdn/6.15.1/img/passive/Annie_Passive.png
    public static void loadPassiveImage(Context context, String passivePath, ImageView imageView){
        if (passivePath == null){
            return;
        }
        Picasso.with(context)
                .load(DDRAGON_SERVICE_URL + SPELL_PASSIVE_PATH + passivePath)
                .into(imageView);
    }
}
