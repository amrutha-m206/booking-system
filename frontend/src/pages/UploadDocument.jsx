import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {uploadDocument} from "../services/documentService";

function UploadDocument(){
    const[file,setFile]=useState(null);
    const navigate=useNavigate();
    const handleUpload=async()=>{
       try{
           localStorage.removeItem("quizSubmitted");
         const data=await uploadDocument(file);
         localStorage.setItem("documentId",data.id);
         alert("Upload Successfully");
//          console.log(data);
       } catch(error){
           alert("Upload Failed");
       }

};

const handleStartQuiz=()=>{
   const documentId=localStorage.getItem("documentId");
       if (!documentId) {

           alert("Please upload a document first");

           return;
       }
    navigate(`/quiz/${documentId}`);

};

return(
  <div>
      <h1>Upload Document</h1>
      <input
        type="file"
        accept=".pdf"
        onChange={(e)=> setFile(e.target.files[0])}
      />
      <br />
      <br />
      <button onClick={handleUpload}>Upload</button>
      <button onClick={handleStartQuiz}>Start Quiz</button>
  </div>

);
}
export default UploadDocument;