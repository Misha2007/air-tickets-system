import { useState } from "react";
import "./Home.css";
import FlightSearch from "./FlightSearch";
import Flight from "./Flight";

const Home = () => {
  const [formData, setFormData] = useState({
    arrival: "",
    destination: "",
    date: "",
    persons: 1,
  });

  const [flights, setFlights] = useState([]);
  const [openFlight, setOpenFlight] = useState(false);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  const searchFlights = async (e) => {
    e.preventDefault();

    const queryParams = new URLSearchParams({
      Saabumiskoht: formData.arrival || "",
      Sihtkoht: formData.destination || "",
      LahkumiseAeg: formData.date || "",
      Arv: formData.persons || 1,
    }).toString();

    try {
      console.log(queryParams);
      const response = await fetch(
        `http://localhost:8090/flights/filtered?${queryParams}`
      );
      if (!response.ok) {
        console.log(response);
      }

      const filteredFlights = await response.json();
      setFlights(filteredFlights);
      console.log(filteredFlights);
    } catch (error) {
      console.error("Error fetching flights:", error);
    }
  };

  const openFlightHandler = () => {
    setOpenFlight(!openFlight);
  };

  return (
    <div id="page">
      {openFlight ? (
        <Flight />
      ) : (
        <div>
          <div id="main-page">
            <div className="form-container">
              <form onSubmit={searchFlights}>
                <div className="form-group">
                  <label htmlFor="arrival">Arrival:</label>
                  <input
                    id="arrival"
                    type="text"
                    value={formData.arrival}
                    onChange={handleChange}
                  />
                </div>

                <div className="form-group">
                  <label htmlFor="destination">Destination:</label>
                  <input
                    id="destination"
                    type="text"
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

                <button type="submit">Search</button>
              </form>
            </div>
          </div>

          {flights.length > 0 ? (
            <div className="main-flight-search">
              {flights.map((flight, index) => (
                <FlightSearch
                  key={index}
                  flight={flight}
                  openFlightHandler={openFlightHandler}
                />
              ))}
            </div>
          ) : (
            <p id="no-found">No flights found.</p>
          )}
        </div>
      )}
    </div>
  );
};

export default Home;
