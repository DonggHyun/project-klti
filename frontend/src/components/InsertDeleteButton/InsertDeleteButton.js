import {Button, Container, Row, Stack, Form, Col} from "react-bootstrap";


export default function InsertDeleteButton() {


    return (
        <Container className="mt-5 mb-3">
            <Stack as={Row} direction="horizontal">
                <Col sm={12} className="d-flex justify-content-end">
                    <Button className="mr-2">신규</Button>
                    <Button className="mr-2">삭제</Button>
                    <Button>저장</Button>
                </Col>
            </Stack>
        </Container>
    )
}
