import Accordion from 'react-bootstrap/Accordion';
import Table from 'react-bootstrap/Table';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import ProgressBar from 'react-bootstrap/ProgressBar';
import Card from 'react-bootstrap/Card';

import './StudentLecture.css';

import React, { useState, useEffect } from 'react';
import YouTube from "react-youtube";
// import YoutubeEmbed from "./YoutubeEmbed";

export default function StudentLecture() {
    // 팝업 상태 관리용 변수
    const [showPopup, setShowPopup] = useState(false);
    const [finalPosition, setFinalPosition] = useState(null);

    const openPopup = () => {
        setShowPopup(true);
    };

    const closePopup = () => {
        setShowPopup(false);
    };

    const savePlaybackProgress = (currentTime) => {
        localStorage.setItem('videoPlaybackPosition', currentTime);
    };
    //진체진도율 표시 위한 변수 선언
    const now = 60;

    //유튜브 재생목록 가져오기


    return (
        <Container>
            <Row>
                <Col xl>
                    <Card border="secondary" className="mt-4">
                        <Card.Header>전체진도율</Card.Header>
                        <Card.Body>
                            {/*일단 title없이*/}
                            <Card.Title></Card.Title>
                            <Card.Text>
                                <ProgressBar now={now} label={`${now}%`}/>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                    <br />
                    <Accordion>
                        <Accordion.Item eventKey="0">
                            <Accordion.Header>기초미디어 번역이론(2023-07-01 ~ 2023-08-31)</Accordion.Header>
                            <Accordion.Body>
                                <Accordion defaultActiveKey={['0']} alwaysOpen>
                                    <Accordion.Item eventKey="0">
                                        <Accordion.Header>기초미디어 번역이론</Accordion.Header>
                                        <Accordion.Body>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}>
                                                        <Button variant="dark" onClick={openPopup}>학습하기</Button>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </Accordion.Body>
                        </Accordion.Item>

                        <Accordion.Item eventKey="1">
                            <Accordion.Header>번역을 위한 Subtitle Edit(2023-07-01 ~ 2023-08-31)</Accordion.Header>
                            <Accordion.Body>
                                <Accordion defaultActiveKey={['0']} alwaysOpen>
                                    <Accordion.Item eventKey="0">
                                        <Accordion.Header>번역을 위한 Subtitle Edit</Accordion.Header>
                                        <Accordion.Body>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                            <Table StripedColumnsExample>
                                                <tbody>
                                                <tr>
                                                    <td rowSpan={2}>섬네일</td>
                                                    <td>차시번호</td>
                                                    <td>1</td>
                                                    <td>학습시간</td>
                                                    <td>0시 25분 03초</td>
                                                    <td rowSpan={2}><Button variant="dark">학습하기</Button></td>
                                                </tr>
                                                <tr>
                                                    <td>차시명</td>
                                                    <td>기초미디어 번역이론 1</td>
                                                    <td>진도율</td>
                                                    <td>55.55%</td>
                                                </tr>
                                                </tbody>
                                            </Table>
                                        </Accordion.Body>
                                    </Accordion.Item>
                                </Accordion>
                            </Accordion.Body>
                        </Accordion.Item>
                    </Accordion>
                </Col>
            </Row>
            {showPopup && (
                <div className="popup-overlay">
                    <div className="popup-container">
                        <YoutubeEmbed embedId="Zoo9klcgjRs" saveProgress={savePlaybackProgress} initialPosition={finalPosition}/>
                        <button className="popup-close" onClick={closePopup}>X</button>
                    </div>
                </div>
            )}
        </Container>
    );
}

function YoutubeEmbed({ embedId, saveProgress, initialPosition }) {
    const [player, setPlayer] = useState(null);

    const opts = {
        width: '680',
        height: '450',
        playerVars: {
            rel: 0
        }
    };

    const onReady = (event) => {
        setPlayer(event.target);
        event.target.playVideo();

        const storedPosition = localStorage.getItem('videoPlaybackPosition');
        if (storedPosition !== null) {
            const parsedPosition = parseFloat(storedPosition);
            event.target.seekTo(parsedPosition);
        }
    };

    const onStateChange = (event) => {
        if (event.data === YouTube.PlayerState.PLAYING) {
            const recordInterval = setInterval(() => {
                const currentTime = player.getCurrentTime();
                console.log('Current Time:', currentTime);
                saveProgress(currentTime); // Save the current playback time
            }, 5000);
        }
    };

    const onPlaybackRateChange = (event) => {

    };

    return (
        <YouTube
            videoId={embedId} // 동영상 주소
            opts={opts}
            onReady={onReady}
            onStateChange={onStateChange}
            onPlaybackRateChange={onPlaybackRateChange}
        />
    );
}
