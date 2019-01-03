
package me.arun.andoridrxnetworking.resModel.country;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Language {

    @SerializedName("code")
    private String mCode;
    @SerializedName("name")
    private String mName;

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

}
