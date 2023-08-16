import './Header.module.css';
import {useNavigate} from "react-router-dom";
import {retrieveStoredToken} from "../../auth-action";
import {useState} from "react";
import axios from "axios";
import HeaderLinks from "../HeaderLinks/HeaderLinks";



export default function Header() {

    const movePage = useNavigate();

    function goHome() {
        movePage('/');
    }




    return (
        <>
            <div>
                <nav className="navbar navbar-expand-xl">
                    <div className="container h-100">
                        <span className="navbar-brand" onClick={goHome}>
                            <h1 className="tm-site-title mb-0">Home</h1>
                        </span>
                        <button
                            className="navbar-toggler ml-auto mr-0"
                            type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            <i className="fas fa-bars tm-nav-icon"></i>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <HeaderLinks />
                        </div>
                    </div>
                </nav>
            </div>
        </>
    )
}

