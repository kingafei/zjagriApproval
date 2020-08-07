package cn.com.ecenter.xzspxt.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sys_Constant {

    public static final String USER = "user";

    public static String Upload_Method;

    @Value("${upload.Upload_Method}")
    public void setUpload_method(String Upload_Method) {
        Sys_Constant.Upload_Method = Upload_Method;
    }
    public static String getUpload_Method() {
        return Upload_Method;
    }

    /**
     * 判断接口传递时间 单位秒
     */
    public static final int TIMESTAMP_COMPARE_TIME = 10000;

    /**
     * 判断nonce标识过期时间 单位秒
     */
    public static final int NONCE_COMPARE_TIME = 900;

    /**
     * 设置token过期时间 单位秒
     */
    public static final int TOKEN_EXPIRED_TIME = 1296000;

    public static final String SUCCESS_CODE = "0000";
    public static final String SUCCESS_MSG = "请求成功!";

    public static final String FAIL_CODE = "1001";
    public static final String FAIL_MSG = "请求失败!";

    public static final String TOKEN_FAIL_CODE = "1002";
    public static final String TOKEN_FAIL_MSG = "请求token异常!";

    public static final String TIME_FAIL_CODE = "1003";
    public static final String TIME_FAIL_MSG = "请求时间异常!";

    public static final String PARAM_FAIL_CODE = "1004";
    public static final String PARAM_FAIL_MSG = "请求参数异常!";

    public static final String NONCE_FAIL_CODE = "1005";
    public static final String NONCE_FAIL_MSG = "nonce参数异常!";

    public static final String TOKEN_EXPIRED_FAIL_CODE = "1006";
    public static final String TOKEN_EXPIRED_FAIL_MSG = "token凭证已过期，请重新登录!";

    public static final String NO_LOGIN_CODE = "9999";
    public static final String NO_LOGIN_MSG = "您还未登录!";

    public static final String OPERATION_FAIL_CODE = "1000";
    public static final String OPERATION_FAIL_MSG = "非法操作!";

}
