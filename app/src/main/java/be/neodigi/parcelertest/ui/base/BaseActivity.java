package be.neodigi.parcelertest.ui.base;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

import be.neodigi.parcelertest.ParcelerApplication;
import be.neodigi.parcelertest.injection.component.ActivityComponent;
import be.neodigi.parcelertest.injection.component.DaggerActivityComponent;
import be.neodigi.parcelertest.injection.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    private Locale mCurrentLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentLocale = getResources().getConfiguration().locale;

        // Fix orientation in portrait mode
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(ParcelerApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getResources().getConfiguration().locale != mCurrentLocale) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
                        finish();
                        startActivity(getIntent());
                    } else {
                        recreate();
                    }
                }
            }, 1);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
