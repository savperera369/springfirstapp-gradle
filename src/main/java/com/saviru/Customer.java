package com.saviru;

import jakarta.persistence.*;

import java.util.Objects;

// need database to store customers
// Datasource from within spring application - just a factory for connections to the physical db
// configure our model with Spring JPA - maps java classes to database table, interact with db without ever having to write any SQL
// next configure an interface that extends a special class (something like CustomerRepository) that allows us
// to perform CRUD operations against our table which will be mapped by the Customer Class
// docker-compose.yml - specify services that we can run- in this case a postgres instance running on docker
// spring data jpa - maps java classes to database tables, can then use class to directly interact with database w/o SQL
// export PATH=/${PATH}:/Library/PostgreSQL/15/bin
// postgres, PW=Mksolive30$, port:5432
// JPA maps customer entity/class to database, and then we create a repository (CustomerRepository) that
// lets us do CRUD operations on Customer Entity
@Entity
public class Customer {
    // define primary key
    // following three @ values are bare minimum to map class to database table
    // this creates a table called customer with 4 columns (id, name, email, age)
    // we also get a sequence out of it
    @Id
    @SequenceGenerator(
            name="customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public Customer(Integer id, String name, String email, Integer age){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Customer (){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(email, customer.email) && Objects.equals(age, customer.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
