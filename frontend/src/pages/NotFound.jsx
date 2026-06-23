import "./NotFound.css";
import { useNavigate } from "react-router-dom";

function NotFound() {
    const navigate = useNavigate();

    return (
        <div className="not-found-container">
            <h1 className="not-found-code">404</h1>

            <h2 className="not-found-title">
                Page Not Found
            </h2>

            <p className="not-found-text">
                Oops! The page you are looking for doesn't exist.
            </p>

            <button
                className="not-found-btn"
                onClick={() => navigate("/dashboard")}
            >
                Go To Dashboard
            </button>
        </div>
    );
}
export default NotFound;