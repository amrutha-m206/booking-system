import {useNavigate} from "react-router-dom";

function Dashboard(){
    const navigate=useNavigate();

    return(
       <div>
       <h1>Dashboard</h1>
       <button onClick={()=>navigate("/upload")}>Upload Document</button>
       <button onClick={()=>navigate("/history")}>Assessment History</button>
       <button onClick={()=>navigate("/analytics")}>Analytics</button>
       <button onClick={()=>navigate("/notifications")}>Notifications</button>

       </div>
    );
 }
export default Dashboard;