import React from 'react';
import { Link } from 'react-router-dom'
import './Home.css'


function Home() {
    return (
        <div data-testid={"homepage"} className="home container">
                <CustomLink to="/ItemPage">
                    <button data-testid={"createbundlebutton"} >Create a Bundle</button>
                </CustomLink>
                <CustomLink to="/Order">
                    <button data-testid={"previousbutton"} >View Previous Orders</button>
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

export default Home