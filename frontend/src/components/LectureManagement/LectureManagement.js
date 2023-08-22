import InsertDeleteButton from "../InsertDeleteButton/InsertDeleteButton";
import CourseRegisteration from "../CourseRegisteration/CourseRegisteration";
import {Col, Container, Row} from "react-bootstrap";
import LectureList from "../LectureList/LectureList";
import 'tui-grid/dist/tui-grid.css';
import style from './LectureManagement.module.css';

export default function LectureManagement() {


    return (
        <>
            <InsertDeleteButton/>
            <CourseRegisteration />
            <Container className="mt-2">
                <Row>
                    <Col sm={12} className={style.column}>
                        <LectureList/>
                    </Col>
                </Row>
                <Row>
                    <Col sm={6} className={style.column}>
                        <LectureList/>
                    </Col>
                    <Col sm={6} className={style.column}>
                        <LectureList/>
                    </Col>
                </Row>
            </Container>
        </>
    )
}