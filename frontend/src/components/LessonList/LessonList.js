import {useEffect, useState} from "react";
import style from '../LectureList/LectureList.module.css';
import 'tui-grid/dist/tui-grid.css';
import axios from "axios";
import ContentGrid from "../ContentList/ContentGrid";
import {retrieveStoredToken} from "../../auth-action";


export default function LessonList({lectNo}){

    const [go, setGo] = useState(false);
    const [data, setData] = useState([]);

    useEffect(() => {

        axios.get(`http://localhost:8080/api/lesson?lectNo=${lectNo}`,{
            headers: {
                Authorization: 'Bearer ' + retrieveStoredToken().token
            }
        })
            .then(response => {
                if(response.status===200){
                    setData(response.data)
                }
            })
            .catch(error => {
                console.log('error: '+error);
            })
        setGo(true);
    },[lectNo]);

    const columns = [
        {
            name:'chk',
            editor:'checkbox'
        },{
            header: '차시순서',
            name:'lessOrder'
        },{
            header: '차시명',
            name:'lessName'
        },
    ];

    const gridConfig = {
        columns,
        rowHeight: 25,
        bodyHeight: 100
    }

    return (
        <div className={style.container} id="grid_tui">
            {go ? <ContentGrid data={data}{...gridConfig}/> : <p>차시 없음</p>}
        </div>
    )
}