
package me.arun.andoridrxnetworkingsample.resModel.country;


import com.google.gson.annotations.SerializedName;


public class ModelCountry {

    @SerializedName("data")
    private Data mData;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

}
