package be.neodigi.parcelertest.data.model;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.RealmStringRealmProxy;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = { RealmStringRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { RealmString.class })
public class RealmString extends RealmObject {

    @PrimaryKey
    public int id;

    public String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
