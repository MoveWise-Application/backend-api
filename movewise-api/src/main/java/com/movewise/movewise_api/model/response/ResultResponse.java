package com.movewise.movewise_api.model.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultResponse<T> {

    private final boolean isSuccess;
    private final List<String> messages;
    private final T data;
    private final int statusCode;

    // Constructor for a single message
    public ResultResponse(boolean isSuccess, String message, T data, int statusCode) {
        this.isSuccess = isSuccess;
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.data = data;
        this.statusCode = statusCode;
    }

    // Constructor for multiple messages
    public ResultResponse(boolean isSuccess, List<String> messages, T data, int statusCode) {
        this.isSuccess = isSuccess;
        this.messages = messages == null ? Collections.emptyList() : new ArrayList<>(messages);
        this.data = data;
        this.statusCode = statusCode;
    }

    // Getters
    public boolean isSuccess() {
        return isSuccess;
    }

    public List<String> getMessages() {
        return messages;
    }

    public T getData() {
        return data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    // Static factory method for success
    public static <T> ResultResponse<T> success(T data, String message, int statusCode) {
        return new ResultResponse<>(true, message, data, statusCode);
    }

    // Static factory method for success (default message and status code)
    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(true, "", data, 200);
    }

    // Static factory method for failure (single error message)
    public static <T> ResultResponse<T> fail(String errorMessage, int statusCode) {
        return new ResultResponse<>(false, errorMessage, null, statusCode);
    }

    // Static factory method for failure (multiple error messages)
    public static <T> ResultResponse<T> fail(List<String> errorMessages, int statusCode) {
        return new ResultResponse<>(false, errorMessages, null, statusCode);
    }
}
