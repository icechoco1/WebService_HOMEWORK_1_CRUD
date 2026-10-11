package kr.ac.jbnu.kth.crud.dto;

public class UserApiResponse<T> {
    private String status;
    private T data;

    public UserApiResponse(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() { return status; }
    public T getData() { return data; }
}