import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import Test from "./components/Test/Test";
import Example from "./components/Example/Example";
import axios from "axios";





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

    useEffect(() => {
        axios.get('/api/loginview')
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, []);


    useEffect(() => {
        if (data) {
            console.log('Received data:', data);
        }
    }, [data]);

  return (
    <div className="App">
        <Test></Test>
        <Example></Example>
        {data}
    </div>
  );
}

export default App;
