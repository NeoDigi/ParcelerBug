package be.neodigi.parcelertest.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import be.neodigi.parcelertest.ParcelerApplication;
import be.neodigi.parcelertest.injection.ApplicationContext;
import be.neodigi.parcelertest.injection.module.ApplicationModule;
import dagger.Component;
import io.realm.Realm;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(ParcelerApplication issApplication);

    @ApplicationContext
    Context context();
    Application application();
    Realm realm();
    Bus eventBus();
}

