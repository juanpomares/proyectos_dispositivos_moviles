package juanpomares.tfm.matermoviles.connectionwear;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class MyWearableListenerService extends WearableListenerService
{
    @Override
    public void onMessageReceived(MessageEvent messageEvent)
    {
        Log.d("MyWearListenerService", "MessageReceived");

        if(messageEvent.getPath().equals(PublicConstants.STOP_ACTIVITY))
        {
            AppSharedPreferences.setAppOpen(getApplicationContext(), false);
            super.onMessageReceived(messageEvent);
        }else
        {
            if (!AppSharedPreferences.getAppOpen(getApplicationContext()) && messageEvent.getPath().equals(PublicConstants.START_ACTIVITY))
            {
            /*if (messageEvent.getPath().contains(PublicConstants.START_ACTIVITY))
            {*/
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                //}
            } else
                super.onMessageReceived(messageEvent);
                //Log.d("Listener", "App Opened yet jeje");
        }
    }
}
