import {useEffect,useState} from "react";
import {getAnalytics} from "../services/analyticsService";
import {useNavigate} from "react-router-dom";

function Analytics(){

   const [analytics,setAnalytics]=useState(null);
    const navigate=useNavigate();

   useEffect(()=>{
          const loadAnalytics=async()=>{
              try{
                 const data=await getAnalytics();
                 setAnalytics(data);
              } catch(err){
                   console.log(err);
              }
          };
             loadAnalytics();
       },[]);
    if(!analytics){
       return <h2>Loading...</h2>
    }
return(
    <div>
        <h1>Analytics</h1>
        <p>Total Attempts: {analytics.totalAttempts}</p>
        <p>Average Score: {analytics.avgScore?.toFixed(2)}</p>
        <p>Best Score: {analytics.bestScore}</p>
        <p>Worst Score: {analytics.worstScore}</p>
        <button onClick={()=> navigate("/dashboard")}>Go To Dashboard</button>

    </div>
    );
}
export default Analytics;