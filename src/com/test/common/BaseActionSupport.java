package com.test.common;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Objects;

/**
 * Created by ff on 2017/1/18.
 */
public class BaseActionSupport extends ActionSupport implements
        ServletRequestAware,ServletResponseAware,SessionAware{

    protected HttpServletRequest request=null;
    protected HttpServletResponse response=null;
    protected Map<String,Object> session = null;

    public PrintWriter out = null;

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
            request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        response = httpServletResponse;
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public void setSession(Map<String, Object> map) {
        session = map;
    }

    public void outPrint(String s){
        try {
            out = response.getWriter();
            out.print(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public void outSuccess(){
        outPrint("{\"result\":1}");
    }

    public void outFailed(){
        outPrint("{\"result\":0}");
    }
}
