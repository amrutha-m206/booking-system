import {useNavigate} from "react-router-dom";

function Dashboard(){
    const navigate=useNavigate();

    return(
       <div>
       <h1>Dashboard</h1>
       <button onClick={()=>navigate("/upload")}>Upload Document</button>
       </div>
    );
 }
export default Dashboard;