import {useState} from "react";
import {uploadDocument} from "../services/documentService";

function UploadDocument(){
    const[file,setFile]=useState(null);
    const handleUpload=async()=>{
       try{
         const data=await uploadDocument(file);
         alert("Upload Successfully");
         console.log(data);

       } catch(error){
           alert("Upload Failed");
       }

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
  </div>

);
}
export default UploadDocument;