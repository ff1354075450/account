package com.test.AppApi;

import com.test.common.BaseActionSupport;
import com.test.db.person.UserDao;

/**
 * Created by ff on 2017/1/18.
 */
public class UserApi extends BaseActionSupport {

    /**
     * 用于登录.
     */
    public void login() {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (UserDao.checkPassword(name, password)) {
            outSuccess();
        } else {
            outFailed();
        }
    }

    /**
     * 用于注册.
     */
    public void register() {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if (UserDao.addUser(name, password)) {
            outSuccess();
        } else {
            outFailed();
        }
    }
}
