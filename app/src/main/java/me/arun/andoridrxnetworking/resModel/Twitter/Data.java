
package me.arun.andoridrxnetworking.resModel.Twitter;

import com.google.gson.annotations.Expose;

public class Data
{

    @Expose
    private Twitter twitter;

    public Twitter getTwitter()
    {
        return twitter;
    }

    public void setTwitter(Twitter twitter)
    {
        this.twitter = twitter;
    }

}
