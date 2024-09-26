package com.prospect.myexpensive.data.models;

import java.util.List;

public class LanguagesResponse {
    private String message;
    private List<Language> data;

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Language> getData() {
        return data;
    }

    public void setData(List<Language> data) {
        this.data = data;
    }
}
