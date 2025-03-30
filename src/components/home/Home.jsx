import { useState } from "react";
import "./Home.css";
import FlightSearch from "./FlightSearch";

const Home = () => {
  const [formData, setFormData] = useState({
    arrival: "",
    destination: "",
    date: "",
    persons: 1,
  });

  const [flights, setFlights] = useState([]);

  const [formIsFilled, setFormIsFilled] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
    setFormIsFilled(true);
  };

  const searchFlights = (e) => {
    e.preventDefault();

    // Mock flight data (Replace with API call)
    const allFlights = [
      {
        from: {
          code: "TLL",
          city: "Tallinn",
          dateTime: new Date("2025-03-22T17:50:00"),
        },
        to: {
          code: "CDG",
          city: "Paris",
          dateTime: new Date("2025-03-22T20:55:00"),
        },
        flightNumber: "FR 1234",
        price: "€26.39",
      },
      {
        from: {
          code: "BER",
          city: "Berlin",
          dateTime: new Date("2025-03-22T07:30:00"),
        },
        to: {
          code: "LHR",
          city: "London",
          dateTime: new Date("2025-03-22T09:00:00"),
        },
        flightNumber: "LH 5678",
        price: "€50.00",
      },
    ];

    // **Filter flights based on user input**
    const filteredFlights = allFlights.filter((flight) => {
      const flightDate = flight.from.dateTime.toISOString().split("T")[0]; // "2025-03-22"
      return (
        (formData.arrival === "" ||
          flight.from.city
            .toLowerCase()
            .includes(formData.arrival.toLowerCase()) ||
          flight.from.code
            .toLowerCase()
            .includes(formData.arrival.toLowerCase())) &&
        (formData.destination === "" ||
          flight.to.city
            .toLowerCase()
            .includes(formData.destination.toLowerCase()) ||
          flight.to.code
            .toLowerCase()
            .includes(formData.destination.toLowerCase())) &&
        (formData.date === "" || flightDate === formData.date)
      );
    });

    setFlights(filteredFlights);
  };

  return (
    <div id="page">
      <div id="main-page">
        <div className="form-container">
          <form onSubmit={searchFlights}>
            <div className="form-group">
              <label htmlFor="arrival">Arrival:</label>
              <input
                id="arrival"
                type="text"
                placeholder="Tallinn"
                value={formData.arrival}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label htmlFor="destination">Destination:</label>
              <input
                id="destination"
                type="text"
                placeholder="Paris"
                value={formData.destination}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label htmlFor="date">Date:</label>
              <input
                id="date"
                type="date"
                value={formData.date}
                onChange={handleChange}
              />
            </div>

            <div className="form-group">
              <label htmlFor="persons">Persons:</label>
              <input
                id="persons"
                type="number"
                min="1"
                value={formData.persons}
                onChange={handleChange}
              />
            </div>

            <i className="fa fa-search" onClick={searchFlights}></i>
          </form>
        </div>
      </div>

      {formIsFilled && (
        <div className="main-flight-search">
          {flights.length > 0 ? (
            flights.map((flight, index) => (
              <FlightSearch key={index} flight={flight} />
            ))
          ) : (
            <p id="no-found">No flights found.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default Home;
