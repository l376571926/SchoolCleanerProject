package group.tonight.schoolcleaner.model;

import java.util.List;

public class ListResponseBean<T> extends BaseResponseBean {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
