import React from 'react';
import { render } from '@testing-library/react';
import Navbar from '../../pages/Navbar';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Navbar Component', () => {
    test('renders the navbar function correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Navbar />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("navbarpage")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("loginroute")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("accountroute")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("registerroute")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("aboutroute")).toBeTruthy();

    });

});
