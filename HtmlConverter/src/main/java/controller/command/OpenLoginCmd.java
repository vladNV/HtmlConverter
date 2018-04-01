package controller.command;

import controller.util.StatusPrinter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenLoginCmd implements Action {
    @Override
    public Response call(HttpServletRequest req, HttpServletResponse resp) {
        StatusPrinter.msgln(StatusPrinter.ANSI_BLUE +
                "The mail.jsp is being loaded");
        return () -> req.getRequestDispatcher(Path.LOGIN_PATH)
                .forward(req, resp);
    }
}
