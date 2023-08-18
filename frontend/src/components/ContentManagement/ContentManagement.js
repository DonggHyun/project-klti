import ContentSearch from "../ContentSearch/ContentSearch";
import InsertDeleteButton from "../InsertDeleteButton/InsertDeleteButton";
import ContentListTop from "../ContentListTop/ContentListTop";
import {Col, Container, Row} from "react-bootstrap";
import ContentList from "../ContentList/ContentList";
import style from './ContentManagement.module.css';
import ContentInfo from "../ContentInfo/ContentInfo";
import Grid from "tui-grid";
import 'tui-grid/dist/tui-grid.css';
import {useEffect} from "react";

export default function ContentManagement() {


    return (
        <>
            <InsertDeleteButton />
            <ContentSearch />
            <ContentListTop />
            <Container className="mt-2">
                <Row>
                    <Col sm={7} className={style.column}>
                        <ContentList wait={2000}/>
                    </Col>
                    <Col sm={5} className={style.column}>
                        <ContentInfo />
                    </Col>
                </Row>
            </Container>
        </>
    )
}
