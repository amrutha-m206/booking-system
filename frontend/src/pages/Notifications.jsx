import {useEffect,useState} from "react";
import { useNavigate } from "react-router-dom";
import {getNotifications} from "../services/notificationService";
import "./Notifications.css";

function Notifications(){
    const [notifications,setNotifications]=useState([]);
    const navigate = useNavigate();

    useEffect(()=>{
        const loadNotifications=async()=>{
           try{
              const data=await getNotifications();
              setNotifications(data);
           } catch(err){
             console.error(err);
           }

        };
        loadNotifications();

    },[]);

    return (
     <div className="notifications-container">
         <h1 className="notifications-title">Notifications</h1>
       {notifications.length === 0 && (
                <p className="no-notifications">
                    No notifications yet
                </p>
       )}
       {notifications.map((n)=>(
             <div key={n.id} className="notification-card">
                 <p className="notification-message">{n.message}</p>
        <small className="notification-time">{new Date(n.createdAt).toLocaleString()}</small>

             </div>
       ))}
       <button className="notification-btn" onClick={() => navigate("/dashboard")}>Go To Dashboard</button>
       <button className="logout-btn"
                          onClick={() => {
                              localStorage.removeItem("token");
                              localStorage.removeItem("documentId");
                              navigate("/");
                          }}>
                          Logout
       </button>
     </div>
    );
}
export default Notifications;