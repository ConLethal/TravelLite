import React from 'react';
import { render } from '@testing-library/react';
import Login from '../../pages/Login';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Login Component', () => {
    test('renders the login form correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Login />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("usernameinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("passwordinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("loginbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("registerbutton")).toBeTruthy();

    });

});
