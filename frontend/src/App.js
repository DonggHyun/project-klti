import logo from './logo.svg';
import './App.css';
import './common/bootstrap.min.css'
import './common/fontawesome/fontawesome.min.css'
/*import './common/templatemo-style.css'*/
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
import ContentManagement from "./components/ContentManagement/ContentManagement";
import LectureManagement from "./components/LectureManagement/LectureManagement";




function App() {

  const [message, setMessage]=useState([]);
  /*useEffect(()=>{
    fetch("/loginview", { headers:
      {'Accept':'application/json',
       'Content-Type':'application/json'}
      }).then((res)=>{
          console.log(res);
          return res.json();
          //return res.text();
        })
        .then((data)=>{
            console.log('data', data);
          setMessage(data);
        })
        .catch(error => {
            console.error('Fetch error:', error);
        });
  },[]);*/

    const [selectedMenu, setSelectedMenu] = useState('default');

    const renderClassComponent = () => {

    }

    const renderAdminComponent = () => {
        switch (selectedMenu) {
            case 'ContentManagement':
                return <ContentManagement />;
            case 'LectureManagement':
                return <LectureManagement />;
            default:
                return <ContentManagement />;
        }
    };


  return (
    <>
        <BrowserRouter>
            <Header setSelectedMenu={setSelectedMenu}></Header>
            <Routes>
                <Route path={"/"} element={<Login />}></Route>
                <Route path={"/user/join"} element={<Join />}></Route>
                <Route path={"/class"} element={<Class renderClassComponent={renderClassComponent} />}></Route>
                <Route path={"/admin"} element={<Admin renderAdminComponent={renderAdminComponent} />}></Route>
                <Route path={"*"} element={<NotFound />}></Route>
            </Routes>
        </BrowserRouter>
    </>
  );
}

export default App;
