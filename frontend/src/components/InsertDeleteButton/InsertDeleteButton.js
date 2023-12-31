import {Button, Container, Row, Stack, Form, Col} from "react-bootstrap";
import axios, {AxiosError} from "axios";
import {retrieveStoredToken} from "../../auth-action";


export default function InsertDeleteButton({page, openPopup}) {

    function plusItem() {

        // 콘텐츠 추가는 인증이 완료되어야 가능하므로 백엔드 토큰 값 조회
        if(page === 'Content') {

            axios.get('http://localhost:8080/api/admin/googleaccesstoken', {
                headers: {
                    'Authorization': 'Bearer ' + retrieveStoredToken().token
                }
            })
                .then(response => {
                    if (response.status === 200) {
                        openPopup();
                    }
                })
                .catch(error => {
                    console.error('Error :', error);
                    if (error.response.status === 401) {
                        // 토큰이 없거나 만료된 경우 401 에러 반환
                        alert('콘텐츠 추가는 업로드 인증 후 가능합니다');
                    }
                });
        }
    }


    function minusItem() {

    }



    return (
        <Container className="mt-5 mb-3">
            <Stack as={Row} direction="horizontal">
                <Col sm={12} className="d-flex justify-content-end">
                    <Button className="mr-2" onClick={plusItem}>신규</Button>
                    <Button className="mr-2" onClick={minusItem}>삭제</Button>
                    <Button>저장</Button>
                </Col>
            </Stack>
        </Container>
    )
}
