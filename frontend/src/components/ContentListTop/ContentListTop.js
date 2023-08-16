import {Button, Container, Row} from "react-bootstrap";


export default function ContentListTop() {


    return (
        <Container className="mt-5">
            <Row className="justify-content-between align-items-center">
                <div>
                    콘텐츠 총 n 개
                </div>
                <div>
                    <Button>
                        업로드 인증
                    </Button>
                </div>
            </Row>
        </Container>
    )
}
