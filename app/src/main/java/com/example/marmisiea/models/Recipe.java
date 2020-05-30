package com.example.marmisiea.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Recipe  implements Parcelable {

  //field are named the exact same way as in the json recieve by request
  private String title;
  private String publisher;
  private String[] ingredients;
  private String recipe_id;
  private String image_url;
  private Float social_rank;

  public Recipe(String title, String publisher, String[] ingredients, String recipe_id,
                String image_url, Float social_rank) {
    this.title = title;
    this.publisher = publisher;
    this.ingredients = ingredients;
    this.recipe_id = recipe_id;
    this.image_url = image_url;
    this.social_rank = social_rank;
  }

  public Recipe() {
  }

  protected Recipe(Parcel in) {
    title = in.readString();
    publisher = in.readString();
    ingredients = in.createStringArray();
    recipe_id = in.readString();
    image_url = in.readString();
    if (in.readByte() == 0) {
      social_rank = null;
    } else {
      social_rank = in.readFloat();
    }
  }

  public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
    @Override
    public Recipe createFromParcel(Parcel in) {
      return new Recipe(in);
    }

    @Override
    public Recipe[] newArray(int size) {
      return new Recipe[size];
    }
  };

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String[] getIngredients() {
    return ingredients;
  }

  public void setIngredients(String[] ingredients) {
    this.ingredients = ingredients;
  }

  public String getRecipe_id() {
    return recipe_id;
  }

  public void setRecipe_id(String recipe_id) {
    this.recipe_id = recipe_id;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public Float getSocial_rank() {
    return social_rank;
  }

  public void setSocial_rank(Float social_rank) {
    this.social_rank = social_rank;
  }

  @Override
  public String toString() {
    return "Recipe{" +
            "title='" + title + '\'' +
            ", publisher='" + publisher + '\'' +
            ", ingredients=" + Arrays.toString(ingredients) +
            ", recipe_id='" + recipe_id + '\'' +
            ", image_url='" + image_url + '\'' +
            ", social_rank=" + social_rank +
            '}';
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(title);
    dest.writeString(publisher);
    dest.writeStringArray(ingredients);
    dest.writeString(recipe_id);
    dest.writeString(image_url);
    if (social_rank == null) {
      dest.writeByte((byte) 0);
    } else {
      dest.writeByte((byte) 1);
      dest.writeFloat(social_rank);
    }
  }

}
