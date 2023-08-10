import {useNavigate} from "react-router-dom";


function handleDateChange() {
    const dateInput = document.getElementById('birth');
    const selectedDate = dateInput.value;
    const formattedDate = selectedDate.replace(/-/g, ''); // "-" 제거
    dateInput.value = formattedDate; // YYYYMMDD 형식으로 변경
}

export default function Join() {

    const movePage = useNavigate();
    function goHome() {
        movePage('/');
    }

    return (
        <>
            <form action="@{/join}" method="post">
                <p>이름 : <input type="text" name="name"/></p>
                <p>생년월일 : <input type="date" id="birth" name="birth" onChange="handleDateChange()"/></p>
                <p>
                    신분 :
                    <select name="role">
                        <option value="강사">강사</option>
                        <option value="학생">학생</option>
                    </select>
                </p>
                <p>
                    성별 :
                    <select name="gender">
                        <option value="여">여</option>
                        <option value="남">남</option>
                    </select>
                </p>
                <p>이메일 : <input type="email" name="userEmail"/></p>
                <p>ID : <input type="text" name="userId"/></p>
                <p>패스워드 : <input type="password" name="password"/></p>
                            <input type="submit" value="가입"/>
            </form>
        </>
    )
}

