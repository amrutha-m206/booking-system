import {useState} from "react";
import {useNavigate} from "react-router-dom";
import {uploadDocument} from "../services/documentService";
import "./UploadDocument.css";

function UploadDocument(){
    const[file,setFile]=useState(null);
    const [message, setMessage] = useState("");
    const [error, setError] = useState("");
    const [loading, setLoading] = useState(false);

    const navigate=useNavigate();
    const handleUpload=async()=>{
        if (!file) {
            setError("Please select a file first");
            setMessage("");
            return;
        }
       try{
           setLoading(true);
           setError("");
           setMessage("");
           localStorage.removeItem("quizSubmitted");
         const data=await uploadDocument(file);
         localStorage.setItem("documentId",data.id);
         setMessage("Upload successful");

       } catch (error) {
                setError("Upload failed. Please try again");
       } finally {
            setLoading(false);
       }

};

const handleStartQuiz=()=>{
   const documentId=localStorage.getItem("documentId");
       if (!documentId) {
            setError("Please upload a document first");
            setMessage("");
            return;
       }
    navigate(`/quiz/${documentId}`);

};

return(
  <div className="upload-container">

      <h1 className="upload-title">Upload Document</h1>
      <input className="upload-input"
        type="file"
        accept=".pdf"
        onChange={(e)=> setFile(e.target.files[0])}
      />
      <br />
      <br />
      {message && (
          <p className="success-text">{message}</p>
      )}

      {error && (
          <p className="error-text">{error}</p>
      )}
      <button className="upload-btn" onClick={handleUpload}>
          Upload
      </button>

      <button className="start-btn" onClick={handleStartQuiz}>
          Start Quiz
      </button>
  </div>

);
}
export default UploadDocument;