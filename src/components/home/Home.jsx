import { useState, useEffect } from "react";
import "./Home.css";
import FlightSearch from "./FlightSearch";
import Flight from "./Flight";
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";

const Home = () => {
  const [flights, setFlights] = useState([]);
  const [openFlight, setOpenFlight] = useState(false);
  const [selectedFlightId, setSelectedFlightId] = useState(null);
  const [locations2, setLocations2] = useState([]);
  const [filteredLocations, setFilteredLocations] = useState([]);
  const [showDropdown, setShowdown] = useState(false);
  const [showDropdown2, setShowdown2] = useState(false);
  const [date, setDate] = useState(new Date());

  const [textData, setTextData] = useState({ text: "" });

  const [formData, setFormData] = useState({
    arrival: "",
    destination: "",
    date: "",
    persons: 1,
  });

  const DateCall = async () => {
    const response = await fetch(`http://192.168.41.206:8081/flights/date`, {
      method: "GET",
    }).then((response) => response.json());

    if (response.ok) {
      console.log("Yipeee, it worked, this is the response", data);
    }
  };

  // Trying to find all of the flights that are starting from a certain place
  const SaabumiskohtCall = async () => {
    const response = await fetch(`http://192.168.41.206:8081/flights/from`, {
      method: "GET",
    }).then((response) => response.json());

    if (response.ok) {
      console.log("From is working, this is the response", data);
    }
  };

  useEffect(() => {}, [DateCall]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  // Searching for cities, when user enters text into input field
  const searchCities = async (e) => {
    e.preventDefault();

    const textParams = new URLSearchParams({
      text: textData.text || "",
    }).toString();

    try {
      console.log(queryParams);
      const response = await fetch(
        `http://192.168.41.206:8081/city/text?${textParams}`,
      );
      if (!response.ok) {
        console.log(response);
      }

      const matchedCities = await response.json();
      console.log(matchedCities);
    } catch (error) {
      console.error(" Error  fetching flights:", error);
    }
  };

  ///////////////////////////////////////
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
        `http://192.168.41.206:8081/flights/filtered?${queryParams}`,
      );
      if (!response.ok) {
        console.log(response);
      }

      const filteredFlights = await response.json();
      setFlights(filteredFlights);
      console.log(filteredFlights);
    } catch (error) {
      console.error(" Error  fetching flights:", error);
    }
  };

  const openFlightHandler = (flight) => {
    setSelectedFlightId(flight);
    setOpenFlight(true);
    console.log(flight);
  };

  let locations = [];

  useEffect(() => {
    async function loadLocations() {
      try {
        const response = await fetch("http://192.168.41.206:8081/cities/all");
        const data = await response.json();

        setLocations2(data.map((item) => item.linnNimi));
        setShowdown(true);
        console.log("What is up guys");
      } catch (error) {
        console.error("Failed to load locations:", error);
      }
    }
    loadLocations();
  }, []);
  // -----------------------------------------------------------
  const handleArrivalChange = (e) => {
    const value = e.target.value;

    setFormData((prev) => ({
      ...prev,
      arrival: value,
    }));

    setTextData((prev) => ({
      ...prev,
      text: value,
    }));

    if (!value) {
      setShowDropdown(false);
      return;
    }

    const matches = locations.filter((loc) =>
      loc.toLowerCase().includes(value.toLowerCase()),
    );

    setFilteredLocations(matches);
    setShowDropdown(true);
  };

  // For closing the form
  const selectArrival = (location) => {
    setFormData((prev) => ({
      ...prev,
      arrival: location,
    }));

    setShowDropdown(false);
  };

  const selectDestination = (location) => {
    setFormData((prev) => ({
      ...prev,
      destination: location,
    }));

    setShowDropdown2(false);
  };

  return (
    <div id="page">
      {openFlight ? (
        <Flight flight={selectedFlightId} persons={formData.persons} />
      ) : (
        <div>
          <div id="main-page">
            <div className="form-container">
              <form onSubmit={searchFlights}>
                <div className="form-group" style={{ position: "relative" }}>
                  <label htmlFor="arrival">From:</label>
                  <input
                    id="arrival"
                    type="text"
                    value={formData.arrival}
                    onChange={handleArrivalChange}
                    autoComplete="off"
                  />

                  {showDropdown && (
                    <ul className="dropdown">
                      {locations2.map((loc, index) => (
                        <li key={index} onClick={() => selectArrival(loc)}>
                          {loc}
                        </li>
                      ))}
                    </ul>
                  )}
                </div>

                <div className="form-group">
                  <label htmlFor="destination">To:</label>
                  <input
                    id="destination"
                    type="text"
                    value={formData.destination}
                    onChange={handleChange}
                  />

                  {showDropdown2 && (
                    <ul className="dropdown">
                      {locations2.map((loc, index) => (
                        <li key={index} onClick={() => selectDestination(loc)}>
                          {loc}
                        </li>
                      ))}
                    </ul>
                  )}
                </div>

                <div className="form-group">
                  <label htmlFor="date">Date:</label>
                  <input
                    id="date"
                    value={formData.date}
                    onChange={handleChange}
                  />
                  <DatePicker
                    selected={date}
                    includeDates={[
                      new Date("2026-01-22"),
                      new Date("2026-01-21"),
                      new Date("2026-02-10"),
                      new Date("2026-10-02"),
                    ]}
                    onChange={(date) => setDate(date)}
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
                  openFlightHandler={() => openFlightHandler(flight)}
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
