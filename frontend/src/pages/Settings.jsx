import React from 'react';
import Navbar from '../components/layout/Navbar';
import Sidebar from '../components/layout/Sidebar';
import { FaGlobe, FaPalette , FaUser,FaLock,FaBell,FaSignOutAlt, FaAngleRight} from 'react-icons/fa';
import "../assets/css/Settings.css";
import "../assets/css/Dashboard_basics.css";
import { useState } from 'react';
import { Link } from 'react-router-dom';


function Settings() {
  const[name,setName]=useState("komal");
  const[email,setEmail]=useState("komal123@gmail.com");

 
  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          <div className="setting-card">
            <div className='profile'>
              <div className="Photo">
                   <FaUser/>
              </div>
              <div className="edit">
                  <p>{name}</p>
                  <p>{email}</p>
                  <Link to="/edit">Edit Profile</Link>
              </div>
            </div>
            <div className='setting-element' >
           
               <FaUser></FaUser> 
             Personal Information<Link to="/changePassword"><FaAngleRight/></Link>
          
            </div>
            <div className='setting-element'>
               <FaLock></FaLock>Change Password<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
            
               <FaBell></FaBell>Notifications<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
              
             <FaLock></FaLock>Privacy & Security<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
              
             <FaPalette ></FaPalette>Theme<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
               
             <FaGlobe></FaGlobe>Language<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
            
              <FaSignOutAlt/>Logout<Link to="/changePassword"><FaAngleRight/></Link>
            </div>
          </div>
          
        </main>
      </div>
    </>
  )
}

export default Settings
