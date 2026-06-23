import {useNavigate} from "react-router-dom";
import "./Dashboard.css";

function Dashboard(){
    const navigate=useNavigate();

    return(
       <div className="dashboard-container">

            <h1 className="dashboard-title">Welcome to AutoAssess</h1>

            <div className="dashboard-grid">

                <div className="dashboard-card" onClick={() => navigate("/upload")}>
                    <h3>Upload Document</h3>
                    <p>Generate quiz from your file</p>
                </div>

                <div className="dashboard-card" onClick={() => navigate("/history")}>
                    <h3>History</h3>
                    <p>View past assessments</p>
                </div>

                <div className="dashboard-card" onClick={() => navigate("/analytics")}>
                    <h3>Analytics</h3>
                    <p>View performance insights</p>
                </div>

                <div className="dashboard-card" onClick={() => navigate("/notifications")}>
                     <h3>Notifications</h3>
                     <p>View your notifications</p>
                </div>

            </div>

            <button className="logout-btn-dashboard"
                onClick={() => {
                    localStorage.removeItem("token");
                    navigate("/");
                }}
            >
                Logout
            </button>

        </div>

    );
 }
export default Dashboard;