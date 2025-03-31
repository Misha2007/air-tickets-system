import "./Flight.css";
import { useState } from "react";

const Flight = ({ flight }) => {
  const rows = 4;
  const columns = ["A", "B", "C", "D"];
  const [selectedSeat, setSelectedSeat] = useState(null);

  const handleSeatSelect = (event) => {
    setSelectedSeat(event.target.value);
  };

  return (
    <div className="airplane">
      <div className="cockpit">
        <h1>Cockpit</h1>
      </div>

      <div className="wings left-wing"></div>

      <div className="seat-container">
        <div className="exit exit--front">EXIT</div>

        <ol className="rows">
          {[...Array(rows)].map((_, rowIndex) => (
            <li className="row" key={rowIndex}>
              {columns.map((col) => (
                <label
                  key={`${rowIndex}${col}`}
                  className={`seat-label ${
                    selectedSeat === `${rowIndex + 1}${col}` ? "selected" : ""
                  }`}
                >
                  <input
                    type="radio"
                    name="seat"
                    value={`${rowIndex + 1}${col}`}
                    onChange={handleSeatSelect}
                    id={`seat-${rowIndex + 1}-${col}`}
                  />
                  <span className="seat-text">{`${rowIndex + 1}${col}`}</span>
                </label>
              ))}
            </li>
          ))}
        </ol>

        <div className="exit exit--back">EXIT</div>
      </div>

      <div className="wings right-wing"></div>

      <div className="tail">
        <h2>Tail</h2>
      </div>

      {selectedSeat && <p>Selected Seat: {selectedSeat}</p>}
    </div>
  );
};

export default Flight;
