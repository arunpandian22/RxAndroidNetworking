
package me.arun.andoridrxnetworkingsample.resModel.imageResponse;
import com.google.gson.annotations.SerializedName;


public class ImageUploadResponse {

    @SerializedName("data")
    private Data mData;

    @SerializedName("status")
    private int mStatus;
    @SerializedName("success")
    private boolean mSuccess;

    public Data getData() {
        return mData;
    }

    public void setData(Data data) {
        mData = data;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public Boolean getSuccess() {
        return mSuccess;
    }

    public void setSuccess(Boolean success) {
        mSuccess = success;
    }

}
