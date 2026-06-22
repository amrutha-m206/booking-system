import {useEffect,useState} from "react";
import {useParams} from "react-router-dom";
import {getQuiz} from "../services/quizService";

function Quiz(){
   const {documentId}=useParams();
   const[questions,setQuestions]=useState([]);

   useEffect(()=>{
       const loadQuiz=async()=>{

          try{
            const data=await getQuiz(documentId);
            const parsedQuestions=JSON.parse(data.questions);
            setQuestions(parsedQuestions);
          } catch(error){
              console.error(error);
          }

      };
  loadQuiz();
   },[documentId]);


return (
    <div>
        <h1>Quiz</h1>
          {questions.map((q,index)=>(
              <div key={index}>
                  <h3>
                      {index+1}.{q.question}
                  </h3>

                  {q.options.map((option,optionIndex)=>(
                      <div key={optionIndex}>
                         <input
                             type="radio"
                             name={`question=${index}`}
                         />
                         {option}
                      </div>


                  ))}
              <br />
              </div>
          ))}
    </div>

  );
}
export default Quiz;