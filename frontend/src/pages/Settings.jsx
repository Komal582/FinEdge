import React from 'react';
import Navbar from '../components/Navbar';
import Sidebar from '../components/Sidebar';
import { FaGlobe, FaPalette , FaUser,FaLock,FaBell,FaSignOutAlt, FaAngleRight} from 'react-icons/fa';
import "../assets/css/Settings.css";
import "../assets/css/Dashboard_basics.css";
import { useState } from 'react';
import { Link } from 'react-router-dom';
import { useContext } from 'react';
import UserDataContext from '../context/UserDataContext';

function Settings() {
 
  const {userData} = useContext(UserDataContext);
 
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
                  <p>{userData.username}</p>
                  <p>{userData.email}</p>
                  <Link to="/edit">Edit Profile</Link>
              </div>
            </div>
            <div className='setting-element' >
           
               <Link className='setting-link' to="/changePassword"><FaUser></FaUser> 
             Personal Information<FaAngleRight/></Link>
          
            </div>
            <div className='setting-element'>
               <Link className='setting-link' to="/changePassword"><FaLock></FaLock><span>Change Password</span><FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
            
               <Link className='setting-link' to="/changePassword"><FaBell></FaBell>Notifications<FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
              
             <Link className='setting-link' to="/changePassword"><FaLock></FaLock>Privacy & Security<FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
              
             <Link className='setting-link' to="/changePassword"><FaPalette ></FaPalette>Theme<FaAngleRight/></Link>
            </div>
            <div className='setting-element'>
               
             <Link className='setting-link' to="/changePassword"><FaGlobe></FaGlobe>Language<FaAngleRight/> </Link>
            </div>
            <div className='setting-element'>
            
              <Link className='setting-link' to="/changePassword"><FaSignOutAlt/>Logout<FaAngleRight/></Link>
            </div>
          </div>
          
        </main>
      </div>
    </>
  )
}

export default Settings
