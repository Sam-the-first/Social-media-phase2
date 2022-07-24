package Enums;

public enum WarningMessage {
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
    Invalid_BIRTH_DATE("invalid birth date"),
    WRONG_ANSWER("wrong answer to security question"),
    USERNAME_CHANGED_SUCCESSFULLY("username changed successfully"),
    PASSWORD_CHANGED_SUCCESSFULLY("password changed successfully"),
    USERNAME_EXIST("username exist"),
    LONG_BIO("maximum bio size is 15"),
    LONG_TEXT("maximum texts size is 1200"),
    POST_DOES_NOT_EXIST("post does not exist"),
    FOLLOWED_SUCCESSFULLY("user followed successfully"),
    UNFOLLOWED_SUCCESSFULLY("user unfollowed successfully"),
    NO_POST_YET("not post yet"),
    NO_COMMENT_FOR_THIS("no comment for this"),
    GROUP_CREATED_SUCCESSFULLY("group created successfully"),
    EDITED_SUCCESSFULLY("edited successfully"),
    YOU_CANT_HAVE_CHAT_WITH_YOURSELF("you cant have chat with yourself"),
    MESSAGE_IS_FORWARDED("message is forwarded"),
    YOU_HAVE_NOT_LIKE_IT_YET("you have not like it yet"),
    YOU_HAVE_NOT_COMMENT_FOR_IT_YET("you have not comment for it yet"),
    YOU_ARE_BLOCKED("you are blocked");



    private String message;

    WarningMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
