import React from 'react';
import { useAuth } from './useAuth'; // Import the custom authentication hook
import { useNavigate } from 'react-router-dom'; // Import useHistory to perform redirection
import './Account.css'

const Account = () => {
  const { logout } = useAuth();
  const navigate = useNavigate(); // Get the history object from react-router-dom

  const handleLogout = () => {
    logout(); // Call the logout function from your custom authentication hook
    navigate('/Login'); // Redirect to the Login page after logging out
  }

  return (
    <div  data-testid={"accounttext"}className="account container">
      <h1>Account</h1>
      <p>First name:</p>
      <p>Last name:</p>
      <p>Email:</p>
      <p>Username:</p>
      <p>Password:</p>
      <button data-testid={"accountbutton"} type="button" onClick={handleLogout}>Log out</button>
    </div>
  );
};

export default Account;
