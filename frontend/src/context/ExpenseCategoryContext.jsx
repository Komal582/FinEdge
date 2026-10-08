import React from 'react'
import {createContext, useState } from 'react'

const options=["Food","Shopping","Travel","Rent","Bills","Medicines","Education","Other"];

export const ExpenseCategoryContext = createContext();

export function ExpenseCategoryProvider({children}) {

  
  return (
   <ExpenseCategoryContext.Provider value={options}>
     {children}
   </ExpenseCategoryContext.Provider>
  );
}

export default ExpenseCategoryContext;  
