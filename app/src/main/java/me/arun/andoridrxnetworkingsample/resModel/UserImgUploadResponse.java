package me.arun.andoridrxnetworkingsample.resModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserImgUploadResponse {

	@SerializedName("image_id")
	@Expose
	private int imageId;

	@SerializedName("url")
	@Expose
	private String url;

	@SerializedName("status")
	@Expose
	private boolean status;

	@SerializedName("message")
	@Expose
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setImageId(int imageId){
		this.imageId = imageId;
	}

	public int getImageId(){
		return imageId;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UserImgUploadResponse{" +
			"image_id = '" + imageId + '\'' + 
			",url = '" + url + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}