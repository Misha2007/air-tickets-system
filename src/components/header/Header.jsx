import React from "react";
import "./Header.css";
import { useNavigate } from "react-router-dom";

const Header = () => {
  const navigate = useNavigate();
  return (
    <header className="header">
      <h1
        onClick={() => {
          navigate("/");
        }}
        style={{ cursor: "pointer" }}
      >
        Flight ticket search
      </h1>
      <nav>
        <ul>
          <li>
            <a href="/">Home</a>
          </li>
          <li>
            <a href="/about">About</a>
          </li>
          <li>
            <a href="/flight">Flight</a>
          </li>
        </ul>
      </nav>
    </header>
  );
};

export default Header;
