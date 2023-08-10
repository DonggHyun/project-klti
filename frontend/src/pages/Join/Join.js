import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useState} from "react";




export default function Join() {

    const [name, setName] = useState('');
    const [birth, setBirth] = useState('');
    const [role, setRole] = useState('');
    const [gender, setGender] = useState('');
    const [userEmail, setUserEmail] = useState('');
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');

    const inputName = e => {
        setName(e.target.value);
    };
    function inputBirth(e) {
        /*const selectedDate = e.target.value;
        const formattedDate = selectedDate.replace(/-/g, ''); // "-" 제거
        e.target.value = formattedDate; // YYYYMMDD 형식으로 변경*/
        setBirth(e.target.value);
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
        setPassword(e.target.value);
    };



    function joinSubmit() {
        axios.post('http://localhost:8080/api/join', {
            name: name,
            birth: '',      // 날짜 넣는법 더 알아보기
            role: role,
            gender: gender,
            userEmail: userEmail,
            userId: userId,
            password: password
        })
            .then(response => {
                console.log('회원가입 완료!');
                console.log('response', response);
                window.location.replace("/");
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }


    return (
        <>
            <form method="post">
                <p>이름 : <input type="text" name="name" onChange={inputName}/></p>
                <p>생년월일 : <input type="date" id="birth" name="birth" onChange={inputBirth}/></p>
                <p>
                    신분 :
                    <select name="role">
                        <option value="강사">강사</option>
                        <option value="학생">학생</option>
                    </select>
                </p>
                <p>
                    성별 :
                    <select name="gender" onChange={inputGender}>
                        <option value="여">여</option>
                        <option value="남">남</option>
                    </select>
                </p>
                <p>이메일 : <input type="email" name="userEmail" onChange={inputUserEmail}/></p>
                <p>ID : <input type="text" name="userId" onChange={inputUserId}/></p>
                <p>패스워드 : <input type="password" name="password" onChange={inputPassword}/></p>
                            <input type="button" value="가입" onClick={joinSubmit}/>
            </form>
        </>
    )
}

