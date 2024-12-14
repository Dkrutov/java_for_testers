package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "addressbook")
public class ContactRecord {

    public ContactRecord() {
    }

    public ContactRecord(int id,
                         String firstname,
                         String middlename,
                         String lastname,
                         String address,
                         String mobile,
                         String email
    ) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }

    @Id
    public int id;
    public String firstname;
    public String middlename;
    public String lastname;
    public String address;
    public String mobile;
    public String email;
    public String nickname = "nickname";
    public String company = "company";
    public String title = "title";
    public String home = "home";
    public String work = "work";
    public String fax = "fax";
    public String email2 = "email2";
    public String email3 = "email3";
    public String homepage = "homepage";
    public String phone2;



}
