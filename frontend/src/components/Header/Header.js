import {useNavigate} from "react-router-dom";




export default function Header() {

    const movePage = useNavigate();

    function goHome() {
        movePage('/');
    }
    function goJoin() {
        console.log('함수 실행');
        movePage('/user/join');
    }

    return (
        <>
            <div>
                <nav className="navbar navbar-expand-xl">
                    <div className="container h-100">
                        <a className="navbar-brand" onClick={goHome}>
                            <h1 className="tm-site-title mb-0">Home</h1>
                        </a>
                        <button
                            className="navbar-toggler ml-auto mr-0"
                            type="button"
                            data-toggle="collapse"
                            data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent"
                            aria-expanded="false"
                            aria-label="Toggle navigation"
                        >
                            <i className="fas fa-bars tm-nav-icon"></i>
                        </button>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mx-auto h-100">
                                <li className="nav-item">
                                    <a className="nav-link" href="index.html">
                                        <i className="fas fa-tachometer-alt"></i> Dashboard
                                        <span className="sr-only">(current)</span>
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" onClick={goJoin}>
                                        <i className="far fa-file-alt"></i> 회원가입
                                    </a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="products.html">
                                        <i className="fas fa-shopping-cart"></i> Products
                                    </a>
                                </li>

                                <li className="nav-item">
                                    <a className="nav-link" href="accounts.html">
                                        <i className="far fa-user"></i> Accounts
                                    </a>
                                </li>
                                <li className="nav-item dropdown">
                                    <a
                                        className="nav-link dropdown-toggle"
                                        href="#"
                                        id="navbarDropdown"
                                        role="button"
                                        data-toggle="dropdown"
                                        aria-haspopup="true"
                                        aria-expanded="false"
                                    >
                                        <i className="fas fa-cog"></i>
                                        <span> Settings <i className="fas fa-angle-down"></i> </span>
                                    </a>
                                    <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a className="dropdown-item" href="#">Profile</a>
                                        <a className="dropdown-item" href="#">Billing</a>
                                        <a className="dropdown-item" href="#">Customize</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </>
    )
}

