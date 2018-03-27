package controller.context;

import controller.command.ConvertPdfCmd;
import model.repo.UnitWorkRepo;

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
        beans.put("ConvertPdfService", new ConvertPdfCmd());
    }

    public Object getBean(String key) {
        return beans.get(key);
    }

}
