package app.todo.dto.base;

public class BaseDto {

    private String message;

    private Boolean value;

    public BaseDto(){}

    public BaseDto(String message, Boolean value) {
        this.setReturnWithMessageResponseStats(message, value);
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public Boolean getValue() {
        return value;
    }

    private void setValue(Boolean value) {
        this.value = value;
    }

    public void setReturnWithMessageResponseStats(String message, Boolean value) {
        setMessage(message);
        setValue(value);
    }

}
