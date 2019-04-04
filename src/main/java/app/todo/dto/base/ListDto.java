package app.todo.dto.base;

import java.io.Serializable;
import java.util.List;

public class ListDto<T> extends BaseDto implements Serializable {

    private List<T> list;

    private int count;

    public ListDto(){}

    public ListDto(List<T> list) {
        this.list = list;
        this.count = list.size();
    }

    public List<T> getData() {
        return list;
    }

    public int getCount() {
        return count;
    }
}
