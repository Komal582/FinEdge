import React from 'react'
import { createContext } from 'react';
import { useState } from 'react';

export const AdminDataContext = createContext();

function AdminDataProvider({children}) {

  const[adminData,setAdminData]=useState({});
  return (
    <AdminDataContext.Provider value={{adminData,setAdminData}}>
         {children}
    </AdminDataContext.Provider>
  )
}

export default AdminDataProvider
