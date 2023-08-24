import {useState} from "react";
import axios from "axios";
import {retrieveStoredToken} from "../../auth-action";

export default function ContentInsertPopup() {

    const [selectedFile, setSelectedFile] = useState(null);

    const handleFileChange = (event) => {
        setSelectedFile(event.target.files[0]);
    };

    const handleUpload = () => {
        const formData = new FormData();
        formData.append('file', selectedFile);

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
            <input type="file" onChange={handleFileChange} />
            <button onClick={handleUpload}>Upload</button>
        </>

    )
}