import React from 'react';
import { render } from '@testing-library/react';
import ItemPage from '../../pages/ItemPage';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('Item page Component', () => {
    test('renders the item page page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <ItemPage />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("typeselect")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("itemselect")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("quantityselect")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("addtobasketbutton")).toBeTruthy();
        expect(renderedComponent.getAllByTestId("savebundlebutton")).toBeTruthy();

    });

});
