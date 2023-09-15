package vn.edu.iuh.fit.service;

import vn.edu.iuh.fit.enties.Log;
import vn.edu.iuh.fit.repository.impl.LogRepositoryImpl;

import java.util.List;

public class LogService {
    private LogRepositoryImpl logRepository;

    public LogService() {
        logRepository= new LogRepositoryImpl();
    }
    public Log findById(Long id){
        return logRepository.findById(Log.class,id);
    }
    public List<Log> findAll(){
        return logRepository.findAll(Log.class);
    }
    public Log save(Log log){
        return logRepository.save(log);
    }
    public Boolean update(Log log){
        return logRepository.update(log);
    }
}
