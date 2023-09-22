import React from 'react';
import { render } from '@testing-library/react';
import Order from '../../pages/Order';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Order Component', () => {
    test('renders the order page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Order />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("backbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("previousdisplay")).toBeTruthy();

    });

});
