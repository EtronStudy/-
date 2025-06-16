package learn.subject.shop.persentation.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final String messageCode;
    private final String message;
    private final T data;

    public static <T> ApiResponse<T> success(String messageCode, String message) {
        return new ApiResponse<>(true, messageCode, message, null);
    }

    public static <T> ApiResponse<T> success(String messageCode, String message, T data) {
        return new ApiResponse<>(true, messageCode, message, data);
    }

    public static <T> ApiResponse<T> failure(String messageCode, String message, T data) {
        return new ApiResponse<>(false, messageCode, message, data);
    }

}
