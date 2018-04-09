package controller.servlet;

import controller.command.ActionFactory;
import controller.exception.AuthorizedException;
import controller.exception.BadRequestException;
import controller.exception.ForbiddenException;
import controller.exception.PageNotFoundException;
import controller.util.StatusPrinter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public final class SingleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleRequest(req, resp);
    }


    private void handleRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            ActionFactory.execute(req.getRequestURI(), req.getMethod())
                    .call(req, resp)
                    .answer();
        } catch (PageNotFoundException e404) {
            resp.sendError(PageNotFoundException.CODE);
        } catch (BadRequestException e400) {
            resp.sendError(BadRequestException.CODE);
        } catch (ForbiddenException e403) {
            resp.sendError(ForbiddenException.CODE);
        } catch (AuthorizedException e401) {
            resp.sendError(401);
        } catch (Throwable internalServerError) {
            internalServerError.printStackTrace();
            resp.sendError(500);
            return;
        }
        StatusPrinter.msgln(StatusPrinter.ANSI_GREEN +
                "The request was processed successful, the response was sent" +
                StatusPrinter.ANSI_GREEN);
    }
}
