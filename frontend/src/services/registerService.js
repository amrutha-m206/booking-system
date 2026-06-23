import api from "./api";

export const startRegistration=async(req)=>{
    const response=await api.post("/auth/register",req);
    return response.data;

}


















