import {useEffect,useState} from "react";
import {getAnalytics} from "../services/analyticsService";
import {useNavigate} from "react-router-dom";
import "./Analytics.css";
import {
    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,
    ResponsiveContainer
} from "recharts";

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
   
   if (!analytics) {
     return (
       <div className="no-analytics">
         <h2>No analytics available yet. Take a quiz to see insights.</h2>
       </div>
     );
   }


    return (
        <div className="analytics-container">

            <h1 className="analytics-title">
                Analytics Dashboard
            </h1>

            <div className="chart-section">

                <div className="bar-row">
                    <div className="bar-label">
                        Average Score ({analytics.avgScore?.toFixed(2)}%)
                    </div>

                    <div className="bar">
                        <div
                            className="fill"
                            style={{
                                width: `${analytics.avgScore}%`
                            }}
                        />
                    </div>
                </div>

                <div className="bar-row">
                    <div className="bar-label">
                        Best Score ({analytics.bestScore}%)
                    </div>

                    <div className="bar">
                        <div
                            className="fill"
                            style={{
                                width: `${analytics.bestScore}%`
                            }}
                        />
                    </div>
                </div>

                <div className="bar-row">
                    <div className="bar-label">
                        Worst Score ({analytics.worstScore}%)
                    </div>

                    <div className="bar">
                        <div
                            className="fill"
                            style={{
                                width: `${analytics.worstScore}%`
                            }}
                        />
                    </div>
                </div>

            </div>

            <div className="analytics-stats">

                <p>
                    Total Attempts:
                    <strong> {analytics.totalAttempts}</strong>
                </p>

                <p>
                    Average Score:
                    <strong> {analytics.avgScore?.toFixed(2)}%</strong>
                </p>

                <p>
                    Best Score:
                    <strong> {analytics.bestScore}%</strong>
                </p>

                <p>
                    Worst Score:
                    <strong> {analytics.worstScore}%</strong>
                </p>

            </div>

            <div className="analytics-actions">

                <button
                    className="dashboard-btn"
                    onClick={() => navigate("/dashboard")}
                >
                    Go To Dashboard
                </button>

                <button
                    className="logout-btn"
                    onClick={() => {
                        localStorage.removeItem("token");
                        localStorage.removeItem("documentId");
                        navigate("/login");
                    }}
                >
                    Logout
                </button>

            </div>

        </div>
    );
}
export default Analytics;