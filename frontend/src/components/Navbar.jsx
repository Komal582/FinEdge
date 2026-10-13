import React from 'react'
import {FaBell, FaUserCircle} from "react-icons/fa";
import "../assets/css/Navbar.css";


function Navbar() {
  const savedUserData = localStorage.getItem("UserData");
  const userData = savedUserData ? JSON.parse(savedUserData):null;

 
  return (
   
        <nav className="navbar">
            <div className="logo">
                 <h2>Finedge</h2>

            </div>

            <div className="nav-right">
                <button className="notification-btn">
                    <FaBell/>
                </button>

                <div className="profile">
                    <FaUserCircle/>
                    <span>{userData.username}</span>
                </div>
            </div>

        </nav>
  
  )
}

export default Navbar
