import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom'


const Payment = (props) => {
    const [nameOnCard, setNameOnCard] = useState('');
    const [longNumber, setLongNumber] = useState('');
    const [expiry, setExpiry] = useState('');
    const [securityCode, setSecurityCode] = useState('');
    const [userName, setUserName] = useState(props.userNameToPay)
    const [paid, setPaid] = useState(props.totalToPay)

    const handlePaymentSubmit = async (e) => {
        e.preventDefault();

        let bundleOrderId = props.bundleIdToPay

        let userPayment = { longNumber, nameOnCard, expiry, securityCode, userName, bundleOrderId, paid};
        await fetch('http://localhost:8080/pay/carddetails', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userPayment)
        }).then((response) => {
            if (response.status === 201) {
                props.onPaymentSubmit()
            }
        }).catch((error) => {
            console.error("Payment submission failed:", error);
            // Handle login error
            window.location.reload();
            return alert("Payment submission error, try again")
        });
    }




    return (
        <div>
            <label>Payment</label>
            <form>
                <label htmlFor="longNumber">Long Number</label>
                <p></p>
                <input data-testid={"longnumberinput"} value={longNumber} onChange={(e) => setLongNumber(e.target.value)} type="longNumber" placeholder="**** **** **** ****" id="longNumber" name="longNumber" />
                <p></p>
                <label htmlFor="expiry">Expiry</label>
                <p></p>
                <input data-testid={"expiryinput"} value={expiry} onChange={(e) => setExpiry(e.target.value)} type="expiry" placeholder="mm/yy" id="expiry" name="expiry" />
                <p></p>
                <label htmlFor="name">Name</label>
                <p></p>
                <input data-testid={"nameinput"} value={nameOnCard} onChange={(e) => setNameOnCard(e.target.value)} type="nameOnCard" placeholder="Name On Card" id="nameOnCard" name="nameOnCard" />
                <p></p>
                <label htmlFor="securityCode">Security Code</label>
                <p></p>
                <input data-testid={"securitycodeinput"} value={securityCode} onChange={(e) => setSecurityCode(e.target.value)} type="securityCode" placeholder="***" id="securityCode" name="securityCode" />
                <label>Total cost(£)</label>
                <p></p>
                <input data-testid={"totaldisplay"} value={props.totalToPay} disabled={true}></input>
                <p></p>
                <label>Bundle Order Number</label>
                <input data-testid={"bundledisplayid"} value={props.bundleIdToPay} disabled={true}></input>
                <p></p>
                <label>Username</label>
                <input data-testid={"userdisplay"} value={props.userNameToPay} disabled={true}></input>
                <button data-testid={"paymentbutton"} onClick={handlePaymentSubmit} type="submit">Submit</button>
            </form>

            <CustomLink to="/Home">
                <button data-testid={"homebutton"}>Home page</button>
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

export default Payment