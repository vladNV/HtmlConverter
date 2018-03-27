package controller.command;

import controller.exception.BadRequestException;
import controller.util.StatusPrinter;
import model.repo.UnitWorkRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OpenConvertCmd implements Action {

    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        StatusPrinter.msgln(StatusPrinter.ANSI_BLUE +
                "The convert.jsp is being loaded");
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        UnitWorkRepo repo = ActionFactory.extractUnitWorkRepo(login);
        if (login == null || repo == null)
            throw new BadRequestException("login cannot be null");
        return () -> req.getRequestDispatcher(Path.CONVERT_PATH)
            .forward(req, resp);
    }
}
