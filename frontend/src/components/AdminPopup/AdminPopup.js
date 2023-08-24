import React from "react";
import './ContentInsertPopup.css';

export default function ContentInsertPopup({closePopup}) {


    return (
        <div className="popup-overlay">
            <div className="popup-container">
                <div className="popup-inner">
                    <button className="popup-close" onClick={closePopup}>X</button>
                </div>
            </div>
        </div>
    )
}