package model;

public record ContactData(
        String firstName,
        String middleName,
        String lastName,
        String address,
        String email,
        String phone
        ) {

    public ContactData(){
        this("","","","","","");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.middleName, this.lastName, this.address, this.email, this.phone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.firstName, this.middleName, lastName, this.address, this.email, this.phone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstName, this.middleName, this.lastName, address, this.email, this.phone);
    }

    public ContactData contactValidation(
            String firstName,
            String middleName,
            String lastName,
            String address,
            String email,
            String phone
    ) {
        return new ContactData(firstName, middleName, lastName, address, email, phone);
    }
}
