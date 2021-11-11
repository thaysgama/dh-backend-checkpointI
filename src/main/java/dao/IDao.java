package dao;

import java.util.List;

public interface IDao<T> {
    public T register(T t);
    public List<T> listAll();
    public T findById(Integer id);
    public void removeById(Integer id);
    public T edit(T t);
}
