export function validateEmail(email){
   const regex= /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
   return regex.test(email);
}

export function validateLogin(email,password){
     const errors={};

     if(!email){
       errors.email="Email is required";
     }
     else if(!validateEmail(email)){
       errors.email="Invalid email format";
     }

     if(!password){
      errors.password="Password is required";
     }
     else if(password.length<4){
     errors.password="Password must be at least 4 characters";
     }
     return errors;
}

export function validateRegister(name,email,password){
     const errors={};

     if(!name){
     errors.name="Name is required";
     }

     const loginErrors=validateLogin(email,password);

     return {...errors,...loginErrors};
}