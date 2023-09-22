import { Link } from 'react-router-dom'
import React, { useState, useEffect} from 'react'

const Order = () => {

    const [bundles, setBundles] = useState([]);

    let userName = window.localStorage.getItem('userName');

//gets all of the items from the user that as a bundle order id
    useEffect(() => {
        // GET request using fetch inside useEffect React hook
        fetch('http://localhost:8080/order/previousorders/' + userName)
            .then(response => response.json())
            .then(data => setBundles(data))
            .catch(error => console.error("Error fetching previous orders:", error));
    }, []);

    const previousDisplay = () => {
        return bundles.map(order => (
            <div className="itemDisplay" key={order.itemId}>
                <p>Bundle Order Id: {order.bundleOrderId}</p>
                <p>{order.itemName}</p>
                <p>£{order.itemPrice}</p>
                <p>Quantity: {order.itemQuantity}</p>
            </div>
        ));
    };

    return (
        <div>

            <h3>Previous Orders:</h3>


          <div data-testid={"previousdisplay"} >{previousDisplay()}</div>



            <p></p>
            <CustomLink to="/Home">
                <button data-testid={"backbutton"}>Back</button>
            </CustomLink>
        </div>

    );
};

function CustomLink({ to, children, ...props }) {
    const path = window.location.pathname
    return (
        <ul className={path === to ? "active" : ""}>
            <Link to={to} {...props}>{children}</Link>
        </ul>
    )
}

export default Order