import {useState} from "react";
import axios from "axios";
import {retrieveStoredToken} from "../../auth-action";
import {Container, FormLabel, Row, Form, Button} from "react-bootstrap";

export default function ContentInsertPopup() {

    const [file, setFile] = useState(null);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleTitleChange = (e) => {
        console.log(e.target.value);
        setTitle(e.target.value);
    }

    const handleDescriptionChange = (e) => {
        console.log(e.target.value);
        setDescription(e.target.value);
    }

    const submit = () => {
        const formData = new FormData();
        formData.append('file', file);
        formData.append('title', title);
        formData.append('description', description);

        axios.post('/api/contents', formData, {
            headers: {
                'Authorization': 'Bearer ' + retrieveStoredToken().token
            }
        })
            .then(response => {
                console.log('File uploaded successfully:', response.data);
            })
            .catch(error => {
                console.error('Error uploading file:', error);
            });
    };


    return (

        <>
            <Form.Control type="file" onChange={handleFileChange} ></Form.Control>
            <Form.Control type="text" onChange={handleTitleChange} ></Form.Control>
            <Form.Control as="textarea" rows={5} onChange={handleDescriptionChange} ></Form.Control>

            <button onClick={submit}>Upload</button>
        </>

    )
}