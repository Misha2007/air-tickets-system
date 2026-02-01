import "./Flight.css";
import { useState } from "react";
import PasSeatInfo from "./UI/PasSeatInfo";
import { useNavigate } from "react-router-dom";
const apiUrl = import.meta.env.VITE_API_URL;

const SelectedSeats = ({ passengerWSeat }) => {
  const [passengers, setPassengers] = useState(
    passengerWSeat.map((pWSeat) => ({
      seatId: pWSeat.seat.id,
      baggageId: 1,
      reisijaENimi: "",
      reisijaPNimi: "",
      email: "",
      telefon: "",
      synniP: "",
      sugu: "",
      countryId: "",
      nationalId: "",
    })),
  );

  const navigate = useNavigate();

  const updatePassenger = (index, passengerData) => {
    setPassengers((prev) => {
      const newArr = [...prev];
      newArr[index] = passengerData;
      return newArr;
    });
  };

  const submitAllPassengers = async () => {
    try {
      console.log(passengers);
      const response = await fetch(`${apiUrl}passenger-booking/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(passengers),
      });
      const data = await response.json();
      console.log("All passengers saved:", data);
      navigate("/thanks");
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div>
      <div className="seat-booking">
        {passengerWSeat.map((pWSeat, index) => (
          <PasSeatInfo
            key={index}
            pWSeat={pWSeat}
            passengerData={passengers[index]}
            setPassengerData={(data) => updatePassenger(index, data)}
          />
        ))}

        <button
          onClick={submitAllPassengers}
          disabled={passengers.length > passengerWSeat.length}
        >
          Submit All Passengers
        </button>
      </div>
    </div>
  );
};

export default SelectedSeats;
