
/* 토큰을 입력받아 헤더 형식으로 반환해주는 함수 */
const createTokenHeader = (token) => {
    return {
        headers: {
            'Authorization': 'Bearer ' + token
        }
    }
}


/* 토큰 만료시간 계산 */
const calculateRemainingTime = (expirationTime) => {
    const currentTime = new Date().getTime();
    const adjExpirationTime = new Date(expirationTime).getTime();
    const remainingDuration = adjExpirationTime - currentTime;
    return remainingDuration;
};


/* 토큰과 만료시간을 받아서 localStorage에 저장 및 남은시간 반환 */
export const loginTokenHandler = (token, expirationTime) => {
    console.log('AUTH-ACTION');
    console.log('token', token);
    localStorage.setItem('token', token);
    localStorage.setItem('expirationTime', String(expirationTime));

    const remainingTime = calculateRemainingTime(expirationTime);
    return remainingTime;
}


/* 저장된 토큰과 만료시간을 가져오기 */
export const retrieveStoredToken = () => {
    const storedToken = localStorage.getItem('token');
    const storedExpirationDate = localStorage.getItem('expirationTime') || '0';

    const remaingTime = calculateRemainingTime(+ storedExpirationDate);

    if(remaingTime <= 1000) {
        localStorage.removeItem('token');
        localStorage.removeItem('expirationTime');
        return null
    }

    return {
        token: storedToken,
        duration: remaingTime
    }
}



/*export const signupActionHandler = (email, password, nickname) => {
    const URL = '/api/auth/join'
    const signupObject = { userId, password };

    const response = POST(URL, signupObject, {});
    return response;
};

export const loginActionHandler = (email, password) => {
    const URL = '/api/auth/login';
    const loginObject = { userId, password };
    const response = POST(URL, loginObject, {});

    return response;
};*/


/* 로그아웃 시 토큰과 만료시간 삭제 */
export const logoutActionHandler = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('expirationTime');
};


/*export const getUserActionHandler = (token) => {
    const URL = '/member/me';
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const changeNicknameActionHandler = ( nickname, token) => {
    const URL = '/member/nickname';
    const changeNicknameObj = { nickname };
    const response = POST(URL, changeNicknameObj, createTokenHeader(token));

    return response;
}

export const changePasswordActionHandler = (
    exPassword,
    newPassword,
    token
) => {
    const URL = '/member/password';
    const changePasswordObj = { exPassword, newPassword }
    const response = POST(URL, changePasswordObj, createTokenHeader(token));
    return response;
}*/
