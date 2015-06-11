package com.testtask.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {
    private int price;
    private String description;

    public Price(){

    }
    public Price(int price, String description) {
        this.price = price;
        this.description = description;
    }

    public Price(Parcel source) {
        this.price = source.readInt();
        this.description = source.readString();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getPrice());
        dest.writeString(getDescription());
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[0];
        }
    };
}