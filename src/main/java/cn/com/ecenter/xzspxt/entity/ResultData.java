package cn.com.ecenter.xzspxt.entity;

import java.io.Serializable;

public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code = "";// -1执行成功但返回对象为空;返回 0：成功；1：失败; 1000: 未登录;

    private T data;// 具体的对象

    private int count = 0;

    private String msg = null;// 消息

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultData [code=" + code + ", count=" + count + ", data=" + data + ", message="
                + msg + "]";
    }

    public ResultData() {
        super();
    }

    public ResultData(String code, T data, String msg, int count) {
        super();
        this.code = code;
        this.count = count;
        this.data = data;
        this.msg = msg;
    }

}
