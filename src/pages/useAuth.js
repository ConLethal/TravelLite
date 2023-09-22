import { useState } from 'react';
const useAuth = () => {
  const [loggedIn, setLoggedIn] = useState(localStorage.getItem('token') !== null);
  const storedUserName = localStorage.getItem('userName')
  const [userName, setUserName] = useState(storedUserName || '');

  //saves the username and token to the local storage
  const login = (token, userName) => {
    localStorage.setItem('token', token);
    localStorage.setItem('userName', userName);
    setUserName(userName);
    setLoggedIn(true);
  };

  //removes the username and token from the local storage
  const logout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userName');
    setUserName('');
    setLoggedIn(false);
  };



  return { loggedIn, userName, login, logout };
};

export { useAuth };
