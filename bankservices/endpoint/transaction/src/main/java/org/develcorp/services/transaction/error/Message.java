package org.develcorp.services.transaction.error;

public class Message {

    private final String code;

    private final String message;

    public Message(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
