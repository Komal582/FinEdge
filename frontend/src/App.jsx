


import Login from './pages/Login';
import { BrowserRouter,  Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import ProtectedRoute from './components/ProtectedRoute';
import Transaction from './pages/Transaction';
import Expense from './pages/Expense';
import Income from './pages/Income';
import Settings from './pages/Settings';
import Reports from './pages/Reports';
import IncomeHistory from './pages/IncomeHistory';
import ExpenseHistory from './pages/ExpenseHistory';
import Logout from './pages/Logout';
import Signup from './pages/Signup';
import ChangePassword from './pages/changePassword';


function App() {


  return (
    <>
     <BrowserRouter>
      <Routes>
          <Route path="/" element={<Login/>} />
          <Route path="/dashboard" element={
            <ProtectedRoute>
              <Dashboard/>
            </ProtectedRoute>
            }/>
          <Route path="/transactions" element={<Transaction/>}/>
          <Route path="/expense" element={<Expense/>}/>
          <Route path="/income" element={<Income/>}/>
          <Route path="/settings" element={<Settings/>}/>
          <Route path="/reports" element={<Reports/>}/>
          <Route path="/income/history" element={<IncomeHistory/>}/>
          <Route path="/expense/history" element={<ExpenseHistory/>}/>
           <Route path="/logout" element={<Logout/>}/>
           <Route path="/signup" element={<Signup/>}/>
           <Route path="/changePassword" element={<ChangePassword/>}/>
      </Routes>
     </BrowserRouter>
    
    </> 
  
  )
}

export default App
