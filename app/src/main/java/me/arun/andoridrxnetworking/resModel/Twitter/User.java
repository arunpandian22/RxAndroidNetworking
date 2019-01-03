
package me.arun.andoridrxnetworking.resModel.Twitter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("created_at")
    private String createdAt;
    @Expose
    private String description;
    @SerializedName("followers_count")
    private Long followersCount;
    @Expose
    private String id;
    @Expose
    private String name;
    @SerializedName("profile_image_url")
    private String profileImageUrl;
    @SerializedName("screen_name")
    private String screenName;
    @Expose
    private List<Tweet> tweets;
    @SerializedName("tweets_count")
    private Long tweetsCount;
    @Expose
    private String url;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Long getTweetsCount() {
        return tweetsCount;
    }

    public void setTweetsCount(Long tweetsCount) {
        this.tweetsCount = tweetsCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
