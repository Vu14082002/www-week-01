package vn.edu.iuh.fit.enties;

public enum IsGrant {
    GRANTED("1"),
    NOTGRANTED("0"),
    DENIED("-1");
    private final String code;

    IsGrant(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
    public static IsGrant fromCode(String code) {
        if (code.equalsIgnoreCase("1")) {
            return GRANTED;
        }
        if (code.equalsIgnoreCase("0")) {
            return NOTGRANTED;
        }
        if (code.equalsIgnoreCase("-1")) {
            return DENIED;
        }
        throw new UnsupportedOperationException(
                "The code " + code + " is not supported!"
        );
    }

}

