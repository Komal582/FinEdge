import React, { useContext } from "react";
import "../assets/css/Login.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { FaChartBar } from "react-icons/fa";
import { FaUser } from "react-icons/fa";
import { FaLock } from "react-icons/fa";
import RoleContext from "../context/RoleContext";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const{role,setRole}=useContext(RoleContext);

  async function handleLogin(event) {
    event.preventDefault();
    const formdata = new URLSearchParams();
    formdata.append("username", username);
    formdata.append("password", password);

    const response = await fetch("http://localhost:8080/do-login", {
      method: "POST",
      body: formdata,
    });

    console.log(response.status);
    const data = await response.json();
    console.log("User data",data);
    console.log("User Token",data.token);
    localStorage.setItem("token", data.token);
    console.log("Role of User",typeof data.role);
    setRole(data.role);

    if(data.role=='USER'){
      navigate("/dashboard");  
    }
    else{
      navigate("/admin/dashboard");
    }
    
    
  }
  return (
    <div className="login-page">
      <div className="login-card">
        <div className="left">
          <h1>
            Smart way
            <br />
            to manage
            <br />
            your <span>money</span>
          </h1>

          <p>Take control of your finances and make smarter decisions.</p>
        </div>

        <div className="right">
          <h1 className="logo">
            <FaChartBar></FaChartBar>
            FinEdge
          </h1>
          <p className="tagline">AI Powered Personal Finance Platform</p>
          <h2 className="welcome-title">Welcome Back!</h2>
          <form onSubmit={handleLogin}>
            <div className="login-input-group">
              <label>Username</label>
              <div className="login-input-wrapper">
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
              <label>Password</label>
              <div className="login-input-wrapper">
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
              Login
            </button>

            <p className="signup">
              Don't have an account? <Link to="/signup">Sign Up</Link>
            </p>
          </form>
        </div>
      </div>
    </div>
  );
}
