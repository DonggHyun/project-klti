import {Container, Row} from "react-bootstrap";
import style from './ContentList.module.css';
import 'tui-grid/dist/tui-grid.css';
import Grid from '@toast-ui/react-grid';
import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {loginTokenHandler, retrieveStoredToken} from "../../auth-action";
import ContentGrid from "./ContentGrid";

export default function ContentList({data, go, getContentList}) {

    const gridRef = useRef();

    /* 콘텐츠 목록 data 가져오기 */
    useEffect(() => {
        getContentList();
    }, []);



    /* 콘텐츠 목록 Grid 컬럼 */
    const columns = [
        {
            name: 'chk',
            editor: 'checkbox'
        },
        {
            header: '콘텐츠명',
            name: 'contName'
        },
        {
            header: 'Youtube ID',
            name: 'contVideoId'
        },
        {
            header: '강의명',
            name: 'lectName'
        },
        {
            header: '콘텐츠 길이',
            name: 'contRuntime'
        }
    ];




    /* Grid 테이블 config */
    const gridConfig = {
        columns,
        rowHeight: 25,
        bodyHeight: 100
    }



/*    useEffect(() => {
        if (!gridRef.current) return;

        const grid = new Grid({
            el: gridRef.current,
            columns,
            rowHeight: 25,
            bodyHeight: 100
        });

    }, []);*/




    return (
        <div className={style.container} id="grid_tui">
            {go ? <ContentGrid data={data} {...gridConfig} /> : <p>Loading...</p>}
        </div>
    )
}

