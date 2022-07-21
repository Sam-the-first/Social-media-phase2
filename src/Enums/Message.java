package Enums;

public enum Message {
    INVALID_CHOICE("invalid choice"),
    SUCCESS("OK"),
    WRONG_CREDENTIALS("Wrong credentials"),
    MISMATCH_PASSWORD("mismatch password"),
    SHORT_PASSWORD("short password"),
    LONG_PASSWORD("long password"),
    NON_ALPHA_NUMERIC_PASSWORD("non alpha numeric password"),
    USER_EXIST("user exists"),
    PRODUCT_EXIST("product exists"),
    INVALID_ROLE("invalid role"),
    USER_DOES_NOT_EXIST("user does not exist"),
    INCORRECT_PASSWORD("incorrect password"),
    WRONG_ANSWER("wrong answer to security question"),
    USERNAME_CHANGED_SUCCESSFULLY("username changed successfully"),
    PASSWORD_CHANGED_SUCCESSFULLY("password changed successfully"),
    USERNAME_EXIST("username exist");


    private String message;

    Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
