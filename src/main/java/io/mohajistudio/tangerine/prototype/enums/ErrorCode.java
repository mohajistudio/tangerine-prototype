package io.mohajistudio.tangerine.prototype.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "유효하지 않은 입력 값입니다"),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 메서드입니다"),
    ENTITY_NOT_FOUND(400, "C003", "엔티티를 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR(500, "C004", "서버에서 에러가 발생했습니다"),
    INVALID_TYPE_VALUE(400, "C005", "유효하지 않은 형식의 값입니다"),
    HANDLE_ACCESS_DENIED(403, "C006", "접근 권한이 없습니다"),
    NOT_FOUND(404, "C007", "요청한 주소의 API를 찾을 수 없습니다"),

    // Member
    MEMBER_NOT_FOUND(404, "M001", "존재하지 않는 회원입니다"),
    MEMBER_PROFILE_DUPLICATION(400, "M002", "이미 존재하는 회원입니다");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
