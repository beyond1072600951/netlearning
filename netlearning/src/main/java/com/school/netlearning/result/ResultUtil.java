package com.school.netlearning.result;

public class ResultUtil {

    public static Result success(Object data) {
        return new Result("success", "200", data);
    }

    public static Result success() {
        return new Result("success", "200", null);
    }

    public static Result fail(String message) {
        return new Result("fail", message);
    }

    public static Result fail(String state, String message) {
        return new Result("fail", state, message);
    }
}
