package edu.navalkishoreb.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public final class TvShow implements Parcelable {

  private long id;
  private String imageUrl;
  private String title;
  private String description;
  private float rating;

  public TvShow() {

  }

   private TvShow(Parcel in) {
    id = in.readLong();
    imageUrl = in.readString();
    title = in.readString();
    description = in.readString();
    rating = in.readFloat();
  }

  public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
    @Override public TvShow createFromParcel(Parcel in) {
      return new TvShow(in);
    }

    @Override public TvShow[] newArray(int size) {
      return new TvShow[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel parcel, int i) {
    parcel.writeLong(id);
    parcel.writeString(imageUrl);
    parcel.writeString(title);
    parcel.writeString(description);
    parcel.writeFloat(rating);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }
}
