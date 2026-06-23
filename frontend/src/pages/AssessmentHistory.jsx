import {useEffect,useState} from "react";
import {getAssessmentHistory} from "../services/assessmentService";
import { useNavigate } from "react-router-dom";
import "./AssessmentHistory.css";

function AssessmentHistory(){

      const[history,setHistory]=useState([]);
      const navigate = useNavigate();
      useEffect(()=> {
         const loadHistory=async()=>{
             try{
               const data=await getAssessmentHistory();
               setHistory(data);

             } catch(err){
               console.error(err);
             }
         };
       loadHistory();
      },[]);

return(  <div className="history-container">
                   <h1 className="history-title">
                       Assessment History
                   </h1>

                   {history.length === 0 ? (
                       <p className="empty-history">
                           No assessments found
                       </p>
                   ) : (
                       <div className="questions-container">
                           {history.map((item) => (
                               <div key={item.assessmentId} className="history-card">
                                   <p className="history-info">Quiz ID: {item.quizId}</p>
                                   <p className="score">Score: {item.scorePercentage}%</p>
                                   <p className="history-info">Correct: {item.correctAnswers}</p>
                                   <p className="history-info">Wrong: {item.wrongAnswers}</p>
                                   <p className="history-info">Total: {item.totalQuestions}</p>
                                   <p className="history-info">Submitted:{" "}{new Date(item.submittedAt).toLocaleString()}</p>
                               </div>
                           ))}
                       </div>
                   )}

                   <button className="dashboard-btn" onClick={() => navigate("/dashboard")}>
                       Go To Dashboard
                   </button>
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
export default AssessmentHistory;
