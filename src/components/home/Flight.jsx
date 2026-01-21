import "./Flight.css";
import { useState, useEffect } from "react";

const Flight = (props) => {
  const columns = ["A", "B", "C", "D"];
  const [seats, setSeats] = useState([]);
  const [rows, setRows] = useState(0);
  const [selectedSeats, setSelectedSeats] = useState({});
  const [selectedPassenger, setSelectedPassenger] = useState(1);

  useEffect(() => {
    if (props.flight) {
      fetch(`http://192.168.41.206:8081/seats/flight/${props.flight.id}`, {
        method: "GET",
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      })
        .then((response) => response.json())
        .then((data) => {
          setSeats(data);
          setRows(Math.ceil(data.length / columns.length));
          const preselected = {};

          for (let index = 0; index < props.persons; index++) {
            const seat = data
              .filter((seat) => seat.vaba)
              .slice(index, index + 1)
              .map((seat) => {
                const seatIndex = seat.number - 1;
                const row = Math.floor(seatIndex / columns.length) + 1;
                const col = columns[seatIndex % columns.length];
                return `${row}${col}`;
              })[0];
            preselected[index + 1] = seat;
          }
          setSelectedSeats(preselected);
        })
        .catch((error) => console.error("Error fetching seats:", error));
    }
  }, [props.flight]);

  const handleSeatSelect = (event) => {
    const selected = event.target.value;

    setSelectedSeats((prevSelected) => {
      const newSelected = { ...prevSelected };

      const existingEntry = Object.entries(newSelected).find(
        ([_, seat]) => seat === selected
      );

      if (existingEntry) {
        setSelectedPassenger(existingEntry[0]);
        delete newSelected[existingEntry[0]];
      } else {
        if (Object.keys(newSelected).length < props.persons) {
          if (selectedPassenger) {
            newSelected[selectedPassenger] = selected;
          }
        }
      }

      return newSelected;
    });
  };

  const isSeatAvailable = (seatNumber) => {
    return !seats.some((seat) => seat.number === seatNumber && !seat.vaba);
  };

  return (
    <div className="seat-selection">
      <div className="passengers">
        <h2>Passengers</h2>
        <div>
          {Array.from({ length: props.persons }).map((_, index) => (
            <div>
              <div className="passengers-container selected">
                <div className="passenger-square">
                  <span class="seat-text">P{index + 1}</span>
                </div>
                {index + 1 == selectedPassenger ? (
                  <p key={index} className="passenger-row selected">
                    Passenger {index + 1}
                  </p>
                ) : (
                  <p key={index} className="passenger-row">
                    Passenger {index + 1}
                  </p>
                )}
              </div>

              <div className="passenger-info">
                {index + 1 == selectedPassenger ? (
                  <strong>Selected</strong>
                ) : null}
                {/* <button onClick={() => setSelectedPassenger(index + 1)}>
                  Select
                </button> */}
                <span>
                  Seat:
                  {" " + selectedSeats[index + 1]}
                </span>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="airplane">
        <div className="cockpit">
          <h1>Cockpit</h1>
        </div>

        <div className="wings left-wing"></div>

        <div className="seat-container">
          <div className="exit exit--front">EXIT</div>

          <ol className="rows">
            <li className="row">
              <small>A</small>
              <small>B</small>
              <small>C</small>
              <small>D</small>
            </li>
            {[...Array(rows)].map((_, rowIndex) => (
              <li className="row" key={rowIndex}>
                {columns.map((col) => {
                  const seatNumber = `${rowIndex + 1}${col}`;
                  const isSelected =
                    Object.values(selectedSeats).includes(seatNumber);

                  const isAvailable = isSeatAvailable(seatNumber);
                  return (
                    <label
                      key={`${rowIndex}${col}`}
                      className={`seat-label ${isSelected ? "selected" : ""} ${
                        !isAvailable ? "disabled" : ""
                      }`}
                    >
                      <input
                        type="checkbox"
                        name="seat"
                        value={seatNumber}
                        onChange={handleSeatSelect}
                        id={`seat-${rowIndex + 1}-${col}`}
                        disabled={!isAvailable}
                        checked={Object.values(selectedSeats).includes(
                          seatNumber
                        )}
                      />
                      <span className="seat-text">
                        <span className="seat-text">
                          {Object.values(selectedSeats).includes(seatNumber)
                            ? `P${
                                Object.entries(selectedSeats).find(
                                  ([_, seat]) => seat === seatNumber
                                )[0]
                              }`
                            : seatNumber}
                        </span>
                      </span>
                    </label>
                  );
                })}
              </li>
            ))}
          </ol>

          <div className="exit exit--back">EXIT</div>
        </div>

        <div className="wings right-wing"></div>

        <div className="tail">
          <h2>Tail</h2>
        </div>
      </div>
      <div className="passengers">
        <h2>Passengers</h2>
        <div className="passengers-container selected">
          <div className="passenger-square"></div>
          <p className="passenger-row">Yours</p>
        </div>
        <div className="passengers-container available">
          <div className="passenger-square"></div>
          <p className="passenger-row">Available</p>
        </div>
        <div className="passengers-container taken">
          <div className="passenger-square"></div>
          <p className="passenger-row">Taken</p>
        </div>
      </div>
    </div>
  );
};

export default Flight;
