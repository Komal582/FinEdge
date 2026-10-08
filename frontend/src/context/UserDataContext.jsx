import React from "react";
import { createContext } from "react";
import { useState } from "react";

export const UserDataContext = createContext();

export function UserDataProvider({ children }) {
  const [userData, setUserData] = useState({});
  return (
    <UserDataContext.Provider value={{ userData, setUserData }}>
      {children}
    </UserDataContext.Provider>
  );
}

export default UserDataContext;
