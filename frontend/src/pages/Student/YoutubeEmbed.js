/*
import React, { useState } from 'react';
import YouTube from 'react-youtube';


const YoutubeEmbed = ({embedId}) => {
    const [player, setPlayer] = useState(null);
    //최종재생시점
    const [finalPosition, setFinalPosition] = useState(null);

    const opts = {
        width: '680',
        height: '450',
        playerVars: {
            rel: 0
        }
    };

    const onReady = (event) => {
        setPlayer(event.target);
        event.target.playVideo(); // 재생 시작

        if (finalPosition !== null) {
            console.log('Seeking to position:', finalPosition);
            player.seekTo(finalPosition); // finalPosition에서 재생시작
        }
    };

    const onStateChange = (event) => {
        if (event.data === YouTube.PlayerState.PLAYING) {
            const recordInterval = setInterval(() => {
                const currentTime = player.getCurrentTime();
                console.log('Current Time:', currentTime);
                setFinalPosition(currentTime);
            }, 5000); // 5초마다 현재 재생 시간을 출력

            // 재생이 멈추면 타이머 해제
            event.target.addEventListener('onStateChange', (event) => {
                if (event.data !== YouTube.PlayerState.PLAYING) {
                    clearInterval(recordInterval);
                }
            });
        }
    }

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
export default YoutubeEmbed;*/
