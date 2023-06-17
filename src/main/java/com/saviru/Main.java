package com.saviru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// SpringBootApplication - wrapper for three other annotations
//
@SpringBootApplication
// means that any method within this class with get, post notations is exposed as REST endpoints that clients can call
// indicates class is a controller, all methods in the marked class will return JSON response
// converts return objects into JSON
// post request - let client store data through api
// dao - customer repository- is responsible for saving data to database
// N-tier architecture
// clients -> make HTTP requests -> API Layer (controllers) -> make request-> Service Classes(Business Layer)-> make requests -> DAO (repository classes) -> make requests -> Database
@RestController
public class Main {
    // inject customer repository
    private final CustomerRepository customerRepository;

    public Main (CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }

    // exposes this method as a REST endpoint for clients to use as get requests
    // this method is responsible for serving the HTTP request to this path/endpoint
    @GetMapping("/greet")
    public GreetResponse greet() {
        // Jackson converts java objects to JSON objects
        return new GreetResponse("Hello", List.of("Javascript", "Java", "C#"), new Person("Yazan", 20, 30000));
    }

    @GetMapping("/api/v1/customers")
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    record NewCustomerRequest(String name, String email, Integer age){}

    // request that comes from the client, we need to capture that to the request body
    // request body will be JSON object that will be mapped into a NewCustomerRequest object
    @PostMapping("/api/v1/customers")
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setAge(request.age());
        // save to database
        customerRepository.save(customer);
    }

    // delete existing resource from backend of application
    // path variable grabs id in url and maps to id argument
    @DeleteMapping("/api/v1/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    // record are classes that let us achieve immutability
    record Person(String name, int age, double savings){}
    record GreetResponse(
            String greet,
            List<String> favProgrammingLanguages,
            Person person
    ){}
}

// model - we can build api to do CRUD operations
