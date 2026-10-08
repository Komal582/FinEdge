import React from 'react';
import{BarChart, Bar,XAxis,YAxis,Tooltip,Legend, ResponsiveContainer,CartesianGrid} from "recharts";
import "../assets/css/IncomeExpenseBar.css"

function IncomeExpenseBar({monthData}) {
  return (
    <div className='incomexpense-container'>
      <h3>Income vs Expense</h3>
       <ResponsiveContainer width="100%" height={200} >
           
           <BarChart data={monthData}>
            <CartesianGrid strokeDasharray="3 3" vertical={false}/>
            <XAxis 
                dataKey="month"
                tick={{fontSize:12,fill: "#6b7280"}}
                axisLine={{stroke:"#d1d5db" }}
                tickLine={false}
            ></XAxis>
            <YAxis
                tick={{fontSize:12,fill: "#6b7280"}}
                axisLine={{stroke:"#d1d5db" }}
                tickLine={false}
            ></YAxis>
            <Tooltip></Tooltip>
            <Legend></Legend>
            <Bar 
                dataKey="income"
                fill="blue"
                radius={[6,6,0,0]}
                barSize={20}
            ></Bar>
            <Bar 
                dataKey="expense"
                fill="red"
                radius={[6,6,0,0]}
                barSize={20}
            ></Bar>
           </BarChart>
       </ResponsiveContainer>
    </div>
  )
}

export default IncomeExpenseBar
