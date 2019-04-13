
package me.arun.andoridrxnetworkingsample.resModel.country;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("currency")
    private String mCurrency;
    @SerializedName("emoji")
    private String mEmoji;
    @SerializedName("languages")
    private List<Language> mLanguages;
    @SerializedName("name")
    private String mName;
    @SerializedName("native")
    private String mNative;

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getEmoji() {
        return mEmoji;
    }

    public void setEmoji(String emoji) {
        mEmoji = emoji;
    }

    public List<Language> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(List<Language> languages) {
        mLanguages = languages;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNative() {
        return mNative;
    }
    public void setNative(String mnative){

            mNative = mnative;
    }


}
