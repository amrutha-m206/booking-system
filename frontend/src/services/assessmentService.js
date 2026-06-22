import api from "./api";

export const submitAssessment=async(payload)=>{
 const response=await api.post(
 "/assessment/submit",
 payload
 );
 return response.data;
};

export const getAssessmentHistory=async()=>{
 const response=await api.get(
   "/assessment/history"
 );
 return response.data;
};