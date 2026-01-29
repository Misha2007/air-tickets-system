import "./Flight.css";
import { useState, useEffect } from "react";
import PasSeatInfo from "./UI/PasSeatInfo";

const SelectedSeats = (props) => {
  const columns = ["A", "B", "C", "D"];
  console.log(props.passengerWSeat);
  // const [passenger, setPassenger]

  const createPassengerBooking = async (passenger) => {
    try {
      const response = await fetch(
        `http://192.168.41.206:8081/passenger-booking/create`,
        {
          method: "POST",
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(passenger),
        },
      );
      const data = await response.json();
      console.log("Response Data:", response);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div>
      {props.passengerWSeat.map((pWSeat, index) => (
        <PasSeatInfo
          pWSeat={pWSeat}
          createPassengerBooking={createPassengerBooking}
          key={index}
        />
      ))}
    </div>
  );
};

export default SelectedSeats;
