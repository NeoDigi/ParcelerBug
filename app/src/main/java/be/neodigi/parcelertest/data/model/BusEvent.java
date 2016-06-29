package be.neodigi.parcelertest.data.model;

public class BusEvent {

    public static class CountryClicked {
        Country country;

        public CountryClicked(Country country) {
            this.country = country;
        }

        public Country getCountry() {
            return country;
        }
    }
}
