import {Button, Container, Row, Stack, Form, Col} from "react-bootstrap";
import style from './CourseRegisteration.module.css';

export default function CourseRegisteration() {


    return (
        <Container>
            <Form as={Row} className={style.form}>
                <Col sm={5}>
                    <Form.Group className="d-flex align-items-center" controlId="lectNameInput">
                        <Col sm={4}>
                            <Form.Label className={style.label}>
                                강좌명
                            </Form.Label>
                        </Col>
                        <Col sm={8}>
                            <Form.Control type="text" placeholder="온라인강좌명" />
                        </Col>
                    </Form.Group>
                </Col>
                <Col sm={5}>
                    <Form.Group className="d-flex align-items-center" controlId="lectNameInput">
                        <Col sm={4}>
                            <Form.Label className={style.label}>
                                강좌진행상태
                            </Form.Label>
                        </Col>
                        <Col sm={8}>
                            <Form.Select>
                                <option value="준비중">준비중</option>
                                <option value="운영중">운영중</option>
                                <option value="종료">종료</option>
                            </Form.Select>
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
