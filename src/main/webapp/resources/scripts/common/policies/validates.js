/**
 * 아이디 유효성 검사
 * 기능 요구사항 명세서 - 아이디 정책 참고
 * - 영문 대소문자 / 숫자, 4자 ~ 16
 */
let pattern_id = /^[a-zA-Z]{4,16}$/;
function validate_id(id) {
    if (!id) return 0;
    if (!pattern_id.test(id)) return -1;

    return 1;
}

/**
 * 비밀번호 유효성 검사
 * 기능 요구사항 명세서 - 비밀번호 정책 참고
 * - 영문 대소문자 / 숫자 / 특수문자 중 2가지 이상 조합, 8자 ~ 16자
 */
let pattern_pw = /^[a-zA-Z0-9!@#$%^*+=-]{8,16}$/;
function validate_pw(password) {
    if (!password) return 0;
    if (!pattern_pw.test(password)) return -1;

    return 1;
}
