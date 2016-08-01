package sociallol.org.com.sociallol.database.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

import sociallol.org.com.sociallol.database.SocialLoLDB;
import sociallol.org.com.sociallol.database.SocialLoLDBImpl;
import sociallol.org.com.sociallol.database.models.Champion;

public class ChampionsLoader extends AsyncTaskLoader<List<Champion>> {
    private static final String TAG = "ChampionsLoader";
    private SocialLoLDB socialLoLDB;

    public ChampionsLoader(Context context) {
        super(context);
        socialLoLDB = new SocialLoLDBImpl(getContext());
    }

    @Override
    public List<Champion> loadInBackground() {
        // this is just a simple query, could be anything that gets a cursor
        return socialLoLDB.getChampions();
    }
}
