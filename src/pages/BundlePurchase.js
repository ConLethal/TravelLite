import React, {useState, useEffect} from 'react';
import Address from "./Address";
import Payment from "./Payment";
import "./BundlePurchase.css"
import { useNavigate } from 'react-router-dom'

const BundlePurchase = () => {

    const [orderSummary, setOrderSummary] = useState([])
    const [addressSaved, setAddressSaved] = useState(false)
    const [bundle, setBundle] = useState('');
    const [total, setTotal] = useState(0.00);
    const navigate = useNavigate();

    const userName = window.localStorage.getItem('userName');

//uses the username from local storage to find the bundle that was just created
    useEffect(() => {
        // GET request using fetch inside useEffect React hook
        fetch('http://localhost:8080/order/' + userName)
            .then(response => response.json())
            .then(data => setBundle(data))
            .catch(error => console.error("Error fetching order summary bundle order id:", error));
    }, [userName]);

//takes the bundle order id that was just found by the above useEffect to then populate the order summary
    useEffect(() => {
        // GET request using fetch inside useEffect React hook
        fetch('http://localhost:8080/order/ordersummary/' + userName  + "/" + bundle)
            .then(response => response.json())
            .then(data => setOrderSummary(data))
            .catch(error => console.error("Error fetching order summary:", error));
    },[userName, bundle]);

    const summaryDisplay = () => {
        console.log("Summary Display")
        console.log("orderSummary", orderSummary)
        console.log("orderSummary[0]", orderSummary[0])


        return orderSummary.map(summary => (
            <div className="summaryDisplay" key={summary.itemId}>
                <p>{summary.itemName}</p>
                <p>£{summary.itemPrice*summary.itemQuantity}</p>
                <p>Quantity: {summary.itemQuantity}</p>
            </div>
        ));
    };

    const summaryTotal = () => {
        //shows the total and is then saved to the database
        let itemsTotal = 0.00;

        orderSummary.forEach(order => {
            itemsTotal += order.itemPrice * order.itemQuantity;
        });

        setTotal(itemsTotal)
    }

    const handleAddressSubmit = (bundleId) => {
        //when the setAddressSaved is set to true then the payment form can be displayed
        // only when the address is saved successfully
        setAddressSaved(true)
        summaryTotal()
    }

    const handlePaymentSubmission = () => {
        setTimeout(() => {
            navigate("/Home");
            return alert("Payment successful, redirecting to Homepage...");
        }, 1000)
    }


    return (
        <div>
            <h3>Basket Items:</h3>
          <div data-testid={"summarydisplay"}>
            {summaryDisplay()}
          </div>
            <div data-testid={"addressdisplay"}>
            <Address
                bundleIdToDeliver={bundle}
                userNameToDeliver={window.localStorage.getItem('userName')}
                onAddressSubmit={handleAddressSubmit}/>
            </div>
            <div data-testid={"paymentdisplay"}>
              {addressSaved && (
                <Payment
                    bundleIdToPay={bundle}
                    userNameToPay={window.localStorage.getItem('userName')}
                    totalToPay={total}
                    onPaymentSubmit={handlePaymentSubmission}
                />

              )}
            </div>
        </div>

    )
}

export default BundlePurchase