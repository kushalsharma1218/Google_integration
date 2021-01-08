package com.example.communicatingtootherappstest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView cur_ver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cur_ver = findViewById(R.id.showCurrentVer);
        cur_ver.setText("Current Ver : " +String.valueOf(isChromeInstalledAndVersionGreaterThan65()));



    }
    private int isChromeInstalledAndVersionGreaterThan65() {
        PackageInfo pInfo;
        try {
            pInfo = getPackageManager().getPackageInfo("com.android.chrome", 0);
        } catch (PackageManager.NameNotFoundException e) {
            //chrome is not installed on the device
            return -1;
        }
        if (pInfo != null) {
            //Chrome has versions like 68.0.3440.91, we need to find the major version
            //using the first dot we find in the string
            int firstDotIndex = pInfo.versionName.indexOf(".");
            //take only the number before the first dot excluding the dot itself
            String majorVersion = pInfo.versionName.substring(0, firstDotIndex);
            return Integer.parseInt(majorVersion) ;
        }
        return -1;
    }
}