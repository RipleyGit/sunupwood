package cn.chainof.sunup.common;

import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.constant.*;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import java.io.Serializable;

public class UserContext implements Serializable {

    /**
     * 获取当前线程绑定的用户登录对象
     *
     * @return
     */
    public static ProjectUser getUserSession() {
        ProjectUser user = (ProjectUser) RequestContextHolder.getRequestAttributes().getAttribute(Const.LOGINED_KEY, RequestAttributes.SCOPE_REQUEST);
        if (user == null){
            user = new ProjectUser();
            user.setName("admin");
            user.setPassword("admin");
        }
        return user;
    }

    /**
     * 将用户登录对象绑定到当前线程
     *
     * @param loginEntity
     */
    public static void setUserSession(ProjectUser loginEntity) {
        RequestContextHolder.getRequestAttributes().setAttribute(Const.LOGINED_KEY, loginEntity, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 将用户登录对象从当前线程销毁
     */
    public static void removeUserSession() {
        RequestContextHolder.getRequestAttributes().removeAttribute(Const.LOGINED_KEY, RequestAttributes.SCOPE_REQUEST);
    }

}
