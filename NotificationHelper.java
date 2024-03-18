package com.zahcode.water_alarm.nfc_notification;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.zahcode.water_alarm.R;
import java.util.Random;

public class NotificationHelper {

    private static final String CHANNEL_ID = "channel_id";
    private static final int NOTIFICATION_ID = (int) System.currentTimeMillis();

    private static final String[] notificationMessages = {
            "Stay hydrated to stay healthy! Remember to drink water throughout the day.",
            "Water is essential for your body's functions. Take a sip and feel refreshed!",
            "Keep a water bottle handy as a reminder to drink more water.",
            "Drinking water can help you maintain a balanced mood and stay focused.",
            "Hydrate your body for better digestion and metabolism.",
            "Feeling tired? Try drinking a glass of water to boost your energy levels.",
            "Water is the best choice to quench your thirst and hydrate your body.",
            "Make it a habit to drink water before and after every meal.",
            "Stay hydrated, stay active! Fuel your workouts with plenty of water.",
            "Your body needs water to function properly. Keep it hydrated!",
            "Keep calm and drink water! Hydration is key to a healthy lifestyle.",
            "Drinking water helps flush toxins out of your body. Stay hydrated and detoxify!",
            "Aim to drink at least 8 glasses of water every day for optimal health.",
            "Don't forget to drink water when you're feeling stressed or anxious.",
            "Water is your body's best friend. Treat it well and drink up!",
            "Stay ahead of dehydration by drinking water regularly throughout the day.",
            "Swap sugary drinks for water to cut down on calories and stay healthy.",
            "Feeling hungry? Sometimes your body is just thirsty. Drink water first!",
            "Start your morning with a glass of water to kickstart your day.",
            "Hydrate, hydrate, hydrate! Keep sipping water to keep your body happy."
    };

    public static void displayRandomNotification(Context context, String title, String content) {
        Random random = new Random();
        int randomIndex = random.nextInt(notificationMessages.length);
        String randomMessage = notificationMessages[randomIndex];
        displayNotification(context, title, randomMessage);
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Channel";
            String description = "Channel Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void displayNotification(Context context, String title, String content) {
        createNotificationChannel(context);
        // Load custom sound
        Uri soundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.s_notification_11);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_done)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(soundUri);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

