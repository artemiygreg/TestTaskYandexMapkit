package com.testtask.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 10.06.2015.
 */
public class Task implements Parcelable {
    private long ID;
    private String title;
    private long date;
    private String text;
    private String longText;
    private String durationLimitText;
    private int zoomLevel;
    private Location location;
    private List<Price> prices;
    private String locationText;
    private boolean translation;


    public Task(Builder builder) {
        this.ID = builder.id;
        this.title = builder.title;
        this.date = builder.date;
        this.text = builder.text;
        this.longText = builder.longText;
        this.durationLimitText = builder.durationLimitText;
        this.zoomLevel = builder.zoomLevel;
        this.prices = builder.prices;
        this.locationText = builder.locationText;
        this.translation = builder.translation;
    }
    private Task(Parcel source){
        this.ID = source.readLong();
        this.title = source.readString();
        this.date = source.readLong();
        this.text = source.readString();
        this.longText = source.readString();
        this.durationLimitText = source.readString();
        this.zoomLevel = source.readInt();
        location = new Location();
        location.setLat(source.readDouble());
        location.setLon(source.readDouble());
        prices = new ArrayList<>();
        source.readList(prices, Price.class.getClassLoader());
        this.locationText = source.readString();
        this.translation = parseTranslation(source.readInt());
    }

    public long getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public long getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getLongText() {
        return longText;
    }

    public String getDurationLimitText() {
        return durationLimitText;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public boolean isTranslation() {
        return translation;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString(){
        return "ID - " + ID + ", title - " + title + ", date - " + date + ", text - " + text +
                "\nlongText - " + longText + ",durationLimitText - " + durationLimitText +
                "\n lat - " + location.getLat() + ",lon - " + location.getLon() + ", zoomLevel - " + zoomLevel +
                "\n translation - " + translation;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getID());
        dest.writeString(getTitle());
        dest.writeLong(getDate());
        dest.writeString(getText());
        dest.writeString(getLongText());
        dest.writeString(getDurationLimitText());
        dest.writeInt(getZoomLevel());
        dest.writeDouble(location.getLat());
        dest.writeDouble(location.getLon());
        dest.writeList(prices);
        dest.writeString(getLocationText());
        dest.writeInt(parseTranslation(isTranslation()));

    }
    private int parseTranslation(boolean translation){
        return translation ? 1 : 0;
    }
    private boolean parseTranslation(int translation){
        return translation == 1;
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[0];
        }
    };

    public String getLocationText() {
        return locationText;
    }

    public static class Builder {
        private long id;
        private String title;
        private long date;
        private String text;
        private String longText;
        private String durationLimitText;
        private double lat;
        private double lon;
        private int zoomLevel;
        private List<Price> prices;
        private String locationText;
        private boolean translation;

        public Builder() {

        }

        public Task build() {
            return new Task(this);
        }

        public Builder setId(long id) {
            this.id = id;

            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;

            return this;
        }

        public Builder setDate(long date) {
            this.date = date;

            return this;
        }

        public Builder setText(String text) {
            this.text = text;

            return this;
        }

        public Builder setLongText(String longText) {
            this.longText = longText;

            return this;
        }

        public Builder setDurationLimitText(String durationLimitText) {
            this.durationLimitText = durationLimitText;

            return this;
        }

        public Builder setZoomLevel(int zoomLevel) {
            this.zoomLevel = zoomLevel;

            return this;
        }

        public Builder setPrices(List<Price> prices) {
            this.prices = prices;

            return this;
        }

        public Builder setTranslation(boolean translation) {
            this.translation = translation;

            return this;
        }

        public Builder setLat(double lat) {
            this.lat = lat;

            return this;
        }

        public Builder setLon(double lon) {
            this.lon = lon;

            return this;
        }

        public Builder setLocationText(String locationText) {
            this.locationText = locationText;

            return this;
        }
    }
}