import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import Navbar from "../components/layout/Navbar";
import Sidebar from "../components/layout/Sidebar";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";
function ExpenseHistory() {
      const [expenses, setExpenses] = useState([]);
      const [error, setError] = useState("");
      let token = "Bearer " + localStorage.getItem("token");
      useEffect(() => {
        async function fetchExpenseHistory() {
          try {
            const response = await fetch("http://localhost:8080/expense/history", {
              method: "GET",
              headers: {
                "Authorization": token,
              },
            });
    
            if (!response.ok) {
              throw new Error("Request Failed");
            }
    
            const data = await response.json();
            console.log("Data", data);
            setExpenses(data);
          } catch (error) {
            setError("Fetch Failed");
            console.log(error);
          }
        }
        fetchExpenseHistory();
      }, []);
  return (
   <>
      <Navbar />
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          <div>
             {error ? (
            error
          ) : (
            <div className="table_style">
              <h3>Expense History</h3>
              <table>
                <thead>
                  <tr>
                    <td>Amount</td>
                    <td>Category </td>
                    <td>Note</td>
                    <td>Date</td>
                  </tr>
                </thead>
                <tbody>
                    {expenses.map((expense)=> (
                        <tr key={expense.expense_id}>
                        <td>{expense.amount}</td>
                        <td>{expense.category}</td>
                        <td>{expense.note}</td>
                        <td>{new Date(expense.date,).toLocaleDateString()}</td>
                        </tr>
                      ))}
                </tbody>
              </table>
            </div>
          )}
          </div>
        </main>
      </div>
    </>
  )
}

export default ExpenseHistory
