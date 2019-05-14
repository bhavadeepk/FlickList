package com.bob.flicklist.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {

   private String id;
   private String owner;
   private String secret;
   private String server;
   private int farm;
   private String title;
   private int ispublic;
   private int isfriend;
   private int isfamily;
   private String imageUrl;
   private Bitmap image;

   public Photo(String id, String owner, String secret, String server, int farm, String title, int ispublic, int isfriend, int isfamily) {
      this.id = id;
      this.owner = owner;
      this.secret = secret;
      this.server = server;
      this.farm = farm;
      this.title = title;
      this.ispublic = ispublic;
      this.isfriend = isfriend;
      this.isfamily = isfamily;
      makeImageUrl();
   }

   protected Photo(Parcel in) {
      id = in.readString();
      owner = in.readString();
      secret = in.readString();
      server = in.readString();
      farm = in.readInt();
      title = in.readString();
      ispublic = in.readInt();
      isfriend = in.readInt();
      isfamily = in.readInt();
      imageUrl = in.readString();
      image = in.readParcelable(Bitmap.class.getClassLoader());
   }

   public static final Creator<Photo> CREATOR = new Creator<Photo>() {
      @Override
      public Photo createFromParcel(Parcel in) {
         return new Photo(in);
      }

      @Override
      public Photo[] newArray(int size) {
         return new Photo[size];
      }
   };

   private void makeImageUrl() {
      StringBuilder sb = new StringBuilder("https://farm").append(farm).append(".staticflickr.com/")
              .append(server).append("/").append(id).append("_").append(secret).append(".jpg");
      this.imageUrl =  sb.toString();
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getOwner() {
      return owner;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public String getSecret() {
      return secret;
   }

   public void setSecret(String secret) {
      this.secret = secret;
   }

   public String getServer() {
      return server;
   }

   public void setServer(String server) {
      this.server = server;
   }

   public int getFarm() {
      return farm;
   }

   public void setFarm(int farm) {
      this.farm = farm;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getIspublic() {
      return ispublic;
   }

   public void setIspublic(int ispublic) {
      this.ispublic = ispublic;
   }

   public int getIsfriend() {
      return isfriend;
   }

   public void setIsfriend(int isfriend) {
      this.isfriend = isfriend;
   }

   public int getIsfamily() {
      return isfamily;
   }

   public void setIsfamily(int isfamily) {
      this.isfamily = isfamily;
   }

   public String getImageUrl() {
      makeImageUrl();
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   public void setImage(Bitmap image) {
      this.image = image;
   }

   public Bitmap getImage() {
      return image;
   }

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel dest, int flags) {
      dest.writeString(id);
      dest.writeString(owner);
      dest.writeString(secret);
      dest.writeString(server);
      dest.writeInt(farm);
      dest.writeString(title);
      dest.writeInt(ispublic);
      dest.writeInt(isfriend);
      dest.writeInt(isfamily);
      dest.writeString(imageUrl);
      dest.writeParcelable(image, flags);
   }
}
