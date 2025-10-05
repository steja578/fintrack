import React, { useEffect, useState } from "react";
import api from "../services/api";

const AccountList = () => {
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    api.get("/accounts")
      .then(response => setAccounts(response.data))
      .catch(error => console.error("Error fetching accounts:", error));
  }, []);

  return (
    <div>
      <h2>Accounts</h2>
      <ul>
        {accounts.map(acc => (
          <li key={acc.id}>
            <strong>{acc.accountNumber || "N/A"}</strong> - {acc.type || "Unknown"} - 💰{acc.balance}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AccountList;
