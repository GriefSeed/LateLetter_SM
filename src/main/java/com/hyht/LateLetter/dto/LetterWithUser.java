package com.hyht.LateLetter.dto;


import com.hyht.LateLetter.entity.Letter;
import com.hyht.LateLetter.entity.Users;


public class LetterWithUser {
    private Users users;
    private Letter letter;

    public LetterWithUser(Users users, Letter letter) {
        this.users = users;
        this.letter = letter;
    }

    public LetterWithUser() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Letter getLetter() {
        return letter;
    }

    public void setLetter(Letter letter) {
        this.letter = letter;
    }
}
