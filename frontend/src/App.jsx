import Login from "./pages/Login";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./components/ProtectedRoute";
import Transaction from "./pages/Transaction";
import Expense from "./pages/Expense";
import Income from "./pages/Income";
import Settings from "./pages/Settings";
import Reports from "./pages/Reports";
import IncomeHistory from "./pages/IncomeHistory";
import ExpenseHistory from "./pages/ExpenseHistory";
import Logout from "./pages/Logout";
import Signup from "./pages/Signup";
import ChangePassword from "./pages/changePassword";
import { ExpenseCategoryProvider } from "./context/ExpenseCategoryContext";
import { Outlet } from "react-router-dom";
import { UserDataProvider } from "./context/UserDataContext";
import AdminDashborad from "./pages/AdminDashborad";
import { RoleProvider } from "./context/RoleContext";
import AdminIncome from "./pages/AdminIncome";
import AdminExpense from "./pages/AdminExpense";
import AdminReport from "./pages/AdminReport";
import AdminSetting from "./pages/AdminSetting";
import AdminDataProvider from "./context/AdminDataContext";

function App() {
  return (
    <>
      <BrowserRouter>
       <RoleProvider>
        <UserDataProvider>
          <ExpenseCategoryProvider>
         
             
              <Routes>
                <Route path="/" element={<Login />} />

                <Route path="/transactions" element={<Transaction />} />
                <Route path="/income" element={<Income />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="/income/history" element={<IncomeHistory />} />
                <Route path="/expense/history" element={<ExpenseHistory />} />
                <Route path="/logout" element={<Logout />} />
                <Route path="/signup" element={<Signup />} />
                <Route path="/changePassword" element={<ChangePassword />} />
                <Route path="/expense" element={<Expense />} />
                <Route path="/reports" element={<Reports />} />
                <Route path="/dashboard" element={<Dashboard />} />

                <Route path="/admin/dashboard" element={<AdminDashborad />} />
                <Route path="/admin/income" element={<AdminIncome />} />
                <Route path="/admin/expense" element={<AdminExpense />} />
                <Route path="/admin/report" element={<AdminReport />} />
                <Route path="/admin/setting" element={<AdminSetting/>} />
              </Routes>
       
          </ExpenseCategoryProvider>
        </UserDataProvider>
        </RoleProvider> 
      </BrowserRouter>
    </>
  );
}

export default App;
