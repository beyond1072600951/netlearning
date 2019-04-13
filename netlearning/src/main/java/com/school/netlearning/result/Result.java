package com.school.netlearning.result;

public class Result {

    //状态（success,fail）
    private String state;

    //状态码（200,404,302）
    private String stateCode;

    //错误信息
    private String message;

    //数据
    private Object data;

    public Result(String state, String stateCode, Object data) {
        this.state = state;
        this.stateCode = stateCode;
        this.data = data;
    }

    public Result(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public Result(String state, String stateCode, String message) {
        this.state = state;
        this.stateCode = stateCode;
        this.message = message;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
