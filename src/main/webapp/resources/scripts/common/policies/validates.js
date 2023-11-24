/**
 * 아이디 유효성 검사
 * 기능 요구사항 명세서 - 아이디 정책 참고
 * - 영문 대소문자 / 숫자, 4자 ~ 16
 */
function validate_id(data) {
    let pattern = /^[a-zA-Z]{4,16}$/;
    if (!data) return 0;
    if (!pattern.test(data)) return -1;

    return 1;
}

/**
 * 비밀번호 유효성 검사
 * 기능 요구사항 명세서 - 비밀번호 정책 참고
 * - 영문 대소문자 / 숫자 / 특수문자 중 2가지 이상 조합, 8자 ~ 16자
 */
function validate_pw(data) {
    let pattern = /^[a-zA-Z0-9!@#$%^*+=-]{8,16}$/;
    if (!data) return 0;
    if (!pattern.test(data)) return -1;

    return 1;
}

/**
 * 이름 유효성 검사
 * 기능 요구사항 명세서 - 이름 정책 참고
 * - 20자 이하의 한글
 */
function validate_name(data) {
    let pattern = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]{1,20}$/;
    if (!data) return 0;
    if (!pattern.test(data)) return -1;

    return 1;
}

/**
 * 휴대전화 유효성 검사
 * 기능 요구사항 명세서 - 휴대전화 정책 참고
 * - 7/8자 숫자
 */
function validate_phone(data) {
    let pattern = /^[0-9]{7,8}$/;
    if (!data) return 0;
    if (!pattern.test(data)) return -1;

    return 1;
}

/**
 * 이메일 유효성 검사
 * 기능 요구사항 명세서 - 이메일 정책 참고
 * - 최대 30자
 */
function validate_email(data) {
    let pattern = /^[0-9a-zA-Z@.]{7,30}$/;
    if (!data) return 0;
    if (!pattern.test(data)) return -1;

    return 1;
}
