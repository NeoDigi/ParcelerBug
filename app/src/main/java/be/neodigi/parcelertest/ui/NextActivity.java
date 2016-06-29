package be.neodigi.parcelertest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.parceler.Parcels;

import be.neodigi.parcelertest.R;
import be.neodigi.parcelertest.data.model.Country;
import be.neodigi.parcelertest.ui.base.BaseActivity;

public class NextActivity extends BaseActivity {

    private static final String EXTRA_COUNTRY = "extraCountry";

    private Country mCurrentCountry;

    public static Intent getIntent(Context context, Country country) {
        Intent intent = new Intent(context, NextActivity.class);
        intent.putExtra(EXTRA_COUNTRY, Parcels.wrap(country));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        activityComponent().inject(this);

        mCurrentCountry = Parcels.unwrap(getIntent().getExtras().getParcelable(EXTRA_COUNTRY));
    }
}
