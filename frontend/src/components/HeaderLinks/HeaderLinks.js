import {NavLink, useNavigate} from "react-router-dom";
import {retrieveStoredToken} from "../../auth-action";
import axios from "axios";
import {useState} from "react";
import styles from './HeaderLinks.module.css';

export default function HeaderLinks() {

    const movePage = useNavigate();
    function goJoin() {
        movePage('/user/join');
    }



    /* 관리자 로그인시 관리자 헤더 출력 */
    let token = '';
    if(retrieveStoredToken() != null) {
        token = retrieveStoredToken().token;
    }
    const [role, setRole] = useState('MEMBER');

    function roleTest() {
        axios.get('http://localhost:8080/api/auth/role', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(response => {
                console.log('response', response);
                setRole(response.data);
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }

    function logout() {
        axios.post('http://localhost:8080/api/auth/logout', null, {
            headers: {
                Authorization: `Bearer ${retrieveStoredToken().token}`
            }
        })
            .then(response => {
                console.log("성공");
                window.location.replace("/");
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    roleTest();

    if(token) {
        if (role === 'ADMIN') {
            return (
                <ul className="navbar-nav h-100 ml-4">
                    <li className="nav-item">
                        콘텐츠관리
                    </li>
                    <li className="nav-item">
                        강의관리
                    </li>
                    <li className="nav-item">
                        수강현황
                    </li>

                    <li className="nav-item" onClick={logout}>
                        로그아웃
                    </li>
                </ul>
            )
        } else {
            return (
                <ul className="navbar-nav h-100 ml-4">
                    <li className="nav-item">
                        나의정보
                    </li>
                    <li className="nav-item">
                        강의수강
                    </li>
                    <li className="nav-item">
                        수강신청
                    </li>
                    <li className="nav-item" onClick={logout}>
                        로그아웃
                    </li>
                    <li>
                        <NavLink to="/user/join"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                            회원가입
                        </NavLink>
                    </li>
                </ul>
            )

        }
    }else{
        if (role === 'ADMIN') {
            return (
                <ul className="navbar-nav h-100 ml-4">
                    <li className="nav-item">
                        콘텐츠관리
                    </li>
                    <li className="nav-item">
                        강의관리
                    </li>
                    <li className="nav-item">
                        수강현황
                    </li>
                    <li className="nav-item">
                        <NavLink to="/"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                             로그인
                        </NavLink>
                    </li>
                </ul>
            )
        } else {
            return (
                <ul className="navbar-nav h-100 ml-4">
                    <li className="nav-item">
                        나의정보
                    </li>
                    <li className="nav-item">
                        강의수강
                    </li>
                    <li className="nav-item">
                        수강신청
                    </li>
                    <li className="nav-item">
                        <NavLink to="/"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                            로그인
                        </NavLink>
                    </li>
                    <li>
                        <NavLink to="/user/join"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                            회원가입
                        </NavLink>
                    </li>
                </ul>
            )

        }
    }



}
