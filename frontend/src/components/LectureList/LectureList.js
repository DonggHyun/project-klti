import 'tui-grid/dist/tui-grid.css';
import style from './LectureList.module.css';
import ContentGrid from "../ContentList/ContentGrid";
import {useEffect, useState} from "react";
import {retrieveStoredToken} from "../../auth-action";
import axios from "axios";

export default function LectureList({onLectureClick}) {
    const [go, setGo] = useState(false);
    const [data, setData] = useState("");

    useEffect(() => {
        axios.get('http://localhost:8080/api/lecture', {
            headers: {
                Authorization: 'Bearer ' + retrieveStoredToken().token
            }
        })
            .then(response => {
                if (response.status === 200) {
                    setData(response.data);
                }
            })
            .catch(error => {
                console.error('error: ', error);
            })
        setGo(true);
    }, []);

    const handleRowClick = (rowData) => {
        const lectureNo = rowData.lectNo;
        console.log(lectureNo);
        if (lectureNo) {
            onLectureClick(lectureNo);
        }
        console.log('Clicked row data:', rowData);
    }

    /* Grid 테이블 config */
    const gridConfig = {
        columns: [
            {
                name: 'chk',
                editor: 'checkbox' // 열의 편집기 유형
            },
            {
                header: '개설강좌번호',
                name: 'lectNo',
            },
            {
                header: '온라인강좌명', //열의 헤더 레이블 지정
                name: 'lectName', // 열의 이름 또는 키 지정
            },
            {
                header: '온라인강좌설명',
                name: 'lectDesc',
            },
            {
                header: '강좌시작일시',
                name: 'lectStartDate',
            },
            {
                header: '강좌종료일시',
                name: 'lectEndDate',
            },
            {
                header: '강좌신행상태',
                name: 'lectStatus',
            },
        ],
        rowHeight: 25,
        bodyHeight: 100,
        usageStatistics: false, // 사용 통계 정보 비활성화
         // 행 클릭 시 이벤트 핸들러 등록
    }

    return (
        <div className={style.container} id="grid_tui">
            {go ? <ContentGrid data={data} {...gridConfig} onGridRowClick={handleRowClick}/> : <p>로딩중</p>}        </div>
    )
}
