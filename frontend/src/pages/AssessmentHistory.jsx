import {useEffect,useState} from "react";
import {getAssessmentHistory} from "../services/assessmentService";
import { useNavigate } from "react-router-dom";

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

return(
<div>
    <h1>Assessment History</h1>
    {history.map((item)=>(
        <div key={item.assessmentId}>
                <p>Assessment ID: {item.assessmentId}</p>
                <p>Quiz ID: {item.quizId}</p>
                <p>Score: {item.scorePercentage}%</p>
                <p>Correct: {item.correctAnswers}</p>
                <p>Wrong: {item.wrongAnswers}</p>
                <p>Total: {item.totalQuestions}</p>
                <p>Submitted: {new Date(item.submittedAt).toLocaleString()}</p>
            <hr />
         </div>
         ))}
  <button onClick={()=> navigate("/dashboard")}>Go To Dashboard</button>


    </div>
    );
}
export default AssessmentHistory;
