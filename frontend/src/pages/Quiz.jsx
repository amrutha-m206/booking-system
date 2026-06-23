import {useEffect,useState} from "react";
import {useParams} from "react-router-dom";
import {getQuiz} from "../services/quizService";
import {submitAssessment} from "../services/assessmentService";
import {useNavigate} from "react-router-dom";
import "./Quiz.css";

function Quiz(){
   const {documentId}=useParams();
   const[questions,setQuestions]=useState([]);
   const[answers,setAnswers]=useState([]);
   const [quizId, setQuizId] = useState(null);

   const navigate = useNavigate();
   useEffect(()=>{
       const loadQuiz=async()=>{
           if (!documentId) {
               alert("Please upload a document first");
               return;
           }
          if(localStorage.getItem("quizSubmitted")==="true"){
              navigate("/dashboard");
              return;
          }
          try{
            const data=await getQuiz(documentId);
            setQuizId(data.quizId);
            const parsedQuestions=JSON.parse(data.questions);
            setQuestions(parsedQuestions);
          } catch(error){
              console.error(error);
          }

      };
  loadQuiz();
   },[documentId]);

useEffect(() => {

    const handlePopState = async () => {

        if (!quizId) return;

        const wrongAnswers = questions.map(() => "Z");

        try {

            await submitAssessment({
                quizId: quizId,
                answers: wrongAnswers
            });

        } catch (err) {
            console.error(err);
        }

        localStorage.removeItem("token");
        localStorage.removeItem("documentId");

        navigate("/");
    };

    window.addEventListener("popstate", handlePopState);

    return () => {
        window.removeEventListener("popstate", handlePopState);
    };

}, [quizId, questions, navigate]);


const handleSubmit=async()=>{
      try{
             for(let i = 0; i < questions.length; i++) {
                if(!answers[i]) {
                    alert(`Please answer Question ${i + 1}`);
                    return;
                }
             }
             const payload={
                    quizId:quizId,
                    answers:answers
                 }
             const result=await submitAssessment(payload);
             localStorage.setItem("quizSubmitted","true");
             localStorage.removeItem("documentId");
             navigate(`/result/${result.assessmentId}`,{
                 state:result
    //              replace: true

                 });

          } catch(error){
              console.error(error);
              alert("Submission Failed");
          }

};

return (
    <div className="quiz-container">
        <h1 className="quiz-title">Quiz</h1>
          {questions.map((q,index)=>(
              <div key={index} className="questions-card">
                  <h3 className="question-text">
                      {index+1}.{q.question}
                  </h3>

                  {q.options.map((option,optionIndex)=>(
                  <label key={optionIndex} className="option">
                         <input
                             type="radio"
                             name={`question-${index}`}
                             value={String.fromCharCode(65+optionIndex)}
                             onChange={(e)=>{
                                    const updatedAnswers=[...answers];
                                    updatedAnswers[index]=e.target.value;
                                    setAnswers(updatedAnswers);
                                 }}
                         />
                         {option}
                      </label>
                  ))}
              <br />
              </div>
          ))}
          <button className="submit-btn" onClick={handleSubmit}>
              Submit Quiz
          </button>
    </div>

  );
}
export default Quiz;