
package me.arun.andoridrxnetworking.resModel.Twitter;

import com.google.gson.annotations.Expose;

public class Twitter {

    @Expose
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
