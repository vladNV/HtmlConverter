package controller.command;

import controller.context.ApplicationContext;
import controller.exception.BadRequestException;
import model.repo.UnitWorkRepo;
import model.service.ConvertPdfService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ConvertPdfCmd implements Action {

    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        UnitWorkRepo repo = ActionFactory.extractUnitWorkRepo(login);
        if (login == null) {
            throw new BadRequestException("null parameters or session's values");
        }
        ConvertPdfService service = (ConvertPdfService) ApplicationContext
                .getInstance()
                .getBean("ConvertPdfService");
        service.convertPdf(repo);
        return () -> req.getRequestDispatcher(Path.MAIL_PATH).forward(req, resp);
    }
}
