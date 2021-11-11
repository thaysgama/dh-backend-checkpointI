package service;

import dao.IDao;
import model.Dentist;

import java.util.List;

public class DentistService {
    private IDao<Dentist> dentistDao;

    public DentistService(IDao<Dentist> destistDao) {
        this.dentistDao = destistDao;
    }

    public Dentist register(Dentist dentist){
        return dentistDao.register(dentist);
    }

    public List<Dentist> listAll(){
        return dentistDao.listAll();
    }

    public Dentist findById(Integer id){
        return dentistDao.findById(id);
    }

    public Dentist edit(Dentist dentist){
        return dentistDao.edit(dentist);
    }

    public void removeById(Integer id){
        dentistDao.removeById(id);
    }

}
