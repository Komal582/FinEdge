import React, { useContext } from 'react'
import { useState } from 'react'
import { FaBars, FaHome, FaWallet, FaMoneyBillWave,FaChartBar,FaCog, FaSignOutAlt} from 'react-icons/fa';
import "../assets/css/Sidebar.css";
import { Link } from 'react-router-dom';
import RoleContext from '../context/RoleContext';

function Sidebar() {
  const[isOpen, setIsOpen] = useState(true);
  const role=useContext(RoleContext);
  console.log("Role",role);
  return (
    <div className={`sidebar ${!isOpen ? "closed": ""}`}>
       
       <button onClick={()=>setIsOpen(!isOpen)}>
       <FaBars/>
       </button>
       <ul>
        <li>
                <FaHome/>
                {isOpen && <Link className="link" to={role.role=="USER"? "/dashboard" : "/admin/dashboard" }>Dashboard</Link>} 
        </li>
         <li>
                <FaWallet/>
                {isOpen && <Link className="link" to={role.role=="USER"? "/income" : "/admin/income" }>Income</Link>} 
        </li>
         <li>
                <FaMoneyBillWave/>
                {isOpen && <Link className="link" to={role.role=="USER"? "/expense" : "/admin/expense" }>Expense</Link>} 
        </li>
         <li>
                <FaChartBar/>
                {isOpen && <Link className="link" to={role.role=="USER"? "/reports" : "/admin/report" }>Reports</Link>} 
        </li>
         <li>
                <FaChartBar/>
                {isOpen && <Link className="link" to={role.role=="USER"? "/ai" : "/admin/ai" }>AI Insights</Link>} 
        </li>

         <li>
                <FaCog/>
                {isOpen && <Link className="link" to="/settings">Settings</Link>} 
        </li>
         <li>
                <FaSignOutAlt/>
                {isOpen && <Link className="link" to="/logout" >Logout</Link>} 
        </li>
       </ul>
    </div>
  )
}

export default Sidebar
