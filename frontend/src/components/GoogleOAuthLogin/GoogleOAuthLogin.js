import {retrieveStoredToken} from "../../auth-action";
import {Button} from "react-bootstrap";


export default function GoogleOAuthLogin() {

    const handleGoogleLoginClick = () => {
        const googleLoginURL = "https://accounts.google.com/o/oauth2/v2/auth" +
            "?client_id=1035882528994-u5k44rj1aq4lt2gfq3b47jglivlligkp.apps.googleusercontent.com" +
            "&redirect_uri=http://localhost:3000/auth/googleoauth" +
            "&scope=https://www.googleapis.com/auth/youtube.upload" +
            "&response_type=code";

        window.location.href = googleLoginURL;
    };



    return (
        <>
            <Button onClick={handleGoogleLoginClick}>업로드 인증</Button>
        </>
    );

}