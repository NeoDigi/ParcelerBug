package be.neodigi.parcelertest;

import android.app.Application;
import android.content.Context;

import be.neodigi.parcelertest.injection.component.ApplicationComponent;
import be.neodigi.parcelertest.injection.component.DaggerApplicationComponent;
import be.neodigi.parcelertest.injection.module.ApplicationModule;

public class ParcelerApplication extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static ParcelerApplication get(Context context) {
        return (ParcelerApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}