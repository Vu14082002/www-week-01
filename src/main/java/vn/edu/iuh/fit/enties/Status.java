package vn.edu.iuh.fit.enties;


import java.util.stream.Stream;

public enum Status {
        ACTIVE(1),
        DEACTIVE(0),
        DELETE(-1);
    private  Integer code;

    Status(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
    public static Status from(int code) {
        return Stream.of(Status.values())
                .filter(p -> p.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
