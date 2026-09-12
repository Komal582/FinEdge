import React, { useEffect } from "react";
import Sidebar from "../components/layout/Sidebar.jsx";
import Navbar from "../components/layout/Navbar.jsx";
import "../assets/css/Dashboard.css";
import "../assets/css/table.css";
import "../assets/css/Dashboard_basics.css";
import "../assets/css/Cards.css";
import { useState } from "react";
import "../assets/css/Transaction.css"

function Transaction() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [transactionlist, setTransactionlist] = useState([]);
  const[amount,setAmount]=useState(0);
  const[paymentStatus,setPaymentStatus]=useState(null);

   let token = "Bearer " + localStorage.getItem("token");
    useEffect(() => {
      async function fetchDashboard() {
        try {
          const response = await fetch("http://localhost:8080/transcation/history", {
            method: "GET",
            headers: {
               "Authorization":token,
            },
          });
          if (!response.ok) {
                throw new Error("Request Failed");
              }
          const data = await response.json();
          setTransactionlist(data);
         
        } catch (error) {
          setError("Something went wrong ");
        } finally {
          setLoading(false);
        }
      }
  
      fetchDashboard();
    }, [paymentStatus]);


   async function makePayment(){
        try{
                const response = await fetch(`http://localhost:8080/payment/create-order?amount=${Number(amount)}`,{
                method:"POST",
                headers:{
                  "Content-Type":"application/json",
                  "Authorization":token
                }
              });
        
              if(!response.ok){
                throw new Error("Request Failed");
              }

              const data = await response.json();
              console.log("Payment Data", data);

              const options={
                  key: "rzp_test_Rt08aid73XdR7V",
                  amount: data.amount,
                  currency: data.currency,
                  order_id: data.order_id,

                  handler: async function (response) {
                      try{
                         const paymentResponse = await fetch("http://localhost:8080/payment/payment-callback",{
                          method:"POST",
                          headers:{
                            "Content-type":"application/json",
                            "Authorization":token
                          },
                          body:JSON.stringify(response)
                       });
                      if(!paymentResponse.ok){
                        throw new Error("Payment Request Failed");
                      }

                      const data = await paymentResponse.json();
                      setPaymentStatus(data.success);
                      setAmount("");
                      console.log("Payment Status", data);

                      }
                      catch(error){
                        console.log(error);
                      }

                  }
              };

              const razorpay = new window.Razorpay(options);
              razorpay.open();
        }
        catch(error){
            console.log("ERROR",error);
            setError("Something went wrong while making payment!")
        }
        
        



    }
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
              
                <div className="table_style">
                  <h4>Transactions</h4>

                  <input type="number" placeholder="enter amount" value={amount} onChange={(e)=>setAmount(e.target.value)}/>
                  <button onClick={makePayment}>Make Payment</button>
                  <p>Total Transactions: {transactionlist.length}</p>
                  {paymentStatus==true&&<p className="success">Payment Successfull</p>}
                  {paymentStatus==false&&<p className="failure">Payment Failed</p>}
                  {transactionlist.length > 0 ? (
                    <div>
                      <table>
                        <thead>
                          <tr>
                            <th>Currency</th>
                            <th>Amount</th>
                            <th>Method</th>
                            <th>Status</th>
                            <th>Date</th>
                          </tr>
                        </thead>
                        <tbody>
                          {transactionlist.map((transaction) => (
                            <tr key={transaction.payment_id}>
                              <td>{transaction.currency}</td>
                              <td>{transaction.amount}</td>
                              <td>{transaction.method}</td>
                              <td className={transaction.payment_status}>{transaction.payment_status==="captured"?"Captured":"Failed"}</td>
                              <td>
                                {new Date(transaction.createdAt,).toLocaleDateString()}
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>

                     
                    </div>
                  ) : (
                    <p>No transactions yet</p>
                  )}
                </div>
              
            )}
          </div>
        </main>
      </div>
    </>
  );
}

export default Transaction;
