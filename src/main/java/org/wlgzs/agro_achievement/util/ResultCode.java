package org.wlgzs.agro_achievement.util;

public enum ResultCode {
    SUCCESS(0, "请求成功"),
    FAIL(-1,"操作失败"),
    ADMIN(1,"管理员"),
    EXPERT(2,"专家");
    private int code;
    private String msg;

    ResultCode(int code, String msg) {
     this.code = code;
     this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
