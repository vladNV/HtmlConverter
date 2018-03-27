package model.repo;

import java.util.HashMap;

public class UnitWorkRepo implements IUnitWorkRepo {
    private HashMap<String, Object> storage
            = new HashMap<>();

    public UnitWorkRepo() { }

    @Override
    public HashMap<String, Object> pull() {
        return storage;
    }

    @Override
    public Object pull(String key) {
        return storage.get(key);
    }

    @Override
    public void push(String key, Object o) {
        storage.putIfAbsent(key, o);
    }

    @Override
    public IUnitWorkRepo clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new UnitWorkRepo(this);
    }

    public UnitWorkRepo(UnitWorkRepo unitWorkRepo) {
        this.storage = unitWorkRepo.storage;
    }
}
