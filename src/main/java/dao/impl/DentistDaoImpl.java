package dao.impl;

import dao.ConfigurationJDBC;
import dao.IDao;
import model.Dentist;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoImpl implements IDao<Dentist> {
    private ConfigurationJDBC configurationJDBC;
    final static Logger log = Logger.getLogger(DentistDaoImpl.class);

    public DentistDaoImpl(ConfigurationJDBC configurationJDBC) {
        this.configurationJDBC = configurationJDBC;
    }

    @Override
    public Dentist register(Dentist dentist) {
        log.info("Registering a new dentist.");
        Connection connection = configurationJDBC.connectDatabase();
        Statement statement = null;

        String query = String.format("INSERT INTO dentists(registration_number, name, surname) VALUES('%s','%s','%s')",
                dentist.getRegistrationNumber(), dentist.getName(), dentist.getSurname());
        try{
            statement=connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                dentist.setId(keys.getInt(1));
            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return dentist;
    }

    @Override
    public List<Dentist> listAll() {
        log.info("Listing all registered dentists.");
        Connection connection = configurationJDBC.connectDatabase();
        Statement statement = null;
        List<Dentist> dentists = new ArrayList<>();

        String query = "SELECT * FROM dentists";
        try{
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
                dentists.add(createObjectDentist(resultSet));

            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return dentists;
    }

    @Override
    public Dentist findById(Integer id) {
        log.info("Finding registered dentist.");
        Connection connection = configurationJDBC.connectDatabase();
        Statement statement = null;
        Dentist dentist = null;

        String query = String.format("SELECT * FROM dentists WHERE id = '%s'", id);
        try{
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
                dentist = createObjectDentist(resultSet);

            statement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return dentist;
    }

    @Override
    public void removeById(Integer id) {
        log.info("Removing registered dentist.");
        Connection connection = configurationJDBC.connectDatabase();
        Statement statement = null;

        String query = String.format("DELETE FROM dentists WHERE id='%s'", id);

        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Dentist edit(Dentist dentist) {
        Connection connection = configurationJDBC.connectDatabase();
        Statement statement = null;

        String query = String.format("UPDATE dentists SET registration_number = '%s', name = '%s', surname = '%s', WHERE id = '%s'",
                dentist.getRegistrationNumber(), dentist.getName(), dentist.getSurname());

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return dentist;
    }

    private Dentist createObjectDentist(ResultSet resultSet){
        Dentist dentist = null;
        try {
            Integer id =resultSet.getInt("id");
            Integer registrationNumber= resultSet.getInt("registration_number");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            dentist = new Dentist(id,registrationNumber,name,surname);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dentist;

    }
}
