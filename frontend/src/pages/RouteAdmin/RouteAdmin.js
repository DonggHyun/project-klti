import {Route, useNavigate} from "react-router-dom";
import axios from "axios";
import {useState} from "react";
import {retrieveStoredToken} from "../../auth-action";
import Admin from "../Admin/Admin";
import NotFound from "../NotFound/NotFound";


export default function RouteAdmin() {

    const token = retrieveStoredToken().token;
    const [role, setRole] = useState('MEMBER');

    function roleTest() {
        axios.get('http://localhost:8080/api/auth/role', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(response => {
                console.log('response', response);
                setRole(response.data);
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }

    roleTest();

    if(role === 'ADMIN') {
        return (
            <Admin />
        )
    } else {
        return (
            <NotFound />
        )
    }
}

