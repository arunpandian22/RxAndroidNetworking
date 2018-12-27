
package me.arun.andoridrxnetworking.resModel;

import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("age")
    private Object mAge;
    @SerializedName("gender")
    private Object mGender;
    @SerializedName("image")
    private Object mImage;
    @SerializedName("name")
    private String mName;
    @SerializedName("number")
    private Long mNumber;
    @SerializedName("person_id")
    private Long mPersonId;
    @SerializedName("place")
    private Object mPlace;
    @SerializedName("visits")
    private java.util.List<Object> mVisits;

    public Object getAge() {
        return mAge;
    }

    public void setAge(Object age) {
        mAge = age;
    }

    public Object getGender() {
        return mGender;
    }

    public void setGender(Object gender) {
        mGender = gender;
    }

    public Object getImage() {
        return mImage;
    }

    public void setImage(Object image) {
        mImage = image;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getNumber() {
        return mNumber;
    }

    public void setNumber(Long number) {
        mNumber = number;
    }

    public Long getPersonId() {
        return mPersonId;
    }

    public void setPersonId(Long personId) {
        mPersonId = personId;
    }

    public Object getPlace() {
        return mPlace;
    }

    public void setPlace(Object place) {
        mPlace = place;
    }

    public java.util.List<Object> getVisits() {
        return mVisits;
    }

    public void setVisits(java.util.List<Object> visits) {
        mVisits = visits;
    }

}
