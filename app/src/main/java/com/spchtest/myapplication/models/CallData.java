package com.spchtest.myapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Ignore;

public class CallData {

    @SerializedName("help")
    @Expose
    private String help;

    @SerializedName("success")
    @Expose
    private Boolean success;


    @SerializedName("result")
    @Expose
    private Result result;

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
