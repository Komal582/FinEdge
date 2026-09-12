import React from 'react'
import Navbar from '../components/layout/Navbar';
import Sidebar from '../components/layout/Sidebar';


function Reports() {
  return (
    <>
      <Navbar />
      <div className="dashboard-container">
        <Sidebar />
        <main className="dashboard-content">
          <div>
             <h1>Expense</h1>
          </div>
        </main>
      </div>
    </>
  )
}

export default Reports
