package vn.edu.iuh.fit.repository.impl;

import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.repository.CRUDRepository;

import java.util.List;

public class LogRepositoryImpl implements CRUDRepository<Log, Long> {
    @Override
    public Log save(Log log) {
        return null;
    }

    @Override
    public Log findById(Class<Log> entityClass, Long aLong) {
        return null;
    }

    @Override
    public List<Log> findAll(Class<Log> entityClass) {
        return null;
    }

    @Override
    public Boolean update(Log T) {
        return null;
    }

    @Override
    public Boolean deleteById(Class<Log> entityClass, Long aLong) {
        return null;
    }
}
