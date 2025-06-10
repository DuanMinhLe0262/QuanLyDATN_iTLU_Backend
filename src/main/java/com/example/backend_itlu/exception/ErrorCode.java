package com.example.backend_itlu.exception;

public enum ErrorCode {

    PASSWORD_INVALID(1003, "Password must be at least 6 characters"),
    USER_NOT_FOUND(1004, "User not found"),

    ENTITY_NOT_FOUND(1005, "Entity not found"),
    ENTITY_ALREADY_EXISTS(1006, "Entity already exists"),
    INVALID_INPUT(1007, "Invalid input data"),
    UNAUTHORIZED_ACCESS(1008, "Unauthorized access"),
    FORBIDDEN_ACTION(1009, "You are not allowed to perform this action"),

    FILE_UPLOAD_FAILED(1010, "File upload failed"),
    FILE_DOWNLOAD_FAILED(1011, "File download failed"),
    FILE_NOT_FOUND(1012, "File not found"),
    FILE_SIZE_EXCEEDED(1013, "File size exceeds the allowed limit"),
    FILE_TYPE_NOT_SUPPORTED(1014, "Unsupported file type"),

    INTERNAL_SERVER_ERROR(1015, "Internal server error");
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
