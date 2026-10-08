import React, { useEffect } from "react";
import Sidebar from "../components/Sidebar.jsx";
import Navbar from "../components/Navbar.jsx";
import "../assets/css/Dashboard.css";

import InfoCard from "../components/InfoCard.jsx";

import "../assets/css/table.css";
import "../assets/css/AdminDashboard.css";
import "../assets/css/Dashboard_basics.css";
import { use } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { UserDataContext } from "../context/UserDataContext.jsx";
import { useContext } from "react";

function AdminDashborad() {
  const [loading, setLoading] = useState(true);
  const [expense, setExpenses] = useState(0);
  const [income, setIncomes] = useState(0);
  const [error, setError] = useState("");
  const [users, setUsers] = useState(0);
  const [transactions, setTransactions] = useState(0);
  const [userList, setUserList] = useState(0);
  const [transactionsList, setTransactionsList] = useState(0);
  const { userData, setUserData } = useContext(UserDataContext);
  let token = "Bearer " + localStorage.getItem("token");
  useEffect(() => {
    async function fetchDashboard() {
      try {
        const response = await fetch("http://localhost:8080/admin/dashboard", {
          method: "GET",
          headers: {
            Authorization: token,
          },
        });
        const data = await response.json();
        console.log("Data", data);

        if (!response.ok) {
          throw new Error("Request Failed");
        }

        setUserData(data);

        setExpenses(data.total_expense);
        setIncomes(data.total_income);
        setTransactions(data.total_transactions);
        setUsers(data.total_users);
        setTransactionsList(data.transactionList);
        setUserList(data.userList);
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
                  <InfoCard title="Total Users" amount={users} />
                  <InfoCard title="Total Income" amount={income} />
                  <InfoCard title="Total Expenses" amount={expense} />
                  <InfoCard title="Total Transactions" amount={transactions} />
                </div>

                <div className="tables">
                  <div className="table_style">
                    <h4>Recent Transactions</h4>
                    {transactionsList.length > 0 ? (
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
                            {transactionsList.map((transaction) => (
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

                  <div className="table_style">
                    <h4>Recent Users</h4>
                    {userList.length > 0 ? (
                      <div>
                        <table>
                          <thead>
                            <tr>
                              <th>Username</th>
                              <th>Email</th>
                              <th>Date</th>
                            </tr>
                          </thead>
                          <tbody>
                            {userList.map((user) => (
                              <tr key={user.user_id}>
                                <td>{user.username}</td>
                                <td>{user.email}</td>

                                <td>
                                  {new Date(
                                    user.created_at,
                                  ).toLocaleDateString()}
                                </td>
                              </tr>
                            ))}
                          </tbody>
                        </table>

                        <Link to="/transactions">more transactions...</Link>
                      </div>
                    ) : (
                      <p>No Users yet</p>
                    )}
                  </div>
                </div>
              </>
            )}
          </div>
        </main>
      </div>
    </>
  );
}

export default AdminDashborad;
