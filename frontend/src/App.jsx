import {BrowserRouter,Routes,Route} from "react-router-dom";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./components/ProtectedRoute";
import UploadDocument from "./pages/UploadDocument";

function App(){
    return(
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path="/dashboard" element={
                                     <ProtectedRoute>
                                     <Dashboard/>
                                     </ProtectedRoute>
                                  }/>
                <Route path="/upload" element={
                                     <ProtectedRoute>
                                         <UploadDocument/>
                                         </ProtectedRoute>
                                }/>
            </Routes>
         </BrowserRouter>
        );
}
export default App;