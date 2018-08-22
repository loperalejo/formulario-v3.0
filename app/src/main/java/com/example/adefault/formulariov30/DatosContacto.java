package com.example.adefault.formulariov30;

import android.os.Parcel;
import android.os.Parcelable;

public class DatosContacto implements Parcelable {
    private String name;
    private String password;
    private String email;
    private String sex;
    private String date;
    private String place;
    private String hobbies;

    public DatosContacto(){
        name = "";
        password = "";
        email = "";
        sex = "";
        date = "";
        place = "";
        hobbies = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return name;
    }

    protected DatosContacto(Parcel in) {
        name = in.readString();
        password = in.readString();
        email = in.readString();
        sex = in.readString();
        date = in.readString();
        place = in.readString();
        hobbies = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeString(sex);
        dest.writeString(date);
        dest.writeString(place);
        dest.writeString(hobbies);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<DatosContacto> CREATOR = new Parcelable.Creator<DatosContacto>() {
        @Override
        public DatosContacto createFromParcel(Parcel in) {
            return new DatosContacto(in);
        }

        @Override
        public DatosContacto[] newArray(int size) {
            return new DatosContacto[size];
        }
    };
}
