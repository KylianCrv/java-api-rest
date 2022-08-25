package fr.m2i.javaapirest.tpuser.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id")
    private int id;

    @Column(
            name = "lastname"
    )
    private String lastname;

    @Column(
            name = "firstname"
    )
    private String firstname;

    @Column(
            name = "role"
    )
    private String role;

    @Column(
            name = "email"
    )
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(
            name = "password"
    )
    private String password;

    public User() {

    }

    public User(String lastname, String firstname, String role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void copy(User userData) {

        if (userData.getFirstname() != null) {
            this.setFirstname(userData.getFirstname());
        }
        if (userData.getLastname() != null) {
            this.setLastname(userData.getLastname());
        }
        if (userData.getRole() != null) {
            this.setRole(userData.getRole());
        }
        if (userData.getEmail() != null) {
            this.setEmail(userData.getEmail());
        }
        if (userData.getPassword() != null) {
            this.setPassword(userData.getPassword());
        }

    }
}
