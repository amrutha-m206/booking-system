import { useLocation, useNavigate } from "react-router-dom";
import "./Result.css";

function Result() {

    const location = useLocation();
    const navigate = useNavigate();
    const result = location.state;
    console.log(result);

    if (!result) {
        return <h2>No Result Found</h2>;
    }

    return (
        <div className="result-container">
            <h1 className="result-title">Result</h1>

        <div className="summary-box">
            <p>Score: {result.score}%</p>
            <p>Correct: {result.correctAnswers}</p>
            <p>Total: {result.totalQuestions}</p>
         </div>

            <h2 style={{ fontWeight: "bold", color: "#f9a8d4" }}>
                Question Review
            </h2>
            {result.results.map((item,index)=>(
                <div className="question-card" key={index}>
                    <h3 className="question-title">Question {index+1}</h3>
                    <p className="answer">{item.question}</p>
                    <p className="answer">Your Answer : <strong>{item.userAnswer}</strong></p>
                    <p className="answer"> Correct Answer : <strong>{item.correctAnswer}</strong></p>
                    <p className={item.correct ? "correct" : "incorrect"}>{item.correct ? "Correct" : "Incorrect"}</p>
                    </div>
                    )
                )}
        <div className="button-group">
        <button className="btn btn-history" onClick={() => navigate("/history")}>
            View History
        </button>

        <button className="btn btn-dashboard" onClick={()=> navigate("/dashboard")}>Go To Dashboard</button>
         </div>
         </div>
    );
}
export default Result;