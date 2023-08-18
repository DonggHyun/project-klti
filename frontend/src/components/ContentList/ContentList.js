import {Container, Row} from "react-bootstrap";
import style from './ContentList.module.css';
import 'tui-grid/dist/tui-grid.css';
import Grid from '@toast-ui/react-grid';
import {useEffect, useRef, useState} from "react";
import axios from "axios";
import {loginTokenHandler, retrieveStoredToken} from "../../auth-action";

export default function ContentList() {

    const gridRef = useRef();
    const [data, setData] = useState([]);

    useEffect(() => {

        axios.get('http://localhost:8080/api/contents', {
            headers: {
                'Authorization': 'Bearer ' + retrieveStoredToken().token
            }
        })
            .then(response => {
                console.log(response);
                if (response.status === 200) {
                    setData(response.data);
                }
            })
            .catch(error => {
                console.error('Error :', error);
            });

    }, []);


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
        <div className={style.container} id="grid_tui" ref={gridRef}>
            <Grid
                columns={columns}
                data={data}
                rowHeight={25}
                bodyHeight={100}
            />
        </div>
    )
}

