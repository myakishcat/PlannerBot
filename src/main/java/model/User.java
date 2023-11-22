package model;

import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class User {


    private final Long chatId;
    private String firstName;
    private String lastName;
    private String userName;
    private final Timestamp registeredAt;

    public User(Long chatId, Timestamp registeredAt) {
        this.chatId = chatId;
        this.registeredAt = registeredAt;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



}
