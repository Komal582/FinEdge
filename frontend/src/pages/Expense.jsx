import React from "react";
import Navbar from "../components/layout/Navbar";
import Sidebar from "../components/layout/Sidebar";
import { useState } from "react";
import { Link } from "react-router-dom";

function Expense() {
  const token = "Bearer " + localStorage.getItem("token");
  const [error, setError] = useState("");

  const [amount, setAmount] = useState(0);
  const [category, setCategory] = useState("");
  const [date, setDate] = useState("");
  const [note, setNote] = useState("");
  const [status, setStatus] = useState(null);

  async function saveExpense(event) {
    event.preventDefault();
    const formdata = new URLSearchParams();
    formdata.append("amount", amount);
    formdata.append("category", category);
    formdata.append("date", date);
    formdata.append("note", note);

    try {
      const response = await fetch("http://localhost:8080/expense/addExpense", {
        method: "POST",
        headers: {
          "Authorization": token,
        },
        body: formdata,
      });

      if (!response.ok) {
        throw new Error("Request Failed");
      }
      const data = await response.text();
      setStatus(data === "true");
    } catch (error) {
      console.log(error);
      setError("Something went wrong..try again later");
    }
  }

  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          {error ? (
            error
          ) : (
            <div>
              <h2>Add Expense</h2>
           {status===true && <p className='success' >Expense Added Successfully</p>}
          {status===false && <p className='failure'>Expense Not Added</p>}
              <form id="form" onSubmit={saveExpense}>
                <div>
                  <label htmlFor="amount">Amount (₹)</label>
                  <input
                    type="number"
                    step="0.01"
                    id="amount"
                    name="amount"
                    value={amount} 
                    onChange={(event)=>{setAmount(event.target.value)}}
                    required
                  />
                </div>

                <div>
                  <label htmlFor="category">Category</label>
                  <select id="category" name="category" value={category} onChange={(event)=>{setCategory(event.target.value)}} required>
                    <option value="">-- Select Category --</option>
                    <option value="Food">Food</option>
                    <option value="Shopping">Shopping</option>
                    <option value="Travel">Travel</option>
                    <option value="Rent">Rent</option>
                    <option value="Bills">Bills</option>
                    <option value="Medicines">Medicines</option>
                    <option value="Education">Education</option>
                    <option value="Other">Other</option>
                  </select>
                </div>

                <div>
                  <label htmlFor="date">Date</label>
                  <input type="date" id="date" name="date" value={date} onChange={(event)=>{setDate(event.target.value)}} required></input>
                </div>

                <div>
                  <label htmlFor="notes">Notes (optional)</label>
                  <textarea
                    id="notes"
                    name="note"
                    placeholder="Reason for this expense..."
                     value={note} 
                     onChange={(event)=>{setNote(event.target.value)}}
                  ></textarea>
                </div>

                <button type="submit">Save Expense</button>
              </form>
               <Link className="Income-history" to="/expense/history">view expense history</Link>
            </div>
          )}
        </main>
      </div>
    </>
  );
}

export default Expense;
