
package me.arun.andoridrxnetworkingsample.resModel.Twitter;

import com.google.gson.annotations.Expose;

public class Tweet {

    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
