import { useState, useEffect } from "react";

const PasSeatInfo = (props) => {
  console.log(props);
  return (
    <div className="seat-booking">
      <div className="passengers">
        <h2>Passenger #{props.pWSeat.passId} Information</h2>
        <h3>Seat number: {props.pWSeat.sPlace}</h3>
        <form className="passenger-form">
          <div className="form-row">
            <input type="text" placeholder="First Name" />
            <input type="text" placeholder="Last Name" />
          </div>

          <div className="form-row">
            <input type="email" placeholder="Email Address" />
            <input type="tel" placeholder="Phone Number" />
          </div>

          <div className="form-row">
            <select>
              <option value="">Gender</option>
              <option>Male</option>
              <option>Female</option>
            </select>

            <input type="date" />
          </div>

          <div className="form-row">
            <select>
              <option value="">Luggage Preference</option>
              <option>Cabin Only</option>
              <option>Checked Luggage</option>
              <option>Cabin + Checked</option>
            </select>
          </div>
        </form>
      </div>

      <div className="seat-details">
        <h2>Seat Details</h2>

        <ul>
          <li>
            <strong>Seat Class:</strong> Economy
          </li>
          <li>
            <strong>Extra Legroom:</strong> Yes
          </li>
          <li>
            <strong>Near Emergency Exit:</strong> No
          </li>
          <li>
            <strong>Seat Position:</strong> Window
          </li>
        </ul>
      </div>
    </div>
  );
};

export default PasSeatInfo;
