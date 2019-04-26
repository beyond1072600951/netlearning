package com.school.netlearning.Util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CurrentUserUtil {

    //获取当前登录人的ID
    public static Integer getUserId(HttpServletRequest request) {
        Object current_user_id = request.getAttribute("current_user_id");
        return Integer.parseInt(request.getAttribute("current_user_id").toString());
    }

    //获取当前登录人的角色
    public static List getUserRole(HttpServletRequest request) {
        return (List) request.getAttribute("current_user_role");
    }

    //判断当前登录人是否是传过来的角色
    public static Boolean isRole(HttpServletRequest request, String role) {
        List<String> userRoleList = getUserRole(request);
        for (String userRole : userRoleList) {
            if (role.equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}
