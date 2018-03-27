package controller.command;

import controller.exception.BadRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCmd implements Action {


    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login == null || password == null)
            throw new BadRequestException("bad request");
        // check login & pass in database here
        // TODO FIX ME
        session.setAttribute("user", login);
        return () -> resp.sendRedirect(Path.SENDER_URI);
    }
}
