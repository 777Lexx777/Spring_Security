package ru.springBoot.lex.springBoot.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User  { //implements UserDetails

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 1, message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;

   @NotEmpty(message = "Name should not be empty")
   @Size(min = 2, max = 45, message = "Name should be between 2 and 30 characters")
   @Column(name = "name")
    private String name; // уникальное значение

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 30 characters")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Name should not be empty")
    @Enumerated (value = EnumType.STRING)
    @Column(name = "roles")
    private Roles roles;

    @Enumerated (value = EnumType.STRING)
    @NotNull(message = "Name should not be empty")
    @Column(name = "status")
    private Status status;

    public User() { }

    public User(Long id,String firstName,String lastName, int age,  String name, String password, Roles roles, Status status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
