package com.chiemy.app.crosshair;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created: chiemy
 * Date: 16/8/24
 * Description:
 */
public class DeskTopWidgetManager {
    private static DeskTopWidgetManager instance = null;
    private Context context;
    private WindowManager windowManager;
    
    private DeskTopWidgetManager(Context context){
        this.context = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }
    
    public static DeskTopWidgetManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DeskTopWidgetManager.class) {
                if (instance == null) {
                    instance = new DeskTopWidgetManager(context);
                }
            }
        }
        return instance;
    }

    private WindowManager.LayoutParams params;
    private CrosshairView crosshairView;
    public void showDeskTopWidget() {
        crosshairView = new CrosshairView(context);

        params = new WindowManager.LayoutParams();
        params.width = (int) CrosshairView.MAX_WIDTH;
        params.height = (int) CrosshairView.MAX_WIDTH;;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.format = PixelFormat.RGBA_8888;
        params.gravity = Gravity.CENTER;
        params.y = 0;
        params.x = 0;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;

        windowManager.addView(crosshairView, params);
    }

    public void dismissDeskTopWidget() {
        windowManager.removeViewImmediate(crosshairView);
    }

    public CrosshairView getCrosshairView() {
        return crosshairView;
    }
}
