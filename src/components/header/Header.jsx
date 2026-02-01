import React from "react";
import "./Header.css";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();
  return (
    <header className="header">
      <nav>
        <ul>
          <li>
            <a
              href="/"
              onClick={() => {
                navigate("/");
              }}
              style={{ cursor: "pointer" }}
            >
              Home
            </a>
          </li>
          <li>
            <a href="/about">About</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
