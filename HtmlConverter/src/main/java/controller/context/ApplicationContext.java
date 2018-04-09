package controller.context;

import model.repo.UnitWorkRepo;
import model.service.ConvertPdfService;
import model.service.DatabaseService;
import model.service.LoginService;

import java.util.HashMap;

public class ApplicationContext {
    private static final ApplicationContext app = new ApplicationContext();
    private ApplicationContext() { }
    private static HashMap<String, Object> beans;

    public static ApplicationContext getInstance() {
        return app;
    }

    static {
        beans = new HashMap<>();
        beans.put("repos", new HashMap<String, UnitWorkRepo>());
        beans.put("ConvertPdfService", new ConvertPdfService());
        beans.put("LoginService", new LoginService());
        beans.put("DatabaseService", new DatabaseService());
    }

    public Object getBean(String key) {
        return beans.get(key);
    }

}
