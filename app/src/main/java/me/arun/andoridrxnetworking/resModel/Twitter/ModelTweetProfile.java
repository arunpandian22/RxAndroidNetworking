
package me.arun.andoridrxnetworking.resModel.Twitter;

import com.google.gson.annotations.Expose;


public class ModelTweetProfile {

    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
