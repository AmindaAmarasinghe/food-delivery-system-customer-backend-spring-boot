package dto;
import lombok.Data;

@Data
public class EditRequest {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    private String fname;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    private String lname;

    public String getContact() {
        return contact;
    }

    public void setCity(String contact) {
        this.contact = contact;
    }

    private String contact;
}
