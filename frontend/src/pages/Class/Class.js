import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {retrieveStoredToken} from '../../auth-action';
import './class.css';


export default function Class({renderClassComponent}){
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
    if(role === 'MEMBER') {
        return (
            <>
                { renderClassComponent() }
            </>
        )
    } else {
        return (
            <>
            </>
        )
    }

}
