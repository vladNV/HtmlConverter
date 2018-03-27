package controller.command;

import controller.context.ApplicationContext;
import controller.exception.BadRequestException;
import model.repo.UnitWorkRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConvertPdfCmd implements Action {

    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        String sheet = req.getParameter("sheet");
        String column = req.getParameter("column");
        String sheetWithTable = req.getParameter("sheetWithTable");
        String columnWithTable = req.getParameter("columnWithTable");
        UnitWorkRepo repo = ActionFactory.extractUnitWorkRepo(login);
        if (sheet == null || column == null
                || login == null || repo == null
                    || sheetWithTable == null || columnWithTable == null) {
            throw new BadRequestException("null parameters or session's values");
        }
        String path = (String) repo.pull("path");
        return null;
    }
}
