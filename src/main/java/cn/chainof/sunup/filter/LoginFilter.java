package cn.chainof.sunup.filter;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.exception.LoginException;
import cn.chainof.sunup.model.ProjectUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

@Component
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        if (request.getServletPath() != null) {
            ProjectUser user = (ProjectUser) session.getAttribute(Const.LOGINED_KEY);
            if (user != null) {
                UserContext.setUserSession(user);
            } else {
//                throw new LoginException("用户未登陆！");
            }
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
