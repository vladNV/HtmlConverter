package controller.command;

import controller.context.ApplicationContext;
import controller.exception.AuthorizedException;
import controller.exception.BadRequestException;
import model.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCmd implements Action {


    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null)
            throw new BadRequestException("bad request");
        LoginService service = (LoginService) ApplicationContext
                .getInstance()
                .getBean("LoginService");
        if (!service.authorisation(login, password)) {
            throw new AuthorizedException();
        }
        session.setAttribute("login", login);
        return () -> resp.sendRedirect(req.getContextPath() + "/" + Path.SENDER_URI);
    }
}
