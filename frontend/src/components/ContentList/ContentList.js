import {Container, Row} from "react-bootstrap";
import style from './ContentList.module.css';
import 'tui-grid/dist/tui-grid.css';
import Grid from '@toast-ui/react-grid';
import {useEffect, useRef} from "react";

export default function ContentList() {

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


    return (
        <Grid
            columns={columns}
            rowHeight={25}
            bodyHeight={100}
        />
    )
}

