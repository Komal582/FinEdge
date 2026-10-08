
import React, { useEffect }  from "react";
import { useState } from "react";
import Sidebar from "../components/Sidebar.jsx";
import Navbar from "../components/Navbar.jsx";
import "../assets/css/Dashboard.css";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";

function AdminExpense() {
  const [expenseList, setExpenseList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  let token = "Bearer " + localStorage.getItem("token");
  useEffect(() => {
    async function fetchAllIncome() {
      try {
        const response = await fetch("http://localhost:8080/admin/expense", {
          method: "GET",
          headers: {
            Authorization: token,
          },
        });
        const data = await response.json();
        if (!response.ok) {
          throw new Error("Request Failed");
        }
        setExpenseList(data);
      } catch (error) {
        setError("Something went wrong");
      } finally {
        setLoading(false);
      }
    }

    fetchAllIncome();
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
                <div className="table_style">
                  <h4>Expense History</h4>
                  {expenseList.length > 0 ? (
                    <div>
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
                          {expenseList.map((expense) => (
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
                  ) : (
                    <p>No Expense yet</p>
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

export default AdminExpense
