package be.neodigi.parcelertest.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import be.neodigi.parcelertest.R;
import be.neodigi.parcelertest.data.model.BusEvent;
import be.neodigi.parcelertest.data.model.Country;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CountryHolder> {

    @Inject Bus mEventBus;

    private List<Country> mCountries = new ArrayList<>();

    @Inject
    public MainAdapter() {

    }

    @Override
    public MainAdapter.CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(MainAdapter.CountryHolder holder, int position) {
        final Country country = mCountries.get(position);

        holder.mName.setText(country.getName());
        holder.country = country;

    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
    }

    class CountryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mName;

        public Country country;

        public CountryHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mEventBus.post(new BusEvent.CountryClicked(country));
        }
    }
}
