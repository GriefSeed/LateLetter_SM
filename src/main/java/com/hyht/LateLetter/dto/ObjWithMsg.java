package com.hyht.LateLetter.dto;


public class ObjWithMsg {
    private Object core;
    private String flag;
    private String msg;


    public ObjWithMsg(Object core, String flag, String msg) {
        this.core = core;
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

    public Object getCore() {
        return core;
    }

    public void setCore(Object core) {
        this.core = core;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
