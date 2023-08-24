import InsertDeleteButton from "../InsertDeleteButton/InsertDeleteButton";
import CourseRegisteration from "../CourseRegisteration/CourseRegisteration";
import {Col, Container, Row} from "react-bootstrap";
import LectureList from "../LectureList/LectureList";
import 'tui-grid/dist/tui-grid.css';
import style from './LectureManagement.module.css';
import LessonList from "../LessonList/LessonList";
import {useState} from "react";

export default function LectureManagement() {

    const[selectedInfo, setSelectedInfo] = useState(null);
    const [selectedLectNo, setSelectedLectNo] = useState(null);

    const onLectureClick = (info) => {
        setSelectedInfo(info);
        setSelectedLectNo(info.lectNo); // info에 lectNo 값이 포함되어 있다고 가정합니다.
    }

    return (
        <>
            <InsertDeleteButton/>
            <CourseRegisteration />
            <Container className="mt-2">
                <Row>
                    <Col sm={12} className={style.column}>
                        <LectureList  onLectureClick={onLectureClick} />
                    </Col>
                </Row>
                <Row>
                    <Col sm={6} className={style.column}>
                        <LessonList lectNo={selectedLectNo}/>
                    </Col>
                    <Col sm={6} className={style.column}>
                        <LectureList/>
                    </Col>
                </Row>
            </Container>
        </>
    )
}