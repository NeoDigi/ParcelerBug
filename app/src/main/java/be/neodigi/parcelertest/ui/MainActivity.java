package be.neodigi.parcelertest.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import be.neodigi.parcelertest.R;
import be.neodigi.parcelertest.data.model.BusEvent;
import be.neodigi.parcelertest.data.model.Color;
import be.neodigi.parcelertest.data.model.Country;
import be.neodigi.parcelertest.data.model.Flag;
import be.neodigi.parcelertest.data.model.RealmString;
import be.neodigi.parcelertest.ui.base.BaseActivity;
import io.realm.RealmList;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject MainPresenter mMainPresenter;
    @Inject MainAdapter mMainAdapter;
    @Inject Bus mBus;

    RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);

        mMainPresenter.attachView(this);
        mBus.register(this);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);

        List<Country> countries = new ArrayList<>();

        Country c = new Country();
        Flag f = new Flag();
        RealmList<Color> colors = new RealmList<>();
        Color color = new Color();
        RealmList<RealmString> list = new RealmList<>();
        RealmString s1 = new RealmString();
        RealmString s2 = new RealmString();
        s1.setId(1);
        s1.setValue("S1");
        s2.setId(2);
        s2.setValue("S2");
        list.add(s1);
        list.add(s2);
        color.setNames(list);
        colors.add(color);
        f.setId(1);
        f.setName("flag");
        f.setColors(colors);
        c.setId(1);
        c.setName("country");
        c.setFlag(f);
        c.setStatus("status");
        countries.add(c);

        mRecycler.setAdapter(mMainAdapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mMainAdapter.setCountries(countries);
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        mBus.unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onCountryClick(BusEvent.CountryClicked event) {
        startActivity(NextActivity.getIntent(this, event.getCountry()));
    }
}
