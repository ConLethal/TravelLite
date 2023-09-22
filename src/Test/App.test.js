import React from 'react';
import { render } from '@testing-library/react';
import App from '../App';
import {describe, expect, test} from '@jest/globals';
import {MemoryRouter} from "react-router-dom";

describe('test', () => {
  test('renders the app', () => {
    // let renderedComponent = render(
    //     <BrowserRouter>
    //       <App />
    //     </BrowserRouter>
    // );

    const renderedComponent = render(<MemoryRouter>
      <App />
    </MemoryRouter>)



  });
})