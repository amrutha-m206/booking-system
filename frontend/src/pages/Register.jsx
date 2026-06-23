import {useState} from "react";
import {useNavigate} from "react-router-dom";
import "./Register.css";
import {startRegistration} from "../services/registerService.js"

function Register(){
    const [name,setName]=useState("");
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");

    const navigate=useNavigate();

    const handleRegister=async()=>{

        if(!name||!email||!password){
           alert("All fields are required");
           return;
        }

       try{
         await startRegistration({name,email,password});
         alert("Registration successful");
         navigate("/");

       } catch(err){
            console.error(err);
            const message = err?.response?.data?.message || "Registration failed";
            alert(message);
       }

};
return (
        <div className="register-container">

            <h2 className="register-title">
                Register
            </h2>

            <input
                className="register-input"
                placeholder="Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
            />

            <input
                className="register-input"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                className="register-input"
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button
                className="register-btn"
                onClick={handleRegister}
            >
                Register
            </button>

        </div>
    );

}
export default Register;