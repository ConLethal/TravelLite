import React from 'react';
import Navbar from './pages/Navbar'
import Account from "./pages/Account"
import Login from './pages/Login'
import Home from './pages/Home'
import Register from './pages/Register'
import Order from './pages/Order'
import Payment from './pages/Payment'
import {Route, Routes} from 'react-router-dom'
import ItemPage from "./pages/ItemPage";
import Address from "./pages/Address";
import BundlePurchase from "./pages/BundlePurchase";
import About from "./pages/About";

function App() {
    return (
        <div>
            <Navbar />
            <Routes>
                <Route path="/Register" element={<Register/>}/>
                <Route path="/" element={<Login/>}/>
                <Route path="/Login" element={<Login/>}/>
                <Route path="/Home" element={<Home/>}/>
                <Route path="/Account" element={<Account/>}/>
                <Route path="/Address" element={<Address />}/>
                <Route path="/Order" element={<Order/>}/>
                <Route path="/Payment" element={<Payment/>}/>
                <Route path="/ItemPage" element={<ItemPage />}/>
                <Route path="/BundlePurchase" element={<BundlePurchase />}/>
                <Route path="/About" element={<About />}/>
            </Routes>
        </div>
    )}


export default App