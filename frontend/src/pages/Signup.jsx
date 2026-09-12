import React from 'react'
import { useState } from 'react';
import { Link } from "react-router-dom";
import { FaChartBar } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
import { FaLock } from "react-icons/fa";
import "../assets/css/Login.css";

function Signup() {
 const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const[email,setEmail]=useState("");
  const[status,setStatus]=useState(null);
  const[error,setError]=useState("");

  async function handleLogin(event) {
    try{
        event.preventDefault();
    const formdata = new URLSearchParams();
    formdata.append("name", username);
    formdata.append("password", password);
    formdata.append("email",email);
    const response = await fetch("http://localhost:8080/user/register", {
      method: "POST",
      body: formdata,
    });

    if(!response.ok){

    }
    const data = await response.json();

    setStatus(true);
   
    }
    catch(error){
      setError(error);
    }
    
  }
  return (
    <div className="login-page">

      <div className="login-card">

        <div className="left">
          
           <h1>
             Create your <br/>
           <span>FinEdge</span><br/>
             account 
           </h1>

           <p>
            Start your journey to financial freedom
           </p>

        </div>

        <div className="right">
           
          <h1 className="logo">
            <FaChartBar></FaChartBar>
            FinEdge
          </h1>
          <p className="tagline">AI Powered Personal Finance Platform</p>
          <h2 className="welcome-title">Create Account</h2>
          {status===true && <p className='success' >User Signed up Successfully</p>}
          {status===false && <p className='failure'>Unable to Sign</p>}
          <form onSubmit={handleLogin}>

            <div className="login-input-group">
              <label>Username</label>
              <div className="input-wrapper">
              <FaUser></FaUser>
              <input
                placeholder="Enter your name"
                type="text"
                value={username}
                onChange={(event) => setUsername(event.target.value)}
              ></input>
           
              </div>
            </div>

              <div className="login-input-group">
              <label>Email</label>
              <div className="input-wrapper">
                  <FaLock></FaLock>
              <input
                placeholder="Enter your email"
                type="email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
              ></input>
              </div>
            </div>

            <div className="login-input-group">
              <label>Password</label>
              <div className="input-wrapper">
                  <FaLock></FaLock>
              <input
                placeholder="Enter your password"
                type="password"
                value={password}
                onChange={(event) => setPassword(event.target.value)}
              ></input>
              </div>
            </div>

            <button type="submit" className="login-btn">
              Sign Up
            </button>

            <p className="signup">
              Already have an account? <Link to="/">Login</Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Signup
