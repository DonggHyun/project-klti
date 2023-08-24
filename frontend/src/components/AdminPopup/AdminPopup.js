import React from "react";
import style from './AdminPopup.module.css';
import {Col, Container, Row} from "react-bootstrap";
import ContentInfo from "../ContentInfo/ContentInfo";
import ContentInsertPopup from "../ContentInsertPopup/ContentInsertPopup";

export default function AdminPopup({closePopup, page}) {

    function renderPopupContent() {
        if(page === 'ContentInsert') {
            return (
                <ContentInsertPopup />
            )
        }
    }



    return (
        <div className={style.popup_overlay}>
            <Container>
                <Row className="justify-content-center">
                    <Col lg={10} className={style.popup_container}>
                        {renderPopupContent()}
                        <button className={style.popup_close} onClick={closePopup}>X</button>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}