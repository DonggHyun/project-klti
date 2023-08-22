import 'tui-grid/dist/tui-grid.css';
import style from './LectureList.module.css';
import ContentGrid from "../ContentList/ContentGrid";
import {useEffect, useState} from "react";
import {retrieveStoredToken} from "../../auth-action";
import axios from "axios";


export default function LectureList() {

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
    const columns = [
        {
            name: 'chk',
            editor: 'checkbox'
        },
        {
            header: '개설강좌번호',
            name: 'lectNo'
        },
        {
            header: '온라인강좌명',
            name: 'lectName'
        },
        {
            header: '온라인강좌설명',
            name: 'lectDesc'
        },
        {
            header: '강좌시작일시',
            name: 'lectStartDate'
        },
        {
            header: '강좌종료일시',
            name: 'lectEndDate'
        },
        {
            header: '강좌신행상태',
            name: 'lectStatus'
        },
    ];


    /* Grid 테이블 config */
    const gridConfig = {
        columns,
        rowHeight: 25,
        bodyHeight: 100
    }

    return (
        <div className={style.container} id="grid_tui">
            {go ? <ContentGrid data={data}{...gridConfig}/> : <p>로딩중</p>}
        </div>
    )
}