import api from "./api";


export const getQuiz=async(documentId)=>{
    const response=await api.get(
       `/quiz/${documentId}`
    );
    return response.data;
};