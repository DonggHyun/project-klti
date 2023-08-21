import {Button, Container, Row, Stack, Form, Col} from "react-bootstrap";
import style from './ContentSearch.module.css';

export default function ContentSearch() {


    return (
        <Container>
            <Form as={Row} className={style.form}>
                <Col sm={5}>
                    <Form.Group className="d-flex align-items-center" controlId="contNameInput">
                        <Col sm={4}>
                            <Form.Label className={style.label}>
                                콘텐츠명
                            </Form.Label>
                        </Col>
                        <Col sm={8}>
                            <Form.Control type="text" placeholder="콘텐츠명" />
                        </Col>
                    </Form.Group>
                </Col>
                <Col sm={5}>
                    <Form.Group className="d-flex align-items-center" controlId="lectNameInput">
                        <Col sm={4}>
                            <Form.Label className={style.label}>
                                강의명
                            </Form.Label>
                        </Col>
                        <Col sm={8}>
                            <Form.Control type="text" placeholder="강의명" />
                        </Col>
                    </Form.Group>
                </Col>
                <Col sm={2} className="d-flex justify-content-end">
                    <Button>검색</Button>
                </Col>
            </Form>
        </Container>
    )
}
