package group.tonight.schoolcleaner.model;

public class DataResponseBean<T> extends BaseResponseBean {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
