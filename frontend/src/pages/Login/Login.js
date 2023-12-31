import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useEffect, useRef, useState} from "react";
import {loginTokenHandler, retrieveStoredToken} from "../../auth-action";
import style from './Login.module.css';
import GoogleOAuthLogin from "../../components/GoogleOAuthLogin/GoogleOAuthLogin";


export default function Login() {


    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('MEMBER');

    async function loginSubmit() {
        try {
            const response = await axios.post('http://localhost:8080/api/auth/login', {
                userId: userId,
                password: password
            });

            loginTokenHandler(response.data.accessToken, response.data.tokenExpiresIn);

            if (response.status === 200) {
                await roleTest();
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }

    async function roleTest() {
        try {
            const response = await axios.get('http://localhost:8080/api/auth/role', {
                headers: {
                    Authorization: 'Bearer ' + retrieveStoredToken().token
                }
            });

            setRole(response.data);

            if (response.data === 'MEMBER') {
                window.location.replace("/class");
            } else if (response.data === 'ADMIN') {
                window.location.replace("/admin");
            }
        } catch (error) {
            console.error('Error:', error);
        }
    }



    return (
        <>
            <div className="container">
                <div className="row col-5 offset-3">
                    <div className={style.loginBox}>
                        <div className={style.block}>
                            <div className="row">
                                <div className="col-12 text-center">
                                    <h2 className="tm-block-title mb-4">Login</h2>
                                </div>
                            </div>
                            <div className="row mt-2">
                                <div className="col-12">
                                    <form method="post" className="tm-login-form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <div className="form-group">
                                            <label htmlFor="username">userId</label>
                                            <input
                                                name="username"
                                                type="text"
                                                className="form-control validate"
                                                id="username"
                                                required
                                                onChange={(e) => {setUserId(e.target.value);}}
                                            />
                                        </div>
                                        <div className="form-group mt-3">
                                            <label htmlFor="password">Password</label>
                                            <input
                                                name="password"
                                                type="password"
                                                className="form-control validate"
                                                id="password"
                                                required
                                                onChange={(e) => {setPassword(e.target.value);}}
                                            />
                                        </div>
                                        <div className="form-group mt-4">
                                            <input
                                                type="button"
                                                className="btn btn-primary btn-block text-uppercase"
                                                value="Login"
                                                onClick={loginSubmit}
                                            />

                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )

}

