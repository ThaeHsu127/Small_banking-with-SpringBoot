package com.mytel.smallBanking.response;

public class MainResponse {
    private boolean Status;
    private Object result;
    private String message;

    public MainResponse() {
    }

    public MainResponse(boolean status, Object result, String message) {
        Status = status;
        this.result = result;
        this.message = message;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
