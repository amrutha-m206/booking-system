import {useEffect,useState} from "react";
import {getNotifications} from "../services/notificationService";

function Notifications(){
    const [notifications,setNotifications]=useState([]);

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
     <div>
         <h1>Notifications</h1>
         {notifications.length===0 && (<p>No notifications yet</p>)}
         {notifications.map((n)=>(
             <div key={n.id}>
                 <p>{n.message}</p>
                 <small>{n.createdAt}</small>
                 <hr/>
             </div>
             ))}
     </div>
    );
}
export default Notifications;