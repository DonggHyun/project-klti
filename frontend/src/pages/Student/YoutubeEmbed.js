import React from 'react';
import PropTypes from "prop-types";


const YoutubeEmbed = ({embedId}) =>(
    <div className="video-responsive">
        <iframe
            width="780"
            height="450"
            src={`https://www.youtube.com/embed/${embedId}`}
            frameBorder="0"
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
            allowFullScreen
            title="Embedded youtube"
        />
    </div>
);

/*
YoutubeEmbed.PropTypes ={
    embedId:PropTypes.string.isRequired
};
*/

export default YoutubeEmbed;