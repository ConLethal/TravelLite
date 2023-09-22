import React from 'react';
import { render } from '@testing-library/react';
import BundlePurchase from '../../pages/BundlePurchase';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Bundle Purchase Component', () => {
    test('renders the Bundle Purchase page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <BundlePurchase />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("summarydisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("addressdisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("paymentdisplay")).toBeTruthy();

    });

});
