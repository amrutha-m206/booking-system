import {useState} from "react"; //Stores and manages data inside a component
import {useNavigate} from "react-router-dom"; //Used to move between pages (routes)
import {login} from "../services/authService";
import "./Login.css";
import { validateLogin } from "../utils/validate";

function Login(){
    const navigate=useNavigate();
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");
    const [error, setError] = useState("");
    const [errors, setErrors] = useState({});

  const handleLogin =async ()=>{
        setErrors({});
        setError("");

    const errs = validateLogin(email, password);

    if (Object.keys(errs).length > 0) {
        setErrors(errs);
        return;
    }
      try{
             const data=await login(email,password);
             localStorage.setItem("token",data.token);
             navigate("/dashboard");
      }
       catch(error){
            setError("Invalid email or password");
      }
};
return(
  <div className="login-container">
     <h1 className="login-title">Login</h1>
          {error && (
              <p className="error-text">
                  {error}
              </p>
          )}

      {errors.email && <p className="error-text">{errors.email}</p>}

      {errors.password && <p className="error-text">{errors.password}</p>}

     <input className="login-input"
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e)=> setEmail(e.target.value)}
     />
     <br /><br />

     <input className="login-input"
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e)=> setPassword(e.target.value)}
     />
     <br /><br />
     <button className="login-btn" onClick={handleLogin}>Login</button>
          <p style={{ textAlign: "center", marginTop: "15px" }}>
                 Don't have an account?{" "}
                 <span
                     onClick={() => navigate("/register")}
                     style={{ color: "#4338ca", cursor: "pointer", fontWeight: "bold" }}
                 >
                     Register here
                 </span>
             </p>
   </div>
);
}
export default Login;