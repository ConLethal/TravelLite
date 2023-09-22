import React from 'react';


const About = () => {
    return(
        <div data-testid={"abouttext"}>
            <h1>About Travel Lite</h1>
            <p> Over the last few years airlines have been increasing their baggage charges and decreasing the available free or carry-on baggage allowance.</p>
               <p> For many travelling with work they need an increasing array of items and technical equipment.</p>
            <p> It can be challenging to both remember all the equipment needed and to ensure that it fits within the baggage allowance along with some other clothing, toiletry essentials. </p>
            <p> A company is setting up a service in which they provide bespoke packs for individual travellers which will include items as requested by the traveller such as charging/docking stations and selected toiletries.</p>
            <p>The aim of this project is to design and develop a platform in which travellers will be able to request and select items and have them delivered to their work/hotel location.</p>
            <p></p>
            <h2>Navigating the Website</h2>
            <p>The Navbar is for easy access to the main parts of the website</p>
            <p>The title is also a button, which allows you to go to the Homepage</p>
            <p>The Homepage contains two buttons: "Create a Bundle" and "View Previous Orders"</p>
            <p>The create a bundle page contains several dropdown option bars, first select the type of product, select the product and then select the quantity</p>
            <p>Save your delivery address and payment</p>
            <p>The Account page allows you to logout of the website</p>

        </div>
    )
}

export default About;