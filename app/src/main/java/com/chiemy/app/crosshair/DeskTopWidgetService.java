package com.chiemy.app.crosshair;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

/**
 * Created: chiemy
 * Date: 16/8/24
 * Description:
 */
public class DeskTopWidgetService extends Service {
    public static final String ACTION_COLOR = "action_color";
    public static final String ACTION_SCALE = "action_scale";
    public static final String ACTION_WIDTH = "action_width";

    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_SCALE = "scale";
    public static final String EXTRA_WIDTH = "width";

    private DeskTopWidgetManager deskTopWidgetManager;
    @Override
    public void onCreate() {
        super.onCreate();
        deskTopWidgetManager = DeskTopWidgetManager.getInstance(this);
        deskTopWidgetManager.showDeskTopWidget();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String action = intent.getAction();
            CrosshairView crosshairView = deskTopWidgetManager.getCrosshairView();
            if (TextUtils.equals(action, ACTION_COLOR)) {
                crosshairView.setColor(intent.getIntExtra(EXTRA_COLOR, Color.RED));
            } else if (TextUtils.equals(action, ACTION_SCALE)) {
                float ratio = intent.getFloatExtra(EXTRA_SCALE, 0);
                crosshairView.setScale(CrosshairView.MAX_SCALE * ratio);
            } else if (TextUtils.equals(action, ACTION_WIDTH)) {
                float ratio = intent.getFloatExtra(EXTRA_WIDTH, 10);
                crosshairView.setLineWidth(2 + ratio * 10);
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        deskTopWidgetManager.dismissDeskTopWidget();
    }
}
