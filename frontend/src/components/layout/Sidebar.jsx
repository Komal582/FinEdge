import React from 'react'
import { useState } from 'react'
import { FaBars, FaHome, FaWallet, FaMoneyBillWave,FaChartBar,FaCog, FaSignOutAlt} from 'react-icons/fa';
import "../../assets/css/Sidebar.css";
import { Link } from 'react-router-dom';

function Sidebar() {
  const[isOpen, setIsOpen] = useState(true);

  return (
    <div className={`sidebar ${!isOpen ? "closed": ""}`}>
       
       <button onClick={()=>setIsOpen(!isOpen)}>
       <FaBars/>
       </button>
       <ul>
        <li className='active'>
                <FaHome/>
                {isOpen && <Link className="link" to="/dashboard">Dashboard</Link>} 
        </li>
         <li >
                <FaWallet/>
                {isOpen && <Link className="link" to="/income">Income</Link>} 
        </li>
         <li>
                <FaMoneyBillWave/>
                {isOpen && <Link className="link" to="/expense">Expense</Link>} 
        </li>
         <li>
                <FaChartBar/>
                {isOpen && <Link className="link" to="/reports">Reports</Link>} 
        </li>
         <li>
                <FaCog/>
                {isOpen && <Link className="link" to="/settings">Settings</Link>} 
        </li>
         <li>
                <FaSignOutAlt/>
                {isOpen && <Link className="link" to="/logout">Logout</Link>} 
        </li>
       </ul>
    </div>
  )
}

export default Sidebar
