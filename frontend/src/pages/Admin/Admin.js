import {Route, useNavigate} from "react-router-dom";
import axios from "axios";
import {useState} from "react";
import {retrieveStoredToken} from "../../auth-action";
import NotFound from "../NotFound/NotFound";
import ContentManagement from "../../components/ContentManagement/ContentManagement";


export default function Admin({ renderAdminComponent }) {

    const token = retrieveStoredToken().token;
    const [role, setRole] = useState('MEMBER');

    function roleTest() {
        axios.get('http://localhost:8080/api/auth/role', {
            headers: {
                'Authorization': 'Bearer ' + token
            }
        })
            .then(response => {
                setRole(response.data);
            })
            .catch(error => {
                console.error('Error :', error);
            });
    }

    roleTest();

    if(role === 'ADMIN') {
        return (
            <>
                { renderAdminComponent() }
            </>
        )
    } else {
        return (
            <></>
        )
    }
}

