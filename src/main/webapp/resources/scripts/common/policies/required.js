export let required = {};

required.login = function(response)
{
    if (response != 'auth error') return false;

    alert('로그인이 필요한 항목입니다');
    location.href = '/account/login';
    return true;
}