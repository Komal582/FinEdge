import React, { useEffect } from 'react';
import Navbar from '../components/layout/Navbar';
import Sidebar from '../components/layout/Sidebar';
import { useState } from 'react';
import "../assets/css/Income.css";
import { Link } from 'react-router-dom';

function Income() {

  const token = "Bearer " +localStorage.getItem("token");
  const[error,setError] = useState("");
  const[amount,setAmount] =useState(0);
  const[source,setSource]=useState("");
  const[incomeDate,setIncomeDate]=useState("");
  const[notes,setNotes]=useState("");
  const[status,setStatus]=useState(null);
 

  async function saveIncome(event){
    event.preventDefault();
    const formdata = new URLSearchParams();
    formdata.append("amount",amount);
    formdata.append("source",source);
    formdata.append("incomeDate",incomeDate);
    formdata.append("notes",notes);

    try{
         const response = await fetch("http://localhost:8080/income/incomeAdded",{
         method:"POST",
         headers:{
             "Authorization":token
         },
         body:formdata } );

            if(!response.ok){
                throw new Error("Request Failed");
            }
            const data =await response.text();
            setStatus(data==="true");
        }
    catch(error){
          console.log(error);
          setError("Something went wrong..try again later");
    }

  }

  
  console.log("Status",status);
  return (
       <>
       <Navbar/>
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
        {error?error:<div>
           <h2>Add Income</h2>
        
           <form id="form" onSubmit={saveIncome}>
           
          {status===true && <p className='success' >Income Added Successfully</p>}
          {status===false && <p className='failure'>Income Not Added</p>}
           <div>
             <label htmlFor="amount">Amount (₹)</label>
            <input type="number" step="0.01" id="amount" name="amount" value={amount} onChange={(event)=>{setAmount(event.target.value)}}  required/>

           </div>


           <div>
             <label htmlFor="source">Source</label>
            <select id="source" name="source" value={source} onChange={(event)=>{setSource(event.target.value)}} required>
            <option value="">-- Select Source --</option>
            <option value="Salary">Salary</option>
            <option value="Business">Business</option>
            <option value="Freelance">Freelance</option>
            <option value="Interest">Interest</option>
            <option value="Gift">Gift</option>
            <option value="Other">Other</option>
            </select>
           </div>

           <div>
                 <label htmlFor="date">Date</label>
                 <input type="date" id="date" name="incomeDate" value={incomeDate} onChange={(event)=>{setIncomeDate(event.target.value)}} required/>
           </div>

            <div>
                 <label htmlFor="notes">Notes (optional)</label>
                 <textarea id="notes" name="notes" placeholder="Any description about this income..." value={notes} onChange={(event)=>{setNotes(event.target.value)}}></textarea>
            </div>
            <button type="submit">Save Income</button>
        </form>

        <Link className="Income-history" to="/income/history">view income history</Link>
         
        </div>}
        </main>
      </div>
    </>
  );
}

export default Income
