package vn.edu.iuh.fit.repository;

import java.util.List;

public interface CRUDRepository<T,ID>{
    T save(T t);
    T findById(Class<T> entityClass,ID id);

    List<T> findAll(Class<T> entityClass);
    Boolean update(T T);
    Boolean deleteById(Class<T> entityClass, ID id);

}
