import ContentSearch from "../ContentSearch/ContentSearch";
import InsertDeleteButton from "../InsertDeleteButton/InsertDeleteButton";
import ContentListTop from "../ContentListTop/ContentListTop";
import {Col, Container, Row} from "react-bootstrap";
import ContentList from "../ContentList/ContentList";
import style from './ContentManagement.module.css';
import ContentInfo from "../ContentInfo/ContentInfo";
import Grid from "tui-grid";
import 'tui-grid/dist/tui-grid.css';
import React, {useEffect, useState} from "react";
import axios from "axios";
import {retrieveStoredToken} from "../../auth-action";
import GoogleOAuthLogin from "../GoogleOAuthLogin/GoogleOAuthLogin";
import AdminPopup from "../AdminPopup/AdminPopup";

export default function ContentManagement() {

    // 팝업 상태 관리용 변수
    const [showPopup, setShowPopup] = useState(false);

    const openPopup = () => {
        setShowPopup(true);
    };

    const closePopup = () => {
        setShowPopup(false);
    };


    return (
        <>
            <InsertDeleteButton page={'Content'} openPopup={openPopup} />
            <ContentSearch />
            <ContentListTop />
            <Container className="mt-2">
                <Row>
                    <Col sm={7} className={style.column}>
                        <ContentList />
                    </Col>
                    <Col sm={5} className={style.column}>
                        <ContentInfo />
                    </Col>
                </Row>
            </Container>

            {showPopup && (
                <AdminPopup closePopup={closePopup} page={'ContentInsert'} />
            )}

        </>
    )
}
