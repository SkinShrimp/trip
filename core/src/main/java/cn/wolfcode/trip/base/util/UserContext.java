package cn.wolfcode.trip.base.util;

import cn.wolfcode.trip.base.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class UserContext {
    public static final String USER_IN_SESSION = "USER_IN_SESSION";

    public static HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).
                getRequest().getSession();
    }

    public static void setCurrentUser(User currentUser) {
        getSession().setAttribute(USER_IN_SESSION, currentUser);
    }

    public static User getCurrentUser() {
        return (User) getSession().getAttribute(USER_IN_SESSION);
    }
}