package io.mohajistudio.tangerine.prototype.enums;

public enum Provider {
    KAKAO("kakao"),
    NAVER("naver"),
    GOOGLE("google"),
    APPLE("apple");

    private final String value;

    Provider(String value) {
        this.value = value;
    }

    public static Provider fromValue(String value) {
        for (Provider provider : values()) {
            if (provider.value.equalsIgnoreCase(value)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 제공자입니다: " + value);
    }
}
