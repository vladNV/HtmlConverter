package controller.command;

import controller.context.ApplicationContext;
import controller.exception.BadRequestException;
import model.domain.File;
import model.domain.Mail;
import model.mail.Sender;
import model.repo.UnitWorkRepo;
import model.service.DatabaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class SendMailCmd implements Action {
    private static ResourceBundle bundle;
    static {
        try {
            bundle = ResourceBundle.getBundle("config");
        } catch (Throwable e) {
            throw new RuntimeException("exception at config loading", e);
        }
    }

    @Override
    public Response call(HttpServletRequest req,
                         HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        String subject = req.getParameter("subject");
        String text = req.getParameter("text");
        String message = from + "\n" + text;
        UnitWorkRepo repo = ActionFactory.extractUnitWorkRepo(login);
        if (to == null || from == null || login == null
                || subject == null || text == null) {
            throw new BadRequestException("null data");
        }
        String pdfPath = (String)repo.pull("pdf");
        Sender sender = new Sender(bundle.getString("mail-login"),
                bundle.getString("mail-password"),
                pdfPath);
        sender.send(subject, message, to);
        DatabaseService serv = (DatabaseService) ApplicationContext
                .getInstance()
                .getBean("DatabaseService");

        File file = new File()
                .setCreated(LocalDateTime.now())
                .setPath(pdfPath)
                .setSizeKb(10L)
                .setType("PDF");

        Mail mail = new Mail()
                .setUserLogin(login)
                .setText(text)
                .setFrom(from)
                .setTo(to)
                .setSubject(subject);

        serv.save(mail);
        serv.save(file);
        return () -> req.getRequestDispatcher(Path.CONGRATULATION_PATH).forward(req, resp);
    }
}
