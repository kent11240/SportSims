package uuu.sportsims.model;

import java.util.List;
import uuu.sportsims.domain.SportSimsException;

public interface DAOInterface<K, T> {

    void insert(T data) throws SportSimsException;

    void update(T data) throws SportSimsException;

    void delete(T data) throws SportSimsException;

    T get(K key) throws SportSimsException;

    List<T> getAll() throws SportSimsException;
}
