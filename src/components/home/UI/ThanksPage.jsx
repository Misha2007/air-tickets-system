import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const ThanksPage = () => {
  const navigate = useNavigate();
  return (
    <div className="page">
      <div id="main-page">
        <div className="form-container">
          <div className="thx">
            <h2>Thank you for your booking</h2>
            <button
              onClick={() => {
                navigate("/");
              }}
            >
              Book more
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ThanksPage;
