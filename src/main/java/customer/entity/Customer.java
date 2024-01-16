package customer.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer")

public class Customer {
    public Customer(){

    }
    public Customer(int customer_id, String fname, String lname, String email, String password, String contact, String longitude, String latitude) {
        this.customer_id = customer_id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    private String fname;

    private String lname;

    private String email;

    private String password;

    private String contact;

    private String longitude;

    private String latitude;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contact='" + contact + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}