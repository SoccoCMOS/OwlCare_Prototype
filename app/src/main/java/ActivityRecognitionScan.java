import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.*;
import android.app.*;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by DUALCOMPUTER on 1/12/2017.
 */

public class ActivityRecognitionScan implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Context context;
    private static final String TAG = "ActivityRecognition";
    private static GoogleApiClient mActivityRecognitionClient;
    private static PendingIntent callbackIntent;

    public ActivityRecognitionScan(Context context) {
        this.context=context;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
