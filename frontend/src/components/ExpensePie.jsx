import React, { useEffect } from "react";
import { useState } from "react";
import { Legend, Pie, PieChart, ResponsiveContainer, Tooltip} from "recharts";
import "../assets/css/expensePie.css";

function ExpensePie({ year, category, currency }) {
  const token = "Bearer " + localStorage.getItem("token");
  const [categoryData, setCategoryData] = useState({});
  const colors=[  
    "#2563eb",
    "#16a34a",
    "#f59e0b",
    "#ef4444",
    "#8b5cf6",
    "#06b6d4",
    "#f97316",
    "#84cc16"
    ];

  const chartData = Object.entries(categoryData).map(([category, amount],index) => {
    return {
      category: category,
      amount: amount,
      fill:colors[index%colors.length]
    };
  }); 

  console.log("Chart data", chartData);
  useEffect(() => {
    async function getExpenseCategoryData() {
      const response = await fetch("http://localhost:8080/report/expense", {
        method: "POST",
        headers: {
          Authorization: token,
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          year: year,
          category: category,
        }),
      });

      const data = await response.json();
      setCategoryData(data);
      console.log("Expense category data", data);
    }
    getExpenseCategoryData();
  }, [year]);

  return (
    <div className="expensepie-container">
      <h3>Expense by Category</h3>
      <ResponsiveContainer width="100%" height={200}>
        <PieChart>
          <Pie 
          data={chartData} 
          dataKey="amount" 
          nameKey="category" 
          outerRadius={70}
          innerRadius={60}
          >
           
          </Pie>
          <Tooltip
            formatter={(value) =>
              Intl.NumberFormat("en-IN", {
                style: "currency",
                currency: currency,
                maximumFractionDigits: 0,
              }).format(value)
            }
          />
          <Legend
            verticalAlign="bottom"
            height={36}
            wrapperStyle={{
              fontSize:"13px",
               color: "#4b5563"
            }}
          />

        </PieChart>
      </ResponsiveContainer>
    </div>
  );
}

export default ExpensePie;
