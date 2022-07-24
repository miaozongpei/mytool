package com.miao.gp.mytool.service.vote;

public class VoteRes {
   // {"code":"0","message":"投票成功，您还可以对此视频投2票","time":1658503125,"count":0,"data":null}
    private String code;
    private String message;
    private String time;
    private String count;
    private String data;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getCount() {
        return count;
    }

    public String getData() {
        return data;
    }
}
