import React, { useEffect } from "react";
import Sidebar from "../components/Sidebar.jsx";
import Navbar from "../components/Navbar.jsx";
import "../assets/css/Dashboard.css";
import "../components/InfoCard.jsx";
import InfoCard from "../components/InfoCard.jsx";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";
import { use } from "react";
import { UserDataContext } from "../context/UserDataContext.jsx";
import { useContext } from "react";

import { useState } from "react";
import { Link } from "react-router-dom";

function Dashboard() {
  const { userData, setUserData } = useContext(UserDataContext);
  const [loading, setLoading] = useState(true);
  const [expense, setExpenses] = useState(0);
  const [income, setIncomes] = useState(0);
  const [error, setError] = useState("");
  const [balance, setBalance] = useState(0);

  const [transactionsList, setTransactionsList] = useState(0);
  let token = "Bearer " + localStorage.getItem("token");

  useEffect(() => {
    async function fetchDashboard() {
      try {
        const response = await fetch("http://localhost:8080/user/dashboard", {
          method: "POST",
          headers: {
            Authorization: token,
          },
        });
        const data = await response.json();
        console.log("Data in Context ", data);
        if (!response.ok) {
          throw new Error("Request Failed");
        }

        setUserData(data);
        setExpenses(data.expense);
        setIncomes(data.incomes);
        setTransactionsList(data.transcationList);
        setBalance(data.balance);
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
      <Navbar />
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
                  <InfoCard
                    title="Total Balance"
                    amount={userData.balance}
                    currency={
                      userData.transcationList.length != 0
                        ? userData.transcationList[0].currency
                        : "INR"
                    }
                  />
                  <InfoCard
                    title="Total Income"
                    amount={userData.income}
                    currency={
                      userData.transcationList.length != 0
                        ? userData.transcationList[0].currency
                        : "INR"
                    }
                  />
                  <InfoCard
                    title="Total Expense"
                    amount={userData.expense}
                    currency={
                      userData.transcationList.length != 0
                        ? userData.transcationList[0].currency
                        : "INR"
                    }
                  />
                </div>

                <div className="table_style">
                  <h4>Recent Transactions</h4>
                  {userData.transcationList.length > 0 ? (
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
                          {userData.transcationList.map((transaction) => (
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
