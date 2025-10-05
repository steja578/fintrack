import React, { useEffect, useState } from "react";
import axios from "axios";

function App() {
  // Create state to hold accounts
  const [accounts, setAccounts] = useState([]);

  // Fetch data from backend when page loads
  useEffect(() => {
    axios
      .get("http://localhost:8080/api/accounts") // your backend endpoint
      .then((response) => {
        setAccounts(response.data); // store in state
      })
      .catch((error) => {
        console.error("Error fetching accounts:", error);
      });
  }, []);

  // Render HTML content
  return (
    <div style={{ textAlign: "center", marginTop: "40px", fontFamily: "Arial" }}>
      <h1>💰 FinTrack Dashboard</h1>
      <h3>Account List</h3>

      {accounts.length > 0 ? (
        <table
          border="1"
          cellPadding="10"
          style={{
            margin: "20px auto",
            borderCollapse: "collapse",
            width: "70%",
          }}
        >
          <thead style={{ backgroundColor: "#f2f2f2" }}>
            <tr>
              <th>ID</th>
              <th>Account Number</th>
              <th>Balance</th>
              <th>Type</th>
              <th>User</th>
            </tr>
          </thead>
          <tbody>
            {accounts.map((acc) => (
              <tr key={acc.id}>
                <td>{acc.id}</td>
                <td>{acc.accountNumber}</td>
                <td>${acc.balance}</td>
                <td>{acc.type}</td>
                <td>{acc.user?.username}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading accounts...</p>
      )}
    </div>
  );
}

export default App;
