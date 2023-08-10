import {useNavigate} from "react-router-dom";




export default function Login() {

/*    const movePage = useNavigate();
    function goJoin() {
        console.log('함수 실행');
        movePage('/user/join');
    }*/

    return (
        <>
            <div className="container tm-mt-big tm-mb-big">
                <div className="row">
                    <div className="col-12 mx-auto tm-login-col">
                        <div className="tm-bg-primary-dark tm-block tm-block-h-auto">
                            <div className="row">
                                <div className="col-12 text-center">
                                    <h2 className="tm-block-title mb-4">Welcome to Dashboard, Login</h2>
                                </div>
                            </div>
                            <div className="row mt-2">
                                <div className="col-12">
                                    <form action="@{/login}" method="post" className="tm-login-form">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <div className="form-group">
                                            <label htmlFor="username">userId</label>
                                            <input
                                                name="username"
                                                type="text"
                                                className="form-control validate"
                                                id="username"
                                                required
                                            />
                                        </div>
                                        <div className="form-group mt-3">
                                            <label htmlFor="password">Password</label>
                                            <input
                                                name="password"
                                                type="password"
                                                className="form-control validate"
                                                id="password"
                                                required
                                            />
                                        </div>
                                        <div className="form-group mt-4">
                                            <input
                                                type="submit"
                                                className="btn btn-primary btn-block text-uppercase"
                                                value="Login"
                                            />

                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

