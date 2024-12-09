package ru.stqa.mantis.model;

public record UserData(String email, String userName, String realName, String password) {

    public UserData() {
        this("","","","");
    }

    public UserData withEmail(String email) {
        return  new UserData(email, this.userName, this.realName, this.password);
    }

    public UserData withUserName(String userName) {
        return  new UserData(this.email, userName, this.realName, this.password);
    }

    public UserData withRealName(String realName) {
        return  new UserData(this.email, this.userName, realName, this.password);
    }

    public UserData withPassword(String password) {
        return  new UserData(this.email, this.userName, this.realName, password);
    }


}
