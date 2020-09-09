package com.example.batteryalarm;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Context mContext;
    TextView mTextViewPercentage;
    ProgressBar mProgressBar;
    EditText edit;
    Vibrator vibrator;
    private int mProgressStatus = 0;

    public void Vib()
    {
     vibrator=((Vibrator)getSystemService(VIBRATOR_SERVICE));
     vibrator.vibrate(VibrationEffect.createWaveform(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500},1));
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
            Toast.makeText(this, "Volume Up", Toast.LENGTH_LONG).show();
            vibrator.cancel();
            ringtoneSound.stop();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Toast.makeText(this, "Volume Down", Toast.LENGTH_LONG).show();
            vibrator.cancel();
            ringtoneSound.stop();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        edit = findViewById(R.id.percent);
        BatteryManager bm = (BatteryManager) getSystemService(BATTERY_SERVICE);
        final int percentage = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        String st = "Battery Percentage is " + percentage + " %";
        text.setText(st);
        mTextViewPercentage = (TextView) findViewById(R.id.tv_percentage);
        mProgressBar = (ProgressBar) findViewById(R.id.pb);
        mProgressStatus = percentage;
        mProgressBar.setProgress(mProgressStatus);
        mTextViewPercentage.setText("" + mProgressStatus + "%");

        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone ringtoneSound = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);

        Button button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String num = edit.getText().toString();
               int finalcent= Integer.parseInt(String.valueOf(num));
                Toast msg = Toast.makeText(getBaseContext(),num,Toast.LENGTH_SHORT);
                msg.show();
                if (finalcent == percentage) {
                    Vib();
                    if (ringtoneSound != null) {
                        ringtoneSound.play();
                    }
                }
                else {
                    Toast msgs = Toast.makeText(getBaseContext(),finalcent,Toast.LENGTH_SHORT);
                    msgs.show();
                }

            }
        });

        Button stop= findViewById(R.id.button);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.cancel();
                ringtoneSound.stop();

            }
        });







    }
}





