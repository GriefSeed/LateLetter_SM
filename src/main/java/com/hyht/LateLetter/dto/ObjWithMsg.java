package com.hyht.LateLetter.dto;


public class ObjWithMsg {
    private Object object;
    private String flag;
    private String msg;


    public ObjWithMsg(Object object, String flag, String msg) {
        this.object = object;
        this.flag = flag;
        this.msg = msg;
    }

    public ObjWithMsg() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
