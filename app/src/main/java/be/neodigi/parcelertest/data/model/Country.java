package be.neodigi.parcelertest.data.model;

import android.support.annotation.StringDef;

import org.parceler.Parcel;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import io.realm.CountryRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = { CountryRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { Country.class })
public class Country extends RealmObject {

    public static final String STATUS = "test";

    @PrimaryKey
    public int id;
    @CountryStatusDef
    public String status;
    public String name;

    public Flag flag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({ STATUS })
    private @interface CountryStatusDef {
    }

}
