import {NavLink, useNavigate} from "react-router-dom";
import {logoutActionHandler, retrieveStoredToken} from "../../auth-action";
import axios from "axios";
import {useState, useEffect} from "react";
import styles from './HeaderLinks.module.css';

export default function HeaderLinks({ setSelectedMenu }) {

    const movePage = useNavigate();
    function goJoin() {
        movePage('/user/join');
    }



    const [token, setToken] = useState('');
    /* 관리자 로그인시 관리자 헤더 출력 */

    const [role, setRole] = useState('MEMBER');

    function roleTest() {
        axios.get('http://localhost:8080/api/auth/role', {
            headers: {
                Authorization: 'Bearer ' + retrieveStoredToken().token
            }
        })
            .then(response => {
                console.log('response', response);
                if(retrieveStoredToken()!=null){
                    setToken(retrieveStoredToken().token);
                }
                setRole(response.data);
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }


    function logout() {
        axios.post('http://localhost:8080/api/auth/logout', null, {
            headers: {
                Authorization: `Bearer `+token
            }
        })
            .then(response => {
                console.log("성공");
                logoutActionHandler();
                setToken('');
                window.location.replace("/");
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }

    useEffect(() => {
        // 컴포넌트가 렌더링된 후에 roleTest 함수 호출
        if(retrieveStoredToken()!=null){
            roleTest();
        }
    }, []);


    if (token!='') {
        if (role === 'ADMIN') {
            return (
                <ul className="navbar-nav h-100 ml-4">
                    <li className="nav-item" onClick={() => setSelectedMenu('ContentManagement')} >
                        콘텐츠관리
                    </li>
                    <li className="nav-item" onClick={() => setSelectedMenu('LectureManagement')} >
                        강의관리
                    </li>
                    <li className="nav-item" onClick={() => setSelectedMenu('LectureProgress')} >
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
                        <NavLink to="/user/StudentLecture"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                            강의수강
                        </NavLink>
                    </li>
                    <li className="nav-item">
                        수강신청
                    </li>
                    <li className="nav-item" onClick={logout}>
                        로그아웃
                    </li>
                </ul>
            )

        }

    } else {
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
                        <NavLink to="/user/StudentLecture"
                                 className={({isActive}) => (isActive ? styles.active : "")}
                        >
                            강의수강
                        </NavLink>
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
