package be.neodigi.parcelertest.data.model;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import be.neodigi.parcelertest.util.RealmListParcelConverter;
import io.realm.FlagRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

@Parcel(implementations = { FlagRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Flag.class })
public class Flag extends RealmObject {

    @PrimaryKey
    public int id;
    @Required
    public String name;

    public RealmList<Color> colors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Color> getColors() {
        return colors;
    }

    @ParcelPropertyConverter(RealmListParcelConverter.class)
    public void setColors(RealmList<Color> colors) {
        this.colors = colors;
    }
}
