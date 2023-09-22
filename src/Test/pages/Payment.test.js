import React from 'react';
import { render } from '@testing-library/react';
import Payment from '../../pages/Payment';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Payment Component', () => {
    test('renders the payment form correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Payment />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("longnumberinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("expiryinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("nameinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("securitycodeinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("totaldisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("bundledisplayid")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("userdisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("paymentbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("homebutton")).toBeTruthy();

    });

});
