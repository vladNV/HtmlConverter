package controller.command;

import controller.util.StatusPrinter;
import model.repo.UnitWorkRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenSenderCmd implements Action {

    @Override
    public Response call(HttpServletRequest req, HttpServletResponse resp) {
        StatusPrinter.msgln(StatusPrinter.ANSI_BLUE +
                "The sender.jsp is being loaded");
        return () -> req.getRequestDispatcher(Path.SENDER_PATH)
                .forward(req, resp);
    }
}
