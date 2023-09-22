import React from 'react';
import { render } from '@testing-library/react';
import Home from '../../pages/Home';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Home Component', () => {
    test('renders the Home page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Home />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("homepage")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("createbundlebutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("previousbutton")).toBeTruthy();

    });

});
