import React, {useState} from 'react';
import Form from 'react-bootstrap/Form';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import Table from 'react-bootstrap/Table';
import Pagination from 'react-bootstrap/Pagination';

export default function LectureProgress() {
    const [currentPage, setCurrentPage] = useState(1);
    const [selectedSection, setSelectedSection] = useState('전체이수현황');

    const data = [
        {id: 1, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 2, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 3, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 4, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 5, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 6, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 7, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 8, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 9, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 10, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 11, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 12, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 13, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 14, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 16, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 17, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 18, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 19, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 20, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 21, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 22, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 23, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 24, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 25, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 26, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 27, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 28, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        {id: 29, type: 'Mark', name: 'Otto', description: '@mdo', startDate: '@mdo', endDate: '@mdo', status: '@mdo'},
        // 나머지 데이터도 추가
    ];

    const itemsPerPage = 5;
    const totalPages = Math.ceil(data.length / itemsPerPage);


    const handlePageChange = (pageNumber) => {
        setCurrentPage(pageNumber);
    };

    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;

    const items = [];
    const numPagesToShow = Math.min(3, totalPages); // 최대 3개 페이지 표시
    for (let number = 1; number <= numPagesToShow; number++) {
        items.push(
            <Pagination.Item
                key={number}
                active={number === currentPage}
                onClick={() => handlePageChange(number)}
            >
                {number}
            </Pagination.Item>
        );
    }

    const currentPageData = data.slice(startIndex, endIndex);

    return (
        <Container>
            <Row className="mt-3">
                <Col xl>
                    <Form style={{border: '1px solid black', padding: '10px'}}>
                        <Row>
                            <Col>
                                <Form.Group>
                                    <Form.Label>온라인강좌명</Form.Label>
                                    <Form.Control placeholder="강좌명을 입력하세요"/>
                                </Form.Group>
                            </Col>
                            <Col>
                                <Form.Group>
                                    <Form.Label className="mt-1">강좌진행상태</Form.Label>
                                    <Form.Select>
                                        <option>(전체)</option>
                                        <option>준비중</option>
                                        <option>운영중</option>
                                        <option>종료</option>
                                    </Form.Select>
                                </Form.Group>
                            </Col>
                            <Col className="mt-3">
                                <Button variant="primary" type="submit">
                                    조회
                                </Button>
                            </Col>
                        </Row>
                    </Form>
                </Col>
            </Row>

            <Row className="mt-3">
                <Col xl>
                    <p>선수학습강좌</p>
                    <Table hover>
                        <thead>
                        <tr>
                            <th>개설강좌번호</th>
                            <th>온라인강좌구분</th>
                            <th>온라인강좌명</th>
                            <th>온라인강좌설명</th>
                            <th>강좌시작일시</th>
                            <th>강좌종료일시</th>
                            <th>강좌진행상태</th>
                        </tr>
                        </thead>
                        <tbody>
                        {currentPageData.map(item => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.type}</td>
                                <td>{item.name}</td>
                                <td>{item.description}</td>
                                <td>{item.startDate}</td>
                                <td>{item.endDate}</td>
                                <td>{item.status}</td>
                            </tr>
                        ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <Row>
                <Col xl>
                    <Pagination size="sm">
                        <Pagination.First onClick={() => handlePageChange(1)}/>
                        <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)}/>
                        {items}
                        <Pagination.Next onClick={() => handlePageChange(currentPage + 1)}/>
                        <Pagination.Last onClick={() => handlePageChange(totalPages)}/>
                    </Pagination>
                </Col>
            </Row>
            <Row className="mt-3">
                <Col xl>
                    <Button onClick={() => setSelectedSection('전체이수현황')} className="mr-3">
                        전체이수현황
                    </Button>
                    <Button onClick={() => setSelectedSection('차시별수강현황')}>
                        차시별수강현황
                    </Button>
                </Col>
            </Row>

            {selectedSection === '전체이수현황' && (
                <Row className="mt-3">
                    <Col xl>
                        <p>전체이수현황</p>
                        <Table hover>
                            <thead>
                            <tr>
                                <th>과정구분</th>
                                <th>기수</th>
                                <th>언어권</th>
                                <th>지원자구분</th>
                                <th>학번</th>
                                <th>성명</th>
                                <th>진도율(%)</th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentPageData.map(item => (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.type}</td>
                                    <td>{item.name}</td>
                                    <td>{item.description}</td>
                                    <td>{item.startDate}</td>
                                    <td>{item.endDate}</td>
                                    <td>{item.status}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                        <Pagination size="sm">
                            <Pagination.First onClick={() => handlePageChange(1)}/>
                            <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)}/>
                            {items}
                            <Pagination.Next onClick={() => handlePageChange(currentPage + 1)}/>
                            <Pagination.Last onClick={() => handlePageChange(totalPages)}/>
                        </Pagination>
                    </Col>
                </Row>
            )}

            {selectedSection === '차시별수강현황' && (
                <Row className="mt-3">
                    <Col xl>
                        <p>강좌차시정보</p>
                        <Table hover>
                            <thead>
                            <tr>
                                <th>차시순서</th>
                                <th>차시명</th>
                                <th>총시간</th>
                                <th>대상수</th>
                                <th>수강완료</th>
                                <th>수강중</th>
                                <th>미진행</th>
                                <th>이수율(%)</th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentPageData.map(item => (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.type}</td>
                                    <td>{item.name}</td>
                                    <td>{item.description}</td>
                                    <td>{item.startDate}</td>
                                    <td>{item.endDate}</td>
                                    <td>{item.status}</td>
                                    <td>{item.status}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                        <Pagination size="sm">
                            <Pagination.First onClick={() => handlePageChange(1)}/>
                            <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)}/>
                            {items}
                            <Pagination.Next onClick={() => handlePageChange(currentPage + 1)}/>
                            <Pagination.Last onClick={() => handlePageChange(totalPages)}/>
                        </Pagination>
                    </Col>

                    <Col xl>
                        <p>수강현황</p>
                        <Table hover>
                            <thead>
                            <tr>
                                <th>과정구분</th>
                                <th>기수</th>
                                <th>언어권</th>
                                <th>지원자구분</th>
                                <th>학번</th>
                                <th>성명</th>
                                <th>수강시간</th>
                                <th>진도율(%)</th>
                            </tr>
                            </thead>
                            <tbody>
                            {currentPageData.map(item => (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.type}</td>
                                    <td>{item.name}</td>
                                    <td>{item.description}</td>
                                    <td>{item.startDate}</td>
                                    <td>{item.endDate}</td>
                                    <td>{item.status}</td>
                                    <td>{item.status}</td>
                                </tr>
                            ))}
                            </tbody>
                        </Table>
                        <Pagination size="sm">
                            <Pagination.First onClick={() => handlePageChange(1)}/>
                            <Pagination.Prev onClick={() => handlePageChange(currentPage - 1)}/>
                            {items}
                            <Pagination.Next onClick={() => handlePageChange(currentPage + 1)}/>
                            <Pagination.Last onClick={() => handlePageChange(totalPages)}/>
                        </Pagination>
                    </Col>
                </Row>
            )}
        </Container>
    );
}
