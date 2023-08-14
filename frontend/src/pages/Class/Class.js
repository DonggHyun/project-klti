import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { retrieveStoredToken } from '../../auth-action';
import './class.css';

function Class() {
    const [studentInfo, setStudentInfo] = useState({
        name: '',
        userId: '',
        password: '',
        birth: '',
        gender: '',
        userEmail: ''
    });

    useEffect(() => {
        axios.get('http://localhost:8080/api/auth/me', {
            headers: {
                Authorization: `Bearer ${retrieveStoredToken().token}`
            }
        })
            .then(response => {
                const studentInfo = response.data;
                setStudentInfo(studentInfo);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }, []);

    const maskPassword = (password) => {
        return '*****'; // Replace with your masking logic if needed
    };
    const formatBirth = (birth) => {
        const year = birth.substring(0, 4);
        const month = birth.substring(4, 6);
        const day = birth.substring(6, 8);
        return `${year}년 ${month}월 ${day}일`;
    };

    return (
        <>
            <div id="classBody">
                <h3><h4 id="className">{studentInfo.name}</h4> 학생 정보</h3>
                <form>
                    <table id="classTable" border="1">
                        <tr>
                            <td className="class_td_1">학생 이름</td>
                            <td className="class_td_2">{studentInfo.name}</td>
                        </tr>
                        <tr>
                            <td className="class_td_1">아이디</td>
                            <td className="class_td_2">{studentInfo.userId}</td>
                        </tr>
                        <tr>
                            <td className="class_td_1">비밀번호</td>
                            <td className="class_td_2">{maskPassword(studentInfo.password)}</td>
                        </tr>
                        <tr>
                            <td className="class_td_1">생년월일</td>
                            <td className="class_td_2">{formatBirth(studentInfo.birth)}</td>
                        </tr>
                        <tr>
                            <td className="class_td_1">성별</td>
                            <td className="class_td_2">{studentInfo.gender}</td>
                        </tr>
                        <tr>
                            <td className="class_td_1">이메일</td>
                            <td className="class_td_2">{studentInfo.userEmail}</td>
                        </tr>
                    </table>
                </form>
            </div>
        </>
    );
}

export default Class;
