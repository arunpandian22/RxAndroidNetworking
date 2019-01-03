
package me.arun.andoridrxnetworking.resModel.country;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("country")
    private Country mCountry;

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        mCountry = country;
    }

}
