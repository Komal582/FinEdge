import React, { useEffect } from "react";
import Sidebar from "../components/layout/Sidebar.jsx";
import Navbar from "../components/layout/Navbar.jsx";
import "../assets/css/Dashboard.css";
import "../components/common/InfoCard.jsx";
import InfoCard from "../components/common/InfoCard.jsx";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";

import { useState } from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  const [loading, setLoading] = useState(true);
  const [balance, setBalance] = useState(0);
  const [expense, setExpense] = useState(0);
  const [income, setIncome] = useState(0);
  const [error, setError] = useState("");
  const[name,setName]=useState("");
  const [transactionlist, setTransactionlist] = useState([{}]);
  const[currency,setCurrency]=useState("");

  let token = "Bearer " + localStorage.getItem("token");
  useEffect(() => {
    async function fetchDashboard() {
      try {
        const response = await fetch("http://localhost:8080/user/dashboard", {
          method: "POST",
          headers: {
             "Authorization":token,
          },
        });
        const data = await response.json();
        console.log("Data",data);
        if (!response.ok) {
          throw new Error("Request Failed");
        }
       
        setExpense(data.expense); 
        setIncome(data.income);
        setBalance(data.balance);
        setTransactionlist(data.transcationList);
        setCurrency(data.transcationList[0].currency);
        setName(data.userName);
       
      } catch (error) {
        setError("Something went wrong");
      } finally {
        setLoading(false);
      }
    }

    fetchDashboard();
  }, []);

  return (
    <>
      <Navbar/>
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          <div>
            {loading ? (
              "Loading..."
            ) : error ? (
              error
            ) : (
              <>
                <div className="cards">
                  <InfoCard title="Total Balance" amount={balance} currency={currency} />
                  <InfoCard title="Total Income" amount={income} currency={currency}/>
                  <InfoCard title="Total Expense" amount={expense} currency={currency}/>
                </div>

                <div className="table_style">
                  <h4>Recent Transactions</h4>
                  {transactionlist.length > 0 ? (
                    <div>
                       
                         <table>
                        <thead>
                          <tr>
                            <th>Currency</th>
                            <th>Amount</th>
                            <th>Method</th>
                            <th>Date</th>
                          </tr>
                        </thead>
                        <tbody>
                          {transactionlist.map((transaction) => (
                            <tr key={transaction.payment_id}>
                              
                              <td>{transaction.currency}</td>
                              <td>{transaction.amount}</td>
                              <td>{transaction.method}</td>

                              <td>
                                {new Date(
                                  transaction.createdAt,
                                ).toLocaleDateString()}
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>

                      
                      
                        <Link to="/transactions">more transactions...</Link>
                    </div>
                  ) : (
                    <p>No transcations yet</p>
                  )}
                </div>
              </>
            )}
          </div>
        </main>
      </div>
    </>
  );
}

export default Dashboard;
