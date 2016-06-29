package be.neodigi.parcelertest.injection.component;

import be.neodigi.parcelertest.injection.PerActivity;
import be.neodigi.parcelertest.injection.module.ActivityModule;
import be.neodigi.parcelertest.ui.MainActivity;
import be.neodigi.parcelertest.ui.NextActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(NextActivity nextActivity);
}
