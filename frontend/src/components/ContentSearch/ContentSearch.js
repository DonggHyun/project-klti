import {Button, Container, Row, Stack, Form, Col} from "react-bootstrap";
import style from './ContentSearch.module.css';

export default function ContentSearch() {


    return (
        <Container>
            <Form as={Row} className={style.form}>
                <Form.Group as={Row} column sm="5" className="align-items-center" controlId="">
                    <Form.Label column sm="4" align="center">
                        콘텐츠명
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control type="text" placeholder="콘텐츠명" />
                    </Col>
                </Form.Group>
                <Form.Group as={Row} column sm="5" className="align-items-center" controlId="">
                    <Form.Label column sm="4" align="center">
                        강의명
                    </Form.Label>
                    <Col sm="8">
                        <Form.Control type="text" placeholder="강의명" />
                    </Col>
                </Form.Group>
                <Col sm="2" className="d-flex justify-content-end">
                    <Button>검색</Button>
                </Col>
            </Form>
        </Container>
    )
}
