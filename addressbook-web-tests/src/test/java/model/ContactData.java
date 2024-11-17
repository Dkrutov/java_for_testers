package model;

public record ContactData(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String address,
        String email,
        String phone,
        String photo
        ) {

    public ContactData(){
        this("", "","","","","","","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.address, this.email, this.phone, this.photo);
    }


    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.address, this.email, this.phone, this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, address, this.email, this.phone, this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName,  this.address,email, this.phone, this.photo);
    }

    public ContactData withPhone(String phone) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName,  this.address,this.email, phone, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName,  this.address,this.email, this.phone, photo);
    }

    public ContactData contactValidation(
            String firstName,
            String middleName,
            String lastName,
            String address,
            String email,
            String phone,
            String photo
    ) {
        return new ContactData(this.id, firstName, middleName, lastName, address, email, phone, photo);
    }
}
