import {Button, Col, Container, Row} from "react-bootstrap";


export default function ContentListTop() {


    return (
        <Container className="mt-5">
            <Row className="justify-content-between align-items-center">
                <Col sm={2} className="d-flex">
                    <div>
                        콘텐츠 총 n 개
                    </div>
                </Col>
                <Col sm={2} className="d-flex justify-content-end mr-3">
                    <div>
                        <Button>
                            업로드 인증
                        </Button>
                    </div>
                </Col>
            </Row>
        </Container>
    )
}
