package app.todo.dto.base;

import java.io.Serializable;

public class ObjectDto<T> extends BaseDto implements Serializable {

    private T instance;

    public ObjectDto(){}

    public ObjectDto(T instance) {
        this.instance = instance;
    }

    public T getData() {
        return instance;
    }
}
