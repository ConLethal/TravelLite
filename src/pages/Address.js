import React, { useState} from 'react';
import { Link } from 'react-router-dom'


const Address = (props) => {
    const [address, setAddress] = useState('');
    const [country, setCountry] = useState('');
    const [postcode, setPostcode] = useState('');
    const [userName, setUserName] = useState(props.userNameToDeliver);

    const handleDeliverySubmit = (e) => {
        e.preventDefault();

        let bundleOrderId = props.bundleIdToDeliver

        let userAddress = { address, country, postcode, bundleOrderId, userName};
        // console.log('userAddress: ',userAddress)

        fetch('http://localhost:8080/address/delivery', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userAddress)
        }).then((response) => {
                if (response.status === 201) {
                    props.onAddressSubmit(bundleOrderId)

                }
            }).catch((error) => {
                console.error("Address submission failed");
                // Handle login error
                window.location.reload();
                return alert("Address submission error, try again")
            });
    }



    return (
        <div className="container">
            <form>
                <label htmlFor="address">Address</label>
                <p></p>
                <input data-testid={"addressinput"} value={address} onChange={(e) => setAddress(e.target.value)} type="address" placeholder="address" id="address" name="address" />
                <p></p>
                <label htmlFor="country">Country</label>
                <p></p>
                <input data-testid={"countryinput"} value={country} onChange={(e) => setCountry(e.target.value)} type="country" placeholder="country" id="country" name="country" />
                <p></p>
                <label htmlFor="postcode">Postcode</label>
                <p></p>
                <input data-testid={"postcodeinput"} value={postcode} onChange={(e) => setPostcode(e.target.value)} type="postcode" placeholder="postcode" id="postcode" name="postcode" />
                <p></p>
                <label>Bundle Order Number</label>
                <input data-testid={"bundleorderiddisplay"} value={props.bundleIdToDeliver} disabled={true}></input>
                <p></p>
                <label>Username</label>
                <input data-testid={"userdisplay"} value={props.userNameToDeliver} disabled={true}></input>
                <button data-testid={"addressbutton"} onClick={handleDeliverySubmit} type="submit">Submit</button>
            </form>

                <CustomLink to="/ItemPage">
                    <button data-testid={"backbutton"}>Back</button>
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

export default Address