import {useState} from "react";
import axios from "axios";
import {retrieveStoredToken} from "../../auth-action";
import {Container, FormLabel, Row, Form, Button} from "react-bootstrap";
import style from "./ContentInsertPopup.module.css";

export default function ContentInsertPopup() {

    const [file, setFile] = useState(null);
    const [fileSize, setFileSize] = useState('0.00 MB');
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
        let fileSize = ((e.target.files[0].size)/1024/1024).toFixed(2);
        setFileSize(fileSize+ ' MB');
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
                alert('콘텐츠가 성공적으로 업로드되었습니다.');
            })
            .catch(error => {
                console.error('Error uploading file:', error);
                alert('콘텐츠 업로드 중 오류가 발생했습니다.');
            });
    };


    return (

        <>
            <Form.Control type="file" className="mt-3" onChange={handleFileChange} ></Form.Control>
            <p className={style.file_size}>{fileSize}</p>

            <h4 className="mt-5">콘텐츠 정보 입력</h4>
            <p className={style.desc}>아래 정보는 유튜브 채널 업로드 시에 사용됩니다</p>
            <Form.Group controlId="contTitle">
                <Form.Label>콘텐츠 제목</Form.Label>
                <Form.Control type="text" onChange={handleTitleChange} />
            </Form.Group>

            <Form.Group controlId="contTitle" className="mt-3">
                <Form.Label>콘텐츠 설명</Form.Label>
                <Form.Control as="textarea" rows={4} onChange={handleDescriptionChange} />
            </Form.Group>

            <div className="d-flex justify-content-center">
                <Button onClick={submit} className="mt-4">업로드</Button>
            </div>
        </>

    )
}