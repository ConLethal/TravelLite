import React from 'react';
import { render } from '@testing-library/react';
import Account from '../../pages/Account';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Account Component', () => {
    test('renders the account page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <Account />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("accountbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("accounttext")).toBeTruthy();

    });

});
