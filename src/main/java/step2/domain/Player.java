package step2.domain;

import java.util.regex.Pattern;

public class Player {
    public static final Pattern pattern = Pattern.compile("[^a-zA-Z]");
    public static final String ERROR_INVALID_NAME = "이름이 유효하지 않습니다.";

    private final String name;

    public Player(String name) {
        isValidName(name);
        this.name = name;
    }


    public static void isValidName(String name) {
        int length = name.length();
        if ((length > 3 || length <= 0) || pattern.matcher(name).find()) {
            throw new IllegalArgumentException(ERROR_INVALID_NAME);
        }
    }

    public String getName() {
        return name;
    }
}
