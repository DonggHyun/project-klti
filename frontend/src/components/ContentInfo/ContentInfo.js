import {Container, FormLabel, Row, Form, Button} from "react-bootstrap";
import style from './ContentInfo.module.css';
import {useEffect, useState} from "react";

export default function ContentInfo({content}) {

    const [contNo, setContNo] = useState('');
    const [contName, setContName] = useState('');
    const [contDesc, setContDesc] = useState('');
    const [contVideoId, setContVideoId] = useState('');
    const [contRuntime, setContRuntime] = useState(0);



    useEffect(() => {
        if(content !== null) {
            setContNo(content.contNo);
            setContName(content.contName);
            setContDesc(content.contDesc == null? '' : content.contDesc);
            setContVideoId(content.contVideoId);
            setContRuntime(content.contRuntime);
        }
    }, [content]);

    return (
        <Row className={style.container}>
            <div className={style.grid_box}>
                <Form.Group className={style.grid_row} controlId="contNo">
                    <Form.Label>콘텐츠 고유번호</Form.Label>
                    <Form.Control type="text" value={contNo} readOnly={true} />
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contName">
                    <Form.Label>콘텐츠명</Form.Label>
                    <Form.Control type="text" value={contName} />
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contDesc">
                    <Form.Label>콘텐츠 설명</Form.Label>
                    <Form.Control as="textarea" value={contDesc} className={style.textarea} rows={3} />
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contThumbnail">
                    <Form.Label>콘텐츠 썸네일</Form.Label>
                    <div className={style.flex_box}>
                        <Form.Control type="text" readOnly={true} />
                        <Button className={`${style.thumb_btn} ${style.btn}`}>등록</Button>
                    </div>
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contFile">
                    <Form.Label>콘텐츠 파일</Form.Label>
                    <div className={style.flex_box}>
                        <Form.Control type="text" readOnly={true} />
                        <Button className={`${style.file_btn} ${style.btn}`}>등록</Button>
                    </div>
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contVideoId">
                    <Form.Label>Youtube ID</Form.Label>
                    <div className={style.flex_box}>
                        <Form.Control type="text" value={contVideoId} />
                        <Button className={`${style.ytChk_btn} ${style.btn}`}>확인</Button>
                    </div>
                </Form.Group>
                <Form.Group className={style.grid_row} controlId="contRuntime">
                    <Form.Label>콘텐츠 길이 [초]</Form.Label>
                    <Form.Control type="text" value={contRuntime} />
                </Form.Group>
            </div>
        </Row>
    )
}
