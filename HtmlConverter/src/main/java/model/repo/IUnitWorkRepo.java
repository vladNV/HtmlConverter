package model.repo;

import java.util.HashMap;

public interface IUnitWorkRepo {

    HashMap<String, Object> pull();

    Object pull(String key);

    void push(String key, Object o);

    IUnitWorkRepo clone();

}
