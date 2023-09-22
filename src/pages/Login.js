import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from './useAuth'; // Import the custom authentication hook
import './Login.css'

const Login = () => {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useAuth(); // Destructure the login function from the custom hook
    const navigate = useNavigate();


  const handleSubmit = (e) => {
    e.preventDefault();
    const myUser = { userName, password };
    console.log("registered user: ", myUser);

    fetch("http://localhost:8080/user/", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(myUser)
    })
    .then((response) => {
      if (response.status === 200) {
        return response.text(); // Parse the response body as text (the JWT token)
      } else {
        console.log("Failed here");
        console.log("Response: ", response.body);
        throw new Error("Login failed");
      }
    })
    .then((token) => {
      login(token, myUser.userName); // Call the login function from the custom hook with the JWT token
      setTimeout(() => {
         navigate("/Home")
        return alert("Login successful. Redirecting...");
    }, 1000)
    })
    .catch((error) => {
      console.error("Login failed");
      // Handle login error
      window.location.reload();
      return alert("Login error, try again")
    });
  };


    return (
        <div className="login container">

            <form onSubmit={handleSubmit}>
                <label htmlFor="userName">User Name</label>
                <p></p>
                <input data-testid={"usernameinput"} value={userName} onChange={(e) => setUserName(e.target.value)} type="username" placeholder="yourusername" id="username" name="userName" />
                <p></p>
                <label htmlFor="password">Password</label>
                <p></p>
                <input data-testid={"passwordinput"} value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="**********" id="password" name="password" />
                <p></p>
                <button data-testid={"loginbutton"}>Submit</button>
            </form>

            < CustomLink to="/Register">
                <button data-testid={"registerbutton"}>Register Here</button>
            </CustomLink>

        </div>
    )
}


function CustomLink({ to, children, ...props }) {
    const path = window.location.pathname
    return (
        <ul className={path === to ? "active" : ""}>
            <Link to={to} {...props}>{children}</Link>
        </ul>
    )
}

export default Login


