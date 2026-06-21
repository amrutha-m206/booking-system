import axios from "axios";

const api=axios.create({
    baseURL:"http://localhost:8080"
});
//It adds the JWT token from localStorage into every API request header before sending it to the backend.
api.interceptors.request.use(
    (config)=>{
    const token=localStorage.getItem("token")
      if(token){
      config.headers.Authorization=`Bearer ${token}`;
      }
      return config;
    },
    (error)=>{
    return Promise.reject(error);
    }
)
export default api;