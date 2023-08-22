import axios from "axios";
import {retrieveStoredToken, createTokenHeader } from "../../auth-action";
import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";

export default function GoogleOAuthRedirect() {

    const navigate = useNavigate();

    const [authorizationCode, setAuthorizationCode] = useState('');
    const [scope, setScope] = useState('');
    const [gotBoth, setGotBoth] = useState(false);

    const [goSave, setGoSave] = useState(false);


    /* 토큰 생성 요청 */
    function createGoogleAccessToken() {

        axios.post('http://localhost:8080/api/admin/oauthcallback', {
            'GoogleToken': authorizationCode,
            'scope' : scope,
            'jwtToken': retrieveStoredToken().token
        }, {
            headers: {
                'Authorization': 'Bearer ' + retrieveStoredToken().token,
                'Content-Type': 'application/json'
            }
        } )
            .then(response => {
                if (response.status === 200) {
                    setGoSave(true);
                } else {
                    console.log(response);
                }
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }


    /* 토큰 저장 요청 */
    function saveGoogleAccessToken() {
        axios.post('http://localhost:8080/api/admin/googleaccesstoken', {
            'jwtToken': retrieveStoredToken().token
        }, {
            headers: {
                'Authorization': 'Bearer ' + retrieveStoredToken().token,
                'Content-Type': 'application/json'
            }
        } )
            .then(response => {
                if (response.status === 200) {
                    alert("인증이 완료되었습니다");
                    setGoSave(false);
                    navigate("/admin");
                } else {
                    console.log(response);
                }
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }



    useEffect(() => {

        // 현재 URL 읽어오기
        const currentUrl = new URL(window.location.href);

        // 파라미터 읽어오기
        const queryParams = new URLSearchParams(currentUrl.search);

        setAuthorizationCode(queryParams.get("code"));
        console.log('authorizationCode', authorizationCode);
        setScope(queryParams.get('scope'));
        console.log('scope', scope);

    }, []);

    useEffect(() => {
        if (authorizationCode !== '' && scope !== '') {
            console.log('검증된 authorizationCode', authorizationCode);
            console.log('검증된 scope', scope);
            setGotBoth(true);
        }
    }, [authorizationCode, scope]);

    useEffect(() => {
        if (gotBoth) {
            console.log('두개 다 들어옴');
            createGoogleAccessToken();
            setGotBoth(false);
        }
    }, [gotBoth]);




    /* 토큰을 발급받고 다시 돌아온 경우 저장하는 요청을 다시 발송 */
    useEffect(() => {
        if(goSave){
            saveGoogleAccessToken();
        }
    }, [goSave]);


    return (
        <></>
    )
}