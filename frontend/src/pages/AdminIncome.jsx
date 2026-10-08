import React, { useEffect }  from "react";
import { useState } from "react";
import Sidebar from "../components/Sidebar.jsx";
import Navbar from "../components/Navbar.jsx";
import "../assets/css/Dashboard.css";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";

function AdminIncome() {
  const [incomeList, setIncomeList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  let token = "Bearer " + localStorage.getItem("token");
  useEffect(() => {
    async function fetchAllIncome() {
      try {
        const response = await fetch("http://localhost:8080/admin/income", {
          method: "GET",
          headers: {
            Authorization: token,
          },
        });
        const data = await response.json();
        if (!response.ok) {
          throw new Error("Request Failed");
        }
        setIncomeList(data);
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
                  <h4>Income History</h4>
                  {incomeList.length > 0 ? (
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
                          {incomeList.map((income) => (
                         <tr key={income.income_id}>
                              <td>{income.amount}</td>
                              <td>{income.source}</td>
                              <td>{income.note}</td>
                              <td>
                                {new Date(income.date).toLocaleDateString()}
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>
                  ) : (
                    <p>No Income yet</p>
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

export default AdminIncome;
