package be.neodigi.parcelertest.data.model;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import be.neodigi.parcelertest.util.RealmListParcelConverter;
import io.realm.ColorRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;

@Parcel(implementations = { ColorRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Color.class })
public class Color extends RealmObject {

    public RealmList<RealmString> names;

    public RealmList<RealmString> getNames() {
        return names;
    }

    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public void setNames(RealmList<RealmString> names) {
        this.names = names;
    }
}
