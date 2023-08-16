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
import RouteAdmin from "./pages/RouteAdmin/RouteAdmin";
/*import 'bootstrap/dist/css/bootstrap.min.css';*/






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

    const [data, setData] = useState('');

    /*useEffect(() => {
        axios.get('/api/test')
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);*/


    /*useEffect(() => {
        if (data) {
            console.log('Received data:', data);
        }
    }, [data]);*/

  return (
    <>
        <BrowserRouter>
            <Header></Header>
            <Routes>
                <Route path={"/"} element={<Login />}></Route>
                <Route path={"/user/join"} element={<Join />}></Route>
                <Route path={"/class"} element={<Class />}></Route>
                <Route path={"/admin"} element={<RouteAdmin />}></Route>
                <Route path={"*"} element={<NotFound />}></Route>
            </Routes>
        </BrowserRouter>
    </>
  );
}

export default App;
