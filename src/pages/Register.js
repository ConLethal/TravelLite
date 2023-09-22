import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Register.css';

const Register = (props) => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [registrationStatus, setRegistrationStatus] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const myUser = { userName, password, firstName, lastName, email };
        console.log("registered user: ", myUser);
        fetch("http://localhost:8080/user/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(myUser)
        }).then((response) => {
            if (response.status === 200 || response.status === 201) {
                console.log("New user added");
                setRegistrationStatus('success');
            }
            if (response.status === 400) {
                console.log("response message:", response);
                // Username or email already exists
                setRegistrationStatus('username-exists');
            }
            if (response.status === 402) {
                console.log("Email exists");
                // Email already has an account
                setRegistrationStatus('email-exists');
            }
        }).catch((error) => {
            console.log("Error", error);
            // Handle network errors or other issues
            setRegistrationStatus('error');
        });
    };


    // Render based on registration status
    const RenderRegistrationStatus = () => {
       // useEffect(() => {
            if (registrationStatus === 'success') {
                // Display success message or redirect to the home page
                setTimeout(() => {
                    navigate("/Login")
                    return alert("Registration successful. Redirecting...");
                }, 1000)
            }
            if (registrationStatus === 'username-exists') {
                // Display an error message for duplicate username
                setTimeout(() => {
                    window.location.reload();
                  return alert("User name already exists, try a different user name");
                }, 1000)
            }
            if (registrationStatus === 'email-exists') {
                // Display an error message for duplicate username
                setTimeout(() => {
                    window.location.reload();
                   return alert("This email already has an account, try a different email");
                }, 1000)
            }
       // }, [])
        if (registrationStatus === 'error') {
            // Display a generic error message
            setTimeout(() => {
                window.location.reload();
                return alert("Registration Error, try again");
            }, 1000)
        }
        else {
            // Default rendering for initial state
            return (
                <div className="register container">
                    <form onSubmit={handleSubmit} className="register form">
                        <label htmlFor="firstName">First Name</label>
                        <p></p>
                        <input data-testid={"firstnameinput"} value={firstName} onChange={(e) => setFirstName(e.target.value)} type="text" placeholder="yourFirstName" id="firstName" name="firstName" />
                        <p></p>
                        <label htmlFor="lastName">Last Name</label>
                        <p></p>
                        <input data-testid={"lastnameinput"} value={lastName} onChange={(e) => setLastName(e.target.value)} type="text" placeholder="yourLastName" id="lastName" name="lastName" />
                        <p></p>
                        <label htmlFor="userName">User Name</label>
                        <p></p>
                        <input data-testid={"userNameinput"} value={userName} onChange={(e) => setUserName(e.target.value)} type="text" placeholder="youruserName" id="userName" name="userName" />
                        <p></p>
                        <label htmlFor="email">Email</label>
                        <p></p>
                        <input data-testid={"emailinput"} value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="yourname@email.co.uk" id="email" name="email" />
                        <p></p>
                        <label htmlFor="password">Password</label>
                        <p></p>
                        <input data-testid={"passWordinput"} value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="**********" id="password" name="password" />

                        <p></p>
                        <button data-testid={"registerbutton"} type="submit">Submit</button>
                    </form>

                    <Link to="/Login">
                        <button data-testid={"logInbutton"}>Log In</button>
                    </Link>
                </div>
            );

        }

    };
    return <>{RenderRegistrationStatus()}</>;
};




export default Register;
