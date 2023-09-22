import React from 'react';
import { Link } from 'react-router-dom'
import './Navbar.css'


export default function Navbar() {

    return (
        <nav data-testid={"navbarpage"} className="nav">
            <Link to="/Home" className="site-title">Travel Lite</Link>
            <ul>
                <CustomLink data-testid={"loginroute"} to="/Login">Log-In</CustomLink>
                <CustomLink data-testid={"accountroute"} to="/Account">Account</CustomLink>
                <CustomLink data-testid={"registerroute"} to="/Register">Register</CustomLink>
                <CustomLink data-testid={"aboutroute"} to="/About">About</CustomLink>
            </ul>
        </nav>
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