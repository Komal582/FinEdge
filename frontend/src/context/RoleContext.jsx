import React from 'react'
import { createContext } from 'react'
import { useState } from 'react';


export const RoleContext = createContext();

export function RoleProvider({children}) {
 
  const[role,setRole] =useState("");
  
  return (
   <RoleContext.Provider value={{role,setRole}} >
      {children}
   </RoleContext.Provider>
  )
}

export default RoleContext
