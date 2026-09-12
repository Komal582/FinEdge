import React from 'react'
import "../../assets/css/InfoCard.css";


function InfoCard({title,amount,currency}) {
  const formattedAmount= new Intl.NumberFormat("en-IN",{
    style: "currency",
    currency: currency,
    maximumFractionDigits: 0
  }).format(amount);
  return (
    <div className='info-card'>
        <h3>{title}</h3>
        <h2>{formattedAmount}</h2>
    </div>
  )
}

export default InfoCard
