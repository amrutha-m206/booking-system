import {useState} from "react"; //Stores and manages data inside a component
import {useNavigate} from "react-router-dom"; //Used to move between pages (routes)
import {login} from "../services/authService";


function Login(){
    const navigate=useNavigate();
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");

    const handleLogin =async ()=>{
      try{
             const data=await login(email,password);
             localStorage.setItem("token",data.token);
             navigate("/dashboard");
      }
       catch(error){
           alert("Login Failed");
      }
};
return(
  <div>
     <h1>Login</h1>
     <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e)=> setEmail(e.target.value)}
     />
     <br /><br />
     <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e)=> setPassword(e.target.value)}
     />
     <br /><br />
     <button onClick={handleLogin}>Login</button>
   </div>
);
}
export default Login;