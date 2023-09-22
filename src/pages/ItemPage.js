
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './ItemPage.css'

// function itemTypesHC() {
//     return [
//         { itemTypeName: "Electrical", id: 1 },
//         { itemTypeName: "Business", id: 2 },
//         { itemTypeName: "Clothing", id: 3 },
//         { itemTypeName: "Family", id: 4 }
//     ];
// }
//
// function itemsHC() {
//     return [
//         { itemName: "Single Adapter", id: 1, itemTypeId: 1 },
//         { itemName: "Multi Adapter", id: 2, itemTypeId: 1 },
//         { itemName: "Winter Coat", id: 3, itemTypeId: 3 },
//         { itemName: "Formal Shoes", id: 4, itemTypeId: 2 },
//         { itemName: "Family Coats", id: 5, itemTypeId: 4 }
//     ];
// }

export default function ItemPage() {

    const [itemTypes, setItemTypes] = useState([]);
    const [selectedItemTypeId, setSelectedItemTypeId] = useState(null);
    const [items, setItems] = useState([]);
    const [selectedItem, setSelectedItem] = useState([]);
    const [basketItems, setBasketItems] = useState([]);
    const [itemQuantity, setItemQuantity] = useState(0.00);
    //const [saveBundle, setSaveBundle] = useState([]);
    const navigate = useNavigate();

//returns all the item type names
    useEffect(() => {
        // GET request using fetch inside useEffect React hook
        fetch('http://localhost:8080/item/itemtypes')
            .then(response => response.json())
            .then(data => setItemTypes(data))
            .catch(error => console.error("Error fetching item types:", error));
    }, []);

    //returns the items based on the item type that was selected
    useEffect(() => {
        // GET request using fetch inside useEffect React hook
        fetch('http://localhost:8080/item/searchbyitemtype/' + selectedItemTypeId)
            .then(response => response.json())
            .then(data => setItems(data))
            .catch(error => console.error("Error fetching item types:", error));
// empty dependency array means this effect will only run once
    }, [selectedItemTypeId]);


    const itemTypeOptionsList = () => {
        let array = []
        itemTypes.forEach(element => {
            array.push(<option key={element.itemTypeId} value={element.itemTypeId}>{element.itemTypeName}</option>)
        })
        return array
    }

    const itemOptionsList = () => {
        let array = []
        items.forEach(element => {
            array.push(<option key={element.itemId} value={element.itemId}>{element.itemName}</option>)
        })
        return array
    }

    const quantityList = () => {
        let count = [1, 2, 3, 4, 5];
        return count.map((value) => (
            <option key={value} value={value}>
                {value}
            </option>
        ));
    };

    const handleAddToBasket = () => {
        if (selectedItem.length > 0 || selectedItem.length < 5) {
            const newItem = { ...selectedItem[0], itemQuantity: itemQuantity };
            setBasketItems(prevBasketItems => [...prevBasketItems, newItem]);
        }
    };

    const handleRemoveFromBasket = (item) => {
        setBasketItems(prevBasketItems =>
            prevBasketItems.filter(basketItems => basketItems.itemId !== item.itemId)
        );
    };


    const basketDisplay = () => {
        return basketItems.map(item => (
            <div className="itemDisplay" key={item.itemId}>
                <p>{item.itemName}</p>
                <p>£{item.itemPrice}</p>
                <img src={item.itemImageUrl} alt={''}/>
                <p>Quantity: {item.itemQuantity}</p>
                <button onClick={() => handleRemoveFromBasket(item)}>Remove Item</button>
            </div>
        ));
    };

    const saveBundleToServer = () => {
        let userName = window.localStorage.getItem('userName');
        console.log("Got username: ", userName);
        console.log("My Basket:", basketItems);
        let itemRequestList = basketItems.map(bi => {
            const { itemId, itemPrice, itemQuantity } = bi;
            return { itemId, itemPrice, itemQuantity };
        });

        let payload = { userName, itemRequestList };
        console.log("Payload:", payload);
        // Send payload to server to save bundle
        fetch('http://localhost:8080/order/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)

        })
            .then((response) => {
                if (response.status === 201) {
                    setTimeout(() => {
                        navigate("/BundlePurchase")
                        return alert("Bundle creation successful. Redirecting...");
                    }, 1000)
                }
            })
            .catch((error) => {
                console.error("Bundle creation failed");
                // Handle login error
                window.location.reload();
                return alert("Bundle creation error, try again")
            });
    };


    return (
        <div className='container'>
            <h1>Create A Bundle</h1>
            <div>
                <label>Select an Item Type</label>
                <select data-testid={"typeselect"} onChange={(e) => setSelectedItemTypeId(parseInt(e.target.value))}>
                    <option value=''>Choose a Type</option>
                    {itemTypeOptionsList()}
                </select>
                <p></p>
                <label>Select an Item</label>
                <select data-testid={"itemselect"} onChange={(e) => {
                    const selectedItem = items.filter(item => item.itemId === parseInt(e.target.value));
                    setSelectedItem(selectedItem);
                }}>
                    <option value=''>Choose an Item</option>
                    {itemOptionsList()}
                </select>
                <label>Quantity</label>
                <select data-testid={"quantityselect"} onChange={(e) => {
                    let quantity =  parseInt(e.target.value);
                    setItemQuantity(quantity);
                }}>
                    <option value=''>Choose a Quantity</option>
                    {quantityList()}
                </select>
            </div>
            <button data-testid={"addtobasketbutton"} onClick={handleAddToBasket}>Add to Bundle</button>
            <div>
                <h2>Basket</h2>
                {basketDisplay()}
            </div>
            <div>
                <button data-testid={"savebundlebutton"} onClick={saveBundleToServer}>Save Bundle</button>
            </div>
        </div>
    );

}