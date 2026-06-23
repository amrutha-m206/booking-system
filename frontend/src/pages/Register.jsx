import {useState} from "react";
import {useNavigate} from "react-router-dom";
import "./Register.css";
import {startRegistration} from "../services/registerService.js"
import { validateRegister } from "../utils/validate";

function Register(){
    const [name,setName]=useState("");
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [errors, setErrors] = useState({});
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const navigate=useNavigate();

    const handleRegister=async()=>{
        setErrors({});
        setError("");
        const errs = validateRegister(name, email, password);

        if (Object.keys(errs).length > 0) {
            setErrors(errs);
            return;
        }

       try{
         await startRegistration({name,email,password});
         setSuccess("Registration successful");
         setError("");
         setErrors({});

         setTimeout(() => {
             navigate("/");
         }, 1000);

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
            {success && <p className="success-text">{success}</p>}
            {error && <p className="error-text">{error}</p>}
            {errors.name && <p className="error-text">{errors.name}</p>}
            {errors.email && <p className="error-text">{errors.email}</p>}
            {errors.password && <p className="error-text">{errors.password}</p>}

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