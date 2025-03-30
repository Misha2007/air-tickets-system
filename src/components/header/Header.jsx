import React from "react";
import "./Header.css"; // Import styles if needed

const Header = () => {
  return (
    <header className="header">
      <h1>My Website</h1>
      <nav>
        <ul>
          <li>
            <a href="/">Home</a>
          </li>
          <li>
            <a href="/">About</a>
          </li>
          <li>
            <a href="/">Account</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
