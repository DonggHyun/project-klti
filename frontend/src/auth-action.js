
/* 토큰을 입력받아 헤더 형식으로 반환해주는 함수 */
export const createTokenHeader = (token) => {
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


/* 로그아웃 시 토큰과 만료시간 삭제 */
export const logoutActionHandler = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('expirationTime');
};
