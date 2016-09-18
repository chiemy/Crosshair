package com.chiemy.app.crosshair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.SeekBar;

import com.larswerkman.holocolorpicker.ColorPicker;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getServiceIntent("");
        startService(intent);

        findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getServiceIntent("");
                stopService(intent);
            }
        });

        ColorPicker colorPicker = (ColorPicker) findViewById(R.id.picker);
        colorPicker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener() {
            @Override
            public void onColorChanged(int color) {
                Intent intent = getServiceIntent(DeskTopWidgetService.ACTION_COLOR);
                intent.putExtra(DeskTopWidgetService.EXTRA_COLOR, color);
                startService(intent);
            }
        });

        SeekBar widthSeekBar = (AppCompatSeekBar) findViewById(R.id.seek_bar_width);
        widthSeekBar.setOnSeekBarChangeListener(this);

        SeekBar scaleSeekBar = (AppCompatSeekBar) findViewById(R.id.seek_bar_scale);
        scaleSeekBar.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seek_bar_scale) {
            Intent intent = getServiceIntent(DeskTopWidgetService.ACTION_SCALE);
            intent.putExtra(DeskTopWidgetService.EXTRA_SCALE, (float) progress / seekBar.getMax());
            startService(intent);
        } else if (seekBar.getId() == R.id.seek_bar_width) {
            Intent intent = getServiceIntent(DeskTopWidgetService.ACTION_WIDTH);
            intent.putExtra(DeskTopWidgetService.EXTRA_WIDTH,  (float) progress / seekBar.getMax());
            startService(intent);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private Intent getServiceIntent(String action) {
        Intent intent = new Intent(MainActivity.this, DeskTopWidgetService.class);
        intent.setAction(action);
        return intent;
    }
}
