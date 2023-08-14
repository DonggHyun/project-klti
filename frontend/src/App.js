import logo from './logo.svg';
import './App.css';
import './common/bootstrap.min.css'
import './common/fontawesome/fontawesome.min.css'
import './common/templatemo-style.css'
import {useEffect, useState} from "react";
import Test from "./components/Test/Test";
import Example from "./components/Example/Example";
import axios from "axios";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Join from "./pages/Join/Join";
import Login from "./pages/Login/Login";
import NotFound from "./pages/NotFound/NotFound";
import Header from "./components/Header/Header";
import Class from "./pages/Class/Class";
import Admin from "./pages/Admin/Admin";
import jwt from "jsonwebtoken";


function App() {

    const [message, setMessage] = useState([]);

    const [data, setData] = useState('');

    const [userRole, setUserRole] = useState(""); // 유저의 역할 정보를 저장할 state

    return (
        <>
            <BrowserRouter>
                <Header></Header>
                <Routes>
                    <Route path={"/"} element={<Login/>}></Route>
                    <Route path={"/user/join"} element={<Join/>}></Route>
                    <Route path={"/class"} element={<Class/>}></Route>
                    <Route path={"/admin"} element={<Admin/>}></Route>
                    <Route path={"*"} element={<NotFound/>}></Route>
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;
