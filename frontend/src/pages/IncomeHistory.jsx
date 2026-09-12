import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import Navbar from "../components/layout/Navbar";
import Sidebar from "../components/layout/Sidebar";
import "../assets/css/IncomeHistory.css";
import "../assets/css/table.css";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";

function IncomeHistory() {
  const [incomes, setIncomes] = useState([]);
  const [error, setError] = useState("");
  let token = "Bearer " + localStorage.getItem("token");
  useEffect(() => {
    async function fetchIncomeHistory() {
      try {
        const response = await fetch("http://localhost:8080/income/history", {
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
        setIncomes(data);
      } catch (error) {
        setError("Fetch Failed");
        console.log(error);
      }
    }
    fetchIncomeHistory();
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
              <h3>Income History</h3>
                <table>
                <thead>
                  <tr>
                    <td>Amount</td>
                    <td>Source </td>
                    <td>Note</td>
                    <td>Date</td>
                  </tr>
                </thead>
                <tbody>
                    {incomes.map((income)=> (
                        <tr key={income.income_id}>
                        <td>{income.amount}</td>
                        <td>{income.source}</td>
                        <td>{income.note}</td>
                        <td>{new Date(income.date,).toLocaleDateString()}</td>
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
  );
}

export default IncomeHistory;
