package com.example.sw221103;

public class ChatMessage {
    private String nickname ;
    private String message ;

    public ChatMessage(String nickname, String message) {
        this.nickname = nickname;
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMessage() {
        return message;
    }
}

