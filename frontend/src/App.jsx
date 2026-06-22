import {BrowserRouter,Routes,Route} from "react-router-dom";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./components/ProtectedRoute";
import UploadDocument from "./pages/UploadDocument";
import Quiz from "./pages/Quiz";
import Result from "./pages/Result";
import AssessmentHistory from "./pages/AssessmentHistory";
import Analytics from "./pages/Analytics";
import Notifications from "./pages/Notifications";

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
                <Route path="/quiz/:documentId" element={
                                       <ProtectedRoute>
                                           <Quiz/>
                                       </ProtectedRoute>

                                  }/>
                <Route path="/result/:assessmentId" element={
                                       <ProtectedRoute>
                                           <Result/>
                                       </ProtectedRoute>
                                  }/>
                <Route path="/history" element={
                                        <ProtectedRoute>
                                           <AssessmentHistory/>
                                        </ProtectedRoute>
                                   }/>
                <Route path="/analytics" element={
                                        <ProtectedRoute>
                                            <Analytics />
                                        </ProtectedRoute>
                                   }/>
                <Route path="/notifications" element={
                                       <ProtectedRoute>
                                         <Notifications />
                                       </ProtectedRoute>
                                   }/>
            </Routes>
         </BrowserRouter>
        );
}
export default App;