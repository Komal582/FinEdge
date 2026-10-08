import React, { useEffect } from "react";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import { useState } from "react";
import IncomeExpenseBar from "../components/IncomeExpenseBar";
import ExpensePie from "../components/ExpensePie";
import ExpenseCategoryContext from "../context/ExpenseCategoryContext";
import { useContext } from "react";
import "../assets/css/Report.css";
import "../assets/css/table.css";
import UserDataContext from '../context/UserDataContext';




function Reports() {
  const {userData} = useContext(UserDataContext);
  console.log("User Data in Report",userData);
  const [error, setError] = useState("");
  const token = "Bearer " + localStorage.getItem("token");
  const [year, setYear] = useState(new Date().getFullYear());
  const [yearArr, setYearArr] = useState([]);
  const [monthData, setMonthData] = useState([]);
  const [display, setDisplay] = useState(false);

  const category = useContext(ExpenseCategoryContext);

  useEffect(() => {
    let currentYear = new Date().getFullYear();
    const years = [];
    for (let i = 1; i <= 5; i++) {
      years.push(currentYear);

      currentYear = currentYear - 1;
    }

    setYearArr(years);
  }, []);

  async function getMonthlyReport() {
    event.preventDefault();
    console.log("getMonthlyReport called");
    try {
      const response = await fetch(
        `http://localhost:8080/report/monthly?year=${Number(year)}`,
        {
          method: "GET",
          headers: {
            Authorization: token,
          },
        },
      );

      if (!response.ok) {
        throw new Error("Fetch Failed");
      }

      const data = await response.json();
      console.log("data", data);
      setDisplay(true);
      setMonthData(data);
    } catch (error) {
      setError("Something went wrong ...try later");
    }
  }

  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          <div>
            <form id="reportForm" onSubmit={getMonthlyReport}>
              <label htmlFor="year">Select Year to Generate Report</label>
              <div className="input-wrapper">
                <select
                  id="year"
                  value={year}
                  onChange={(event) => {
                    setYear(event.target.value);
                  }}
                  required
                >
                  {yearArr.map((year) => (
                    <option key={year} value={year}>
                      {year}
                    </option>
                  ))}
                </select>
                <button type="submit">Apply</button>
              </div>
            </form>
            {display ? (
              <div>
                <div className="reportCharts">
                  <div className="chart">
                   
                    <IncomeExpenseBar monthData={monthData} />
                  </div>
                  <div className="chart">
                 
                    <ExpensePie year={year} category={category} currency={Object.keys(userData).length!=0?(userData.transcationList.length!=0?userData.transcationList[0].currency:"INR"):"INR"} />
                  </div>
                </div>
                <div className="table_style">
                     <h4>Monthly Report</h4>
                  <table>
                 
                    <thead>
                      <tr>
                        <td>Months</td>
                        <td>Income</td>
                        <td>Expense</td>
                        <td>Transcations</td>
                        <td>Savings</td>
                      </tr>
                    </thead>
                    <tbody>
                      {monthData.map((data) => (
                        <tr key={data.month}>
                          <td>{data.month}</td>
                          <td>{data.income}</td>
                          <td>{data.expense}</td>
                          <td>{data.payment}</td>
                          <td>{data.savings}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              </div>
            ) : null}
          </div>
        </main>
      </div>
    </>
  );
}

export default Reports;
