package sociallol.org.com.sociallol.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
public class SocialLoLSyncService extends Service{
    private static final Object sSyncAdapterLock = new Object();
    private static SocialLoLSyncAdapter sSyncAdapter = null;

    @Override
    public void onCreate() {
        synchronized (sSyncAdapterLock) {
            if (sSyncAdapter == null)
                sSyncAdapter = new SocialLoLSyncAdapter(getApplicationContext(), true);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sSyncAdapter.getSyncAdapterBinder();
    }
}
