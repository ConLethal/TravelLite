import React from 'react';
import { render } from '@testing-library/react';
import Register from '../../pages/Register';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Register Component', () => {
    test('renders the register form correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Register />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("registerbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("firstnameinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("lastnameinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("userNameinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("emailinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("passWordinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("logInbutton")).toBeTruthy();

    });

});
