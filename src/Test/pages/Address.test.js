import React from 'react';
import { render } from '@testing-library/react';
import Address from '../../pages/Address';
import {describe, expect, jest, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Address Component', () => {
    test('renders the Address page correctly', () => {

        let jestFunction = jest.fn
        const renderedComponent = render(<MemoryRouter>
            <Address
                userNameToDeliver={"username"}
                bundleIdToDeliver={1}
                onAddressSubmit={jestFunction}
            />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("backbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("addressbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("userdisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("bundleorderiddisplay")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("postcodeinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("countryinput")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("addressinput")).toBeTruthy();

    });

});
