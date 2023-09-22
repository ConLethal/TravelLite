import React from 'react';
import { render } from '@testing-library/react';
import About from '../../pages/About';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('About Component', () => {
    test('renders the About page correctly', () => {

        const renderedComponent = render(<MemoryRouter>
            <About />
        </MemoryRouter> );

        expect(renderedComponent.getAllByTestId("abouttext")).toBeTruthy();

    });

});
