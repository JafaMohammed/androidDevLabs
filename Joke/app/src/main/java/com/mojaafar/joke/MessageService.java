package com.mojaafar.joke;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * * helper methods.
 */
public class MessageService extends IntentService {
    //USE A CONSTANT TO PASS A A MESSAGE FROM THE MAIN ACTTIVITY TO THE SERVICE
    public static final String EXTRA_MESSAGE = "MESSAGE";
    /*Declare a private NOTIFICATION_ID which will be used to identify a notification.
    * If we send another notification with the same ID, it will replace
    the current notification.
    * This is useful if you want to update an existing notification with new information.
    **/
    public static final int NOTIFICATION_ID = 1;
    public MessageService() {
        super("MessageService");
//required constructor
    }
    @Override
    protected void onHandleIntent(Intent intent) {
//this method contains the code you want to run when the service receives an intent
        synchronized (this) {
            try {
//wait for 10 seconds
                wait(10000);
            } catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
//get the text from the intent
        String text = intent.getStringExtra(EXTRA_MESSAGE);
//call showText method
        showText(text);
    }
    private void showText(final String text) {
        Log.v("DelayedMessageService", "What is the secret of comedy?? " + text);
// the above line of code logs a piece of text so that we can see it in the logcat
//Create an Intent
        Intent intent = new Intent(this, MainActivity.class);
//use a TaskStackBuilder to make the back button play nicely and create the pending intent
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent);
/*create Pending intent
Next, we get the pending intent from the TaskStackBuilder using its
getPendingIntent() method.
The getPendingIntent() method takes two int parameters, a request code that can be
used to identify the intent and a flag that specifies the pending intent’s behavior.
*/
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
/*Build the Notification
You create a notification using a notification builder to create a new
Notification object.
The notification builder allows you to create a notification with a specific
set of features, without writing too much code.
Each notification must include a small icon, a title, and some text.*/
        Notification notification = new Notification.Builder(this)
//this displays a small notification icon-in this case the mipmap calledic_joke_round
                .setSmallIcon(R.mipmap.ic_joke_round)
//set the title as your application name
                .setContentTitle(getString(R.string.app_name))
//set the content text
                .setContentText(text)
                //make the notification disappear when clicked
                .setAutoCancel(true)
//give it a maximum priority to allow peeking
                .setPriority(Notification.PRIORITY_MAX)
//set it to vibrate to get a large heads-up notification
                .setDefaults(Notification.DEFAULT_VIBRATE)
//open main activity on clicking the notification
                .setContentIntent(pendingIntent)
                .build();
//display the notification using the Android notification service
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
//Issue the notification
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}