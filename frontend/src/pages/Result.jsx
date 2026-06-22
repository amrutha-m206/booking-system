import { useLocation, useNavigate } from "react-router-dom";


function Result() {

    const location = useLocation();
    const navigate = useNavigate();
    const result = location.state;
    console.log(result);

    if (!result) {
        return <h2>No Result Found</h2>;
    }

    return (
        <div>
            <h1>Result</h1>

            <p>Score: {result.score}%</p>
            <p>Correct: {result.correctAnswers}</p>
            <p>Total: {result.totalQuestions}</p>
            <hr />

            <h2>Question Review</h2>

            {result.results.map((item,index)=>(
                <div key={index}>
                    <h3>Question{index+1}</h3>
                    <p>{item.question}</p>
                    <p>Your Answer:{item.userAnswer}</p>
                    <p>Correct Answer:{item.correctAnswer}</p>
                    <p>{item.correct?"Correct":"Incorrect"}</p>
                    <hr />
                    </div>
                    )
                )}

        <button onClick={() => navigate("/history")}>
            View History
        </button>

        <button onClick={()=> navigate("/dashboard")}>Go To Dashboard</button>

         </div>
    );
}
export default Result;