import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useState} from "react";
import './join.css';
import React from 'react';

export default function Join() {

    const [name, setName] = useState('');
    const [birth, setBirth] = useState('');

    const [gender, setGender] = useState('여');
    const [userEmail, setUserEmail] = useState('');
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [passwordCheck, setPasswordCheck] = useState('');
    const [passwordError, setPasswordError] = useState('');
    const [createReq, setCreateReq] = useState(new Date());
    const [role, setRole] = useState('');
    const [changePassword, setChangePassword] = useState('0');
    const navigate = useNavigate();

    const inputName = e => {
        setName(e.target.value);
    };

    function handleDateChange(e) {
        const formattedDate = e.target.value.replace(/-/g, '');
        setBirth(formattedDate);
    }

    const inputGender = e => {
        setGender(e.target.value);
    };
    const inputUserEmail = e => {
        setUserEmail(e.target.value);
    };
    const inputUserId = e => {
        setUserId(e.target.value);
    };
    const inputPassword = e => {
        const newPassword = e.target.value;

        if (!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[a-zA-Z\d@$!%*#?&]{3,}$/.test(newPassword)) {
            setPasswordError('비밀번호 형식이 올바르지 않습니다.');
        } else {
            setPasswordError('');
        }
        setPassword(e.target.value);
    };
    const inputCreateReq = e => {
        setCreateReq(new Date())
    };

    const checkPasswordMatch = () => {
        if (password !== passwordCheck) {
            setPasswordError('비밀번호가 일치하지 않습니다.');
        } else {
            setPasswordError('');
        }
    };

    const inputPasswordCheck = e => {
        setPasswordCheck(e.target.value);
        checkPasswordMatch();
        if (password === e.target.value) {
            setPasswordError('');
        }
    };

    function handleFormSubmit(e) {
        e.preventDefault(); // 기본 제출 동작 방지
        if (!name || !birth || !gender || !userEmail || !userId || !password || !passwordCheck) {
            alert('입력값을 확인해주세요.');
            return;
        }
        if (password !== passwordCheck) {
            setPasswordError('비밀번호가 일치하지 않습니다.');
            return;
        }

        if (!/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*#?&])[a-zA-Z\d@$!%*#?&]{3,}$/.test(password)) {
            setPasswordError('비밀번호 형식이 올바르지 않습니다.');
            return;
        }

        if (passwordError) {
            alert('형식이 올바르지 않거나 일치하지 않습니다.');
            return;
        }

        joinSubmit(); // 위의 모든 조건을 통과하면 실제 가입 함수 호출
    }

    function joinSubmit() {

        axios.post('http://localhost:8080/api/auth/join', {
            name: name,
            birth: birth,      // 날짜 넣는법 더 알아보기
            gender: gender,
            userEmail: userEmail,
            userId: userId,
            password: password,
            role: role,
            createReq: createReq,
            changePassword: changePassword
        })
            .then(response => {
                console.log('회원가입 완료!');
                console.log('response', response);
                if (response.status === 200) {
                    navigate("/");
                }
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }


    return (
        <>
            <div id="joinBody">
                <h3>회원가입</h3>
                <form method="post" onSubmit={handleFormSubmit}>
                    <table id="jointable">
                        <tr>
                            <td>이름</td>
                            <td className="join_td_2"><input type="text" name="name" onChange={inputName} placeholder="이름" required/></td>
                        </tr>
                        <tr>
                            <td>생년월일</td>
                            <td className="join_td_2"><input type="date" id="birth" name="birth" onChange={handleDateChange} required/></td>
                        </tr>
                        <tr>
                            <td>
                                성별
                            </td>
                            <td className="join_td_2">
                                <select name="gender" onChange={inputGender}  required>
                                    <option value="여">여</option>
                                    <option value="남">남</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>이메일</td>
                            <td className="join_td_2"><input type="email" name="userEmail" onChange={inputUserEmail} placeholder="이메일" required/></td>
                        </tr>
                        <tr>
                            <td>ID</td>
                            <td className="join_td_2"><input type="text" name="userId" onChange={inputUserId} placeholder="아이디" required/></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td className="join_td_2">
                                <input type="password" name="password" onChange={inputPassword} placeholder="비밀번호" required/>
                            </td>
                        </tr>

                        <tr>
                            <td>PasswordCheck</td>
                            <td className="join_td_2"><input type="password" name="passwordCheck" onChange={inputPasswordCheck} placeholder="비밀번호 확인" required/></td>
                        </tr>
                        <tr>
                            <td colSpan="2" className="join_password"><small>숫자,영어,특수문자를 포함해 3자리 이상 입력해주세요.</small></td>
                        </tr>
                        {passwordError && <tr><td colSpan="2" className="error_td"><small className="error_message">{passwordError}</small></td></tr>}
                        <tr>
                            <td colSpan="2" className="join_btn">
                                <button
                                    className="join_btn_inner"
                                >가입
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>

        </>
    )
}
