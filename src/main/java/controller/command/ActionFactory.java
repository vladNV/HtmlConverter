package controller.command;

import controller.context.ApplicationContext;
import controller.exception.BadRequestException;
import controller.exception.PageNotFoundException;
import model.repo.UnitWorkRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ActionFactory {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final ResourceBundle resource;
    private static Map<String, Action> actions = new HashMap<>();

    static {
        resource = ResourceBundle.getBundle("command");
        actions.put("controller.command.OpenSenderCmd.java", new OpenSenderCmd());
        actions.put("controller.command.ConvertPdfCmd.java", new ConvertPdfCmd());
        actions.put("controller.command.LoginCmd.java", new LoginCmd());
        actions.put("controller.command.OpenConvertCmd.java", new OpenConvertCmd());
        actions.put("controller.command.OpenMailCmd.java", new OpenMailCmd());
        actions.put("controller.command.SendMailCmd.java", new SendMailCmd());
        actions.put("controller.command.OpenLoginCmd.java", new OpenLoginCmd());
    }

    public static Action execute(String request, String type) {
        switch (type) {
            case GET: {
                String[] structure = request
                        .trim()
                        .replaceFirst("/", "")
                        .split("\\?")[0].split("/");
                String kindOf = structure[0];
                String commandKey = structure[1];
                try {
                    String classname = resource.getString(commandKey);
                    return actions.get(classname);
                } catch (Exception e) {
                    throw new PageNotFoundException
                            ("resource bundle property doesn't exist", commandKey);
                }
            }
            case POST: {
                String[] structure = request
                        .trim()
                        .replaceFirst("/","")
                        .split("/");
                String kindOf = structure[0];
                String commandKey = structure[1];
                try {
                    String classname = resource.getString(commandKey);
                    return actions.get(classname);
                } catch (Exception e) {
                    throw new PageNotFoundException
                            ("resource bundle property doesn't exist", commandKey);
                }
            }
        }
        throw new BadRequestException(type);
    }

    @SuppressWarnings("unchecked")
    static UnitWorkRepo extractUnitWorkRepo(String login) {
        return ((HashMap<String, UnitWorkRepo>)
                ApplicationContext.getInstance().getBean("repos"))
                .get(login);
    }

}
