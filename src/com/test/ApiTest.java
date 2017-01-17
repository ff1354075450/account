package com.test;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ff on 2017/1/13.
 */
public class ApiTest extends ActionSupport implements
        ServletResponseAware, ServletRequestAware, SessionAware  {
    private  HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, Object> session;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    /**
     * 这仅仅是一个测试
     */
    public void mytest(){
        System.out.println("这是一个test。。。");
    }

    /**
     * 一个简单的测试接口
     * @return
     * @throws IOException mm
     */
    public String mytest2() throws IOException {
        //设置编码，这是一个最低配置
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write("你好这是一个测试接口");
        pw.close();
        return SUCCESS;
    }


}
