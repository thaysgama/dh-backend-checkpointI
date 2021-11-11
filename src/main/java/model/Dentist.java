package model;

public class Dentist {

    private Integer id;
    private Integer registrationNumber;
    private String name;
    private String surname;

    public Dentist(Integer id, Integer registrationNumber, String name, String surname) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.surname = surname;
    }

    public Dentist(Integer registrationNumber, String name, String surname) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.surname = surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Dentist [" +
                "Id: " + id +
                ", Registration Number: " + registrationNumber +
                ", Name: " + name +
                ", Surname: " + surname +
                ']';
    }
}
