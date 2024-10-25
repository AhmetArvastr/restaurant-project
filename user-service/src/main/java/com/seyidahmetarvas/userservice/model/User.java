package com.seyidahmetarvas.userservice.model;

import com.seyidahmetarvas.userservice.model.enums.Gender;
import com.seyidahmetarvas.userservice.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name ="surname", length=50, nullable=false)
    private String surname;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column (name ="email",length=100, nullable=false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column (name="gender", length=30)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column (name="user_status", length=30, nullable=false)
    private Status userStatus = Status.ACTIVE;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    public User() {
    }

    public User(String name, String surname, LocalDate birthDate, String email, Gender gender, Status userStatus, double latitude, double longitude) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
        this.userStatus = userStatus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(latitude, user.latitude) == 0 && Double.compare(longitude, user.longitude) == 0 && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(birthDate, user.birthDate) && Objects.equals(email, user.email) && gender == user.gender && userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, email, gender, userStatus, latitude, longitude);
    }
}
