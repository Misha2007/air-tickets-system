import { useState, useEffect, useRef } from "react";
import "./Home.css";
import FlightSearch from "./FlightSearch";
import Flight from "./Flight";
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
const apiUrl = import.meta.env.VITE_API_URL;

const Home = () => {
  const [flights, setFlights] = useState([]);
  const [openFlight, setOpenFlight] = useState(false);
  const [selectedFlightId, setSelectedFlightId] = useState(null);
  const [locations2, setLocations2] = useState([]);
  const [filteredLocations, setFilteredLocations] = useState([]);
  const [showDropdown, setShowdown] = useState(false);
  const [showDropdown2, setShowdown2] = useState(false);
  const [date, setDate] = useState(new Date());

  // const suggestions = ["London", "Tallinn", "Paris"];

  const [suggestions, setsuggestions] = useState([]);
  const [suggestionsTo, setTosuggestions] = useState([]);
  const [canFrom, setcanFrom] = useState(false);
  const [saveDate, setsaveDate] = useState([]);

  // First
  const [isHovered, setIsHovered] = useState(false);
  const inputRef = useRef();
  const [inputValue, setInputValue] = useState("Tallinn");
  const [isFocus, setIsFocus] = useState(false);

  // Second
  const [inputValue2, setInputValue2] = useState("");
  const [isHovered2, setIsHovered2] = useState(false);
  const inputRef2 = useRef();
  const [isFocus2, setIsFocus2] = useState(false);

  const [availableDates, setAvailableDates] = useState([]);

  const [formData, setFormData] = useState({
    arrival: "",
    destination: "",
    date: "",
    persons: 1,
  });

  /* not in use right now
  const DateCall = async () => {
    const response = await fetch(`${apiUrl}flights/date`, {
      method: "GET",
    }).then((response) => response.json());

    if (response.ok) {
      console.log("Yipeee, it worked, this is the response", data);
    }
  };

  */
  // Trying to find all of the flights that are starting from a certain place
  const SaabumiskohtCall = async () => {
    const response = await fetch(`${apiUrl}flights/from`, {
      method: "GET",
    });

    if (response.ok) {
      console.log("From is working, this is the response");
      const data = await response.json();
      setsuggestions(data);
    }

    if (!response.ok) {
      console.error("Failed at fetching the saabumis");
    }
  };

  useEffect(() => {
    SaabumiskohtCall();
  }, []);

  const ToCall = async () => {
    const ToParams = new URLSearchParams({
      Saabumiskoht: inputValue,
    }).toString();

    console.log("Tell me it is working");
    try {
      console.log(inputValue);
      const response = await fetch(`${apiUrl}flights/to?${ToParams}`, {
        method: "GET",
      });

      if (response.ok) {
        console.log("From is working, this is the response");
        const data = await response.json();
        setTosuggestions(data);
      }

      if (!response.ok) {
        console.log(response);
      }
    } catch (error) {
      console.error(" Error  fetching flights:", error);
    }
  };

  const FromCall = async () => {
    const ToParams = new URLSearchParams({
      Saabumiskoht: inputValue,
      Sihtkoht: inputValue2,
    }).toString();

    console.log("Tell me it is working");
    try {
      console.log(inputValue);
      const response = await fetch(`${apiUrl}flights/destination?${ToParams}`, {
        method: "GET",
      });

      if (response.ok) {
        const data = await response.json();
        console.log("From is from working", data);
        setAvailableDates(data.map((dateStr) => new Date(dateStr)));
      }

      if (!response.ok) {
        console.log(response);
      }
    } catch (error) {
      console.error(" Error  fetching flights:", error);
    }
  };

  //   if (response.ok) {
  //     console.log("From is working, this is the response");
  //     const data = await response.json();
  //     setsuggestions(data);
  //   }

  //   if (!response.ok) {
  //     console.error("Failed at fetching the saabumis");
  //   }
  // };

  useEffect(() => {
    // This runs when the input field has been changed
    if (suggestions.includes(inputValue)) {
      setcanFrom(true);
      ToCall();
      console.log("The useEffect clause ran");
    }
  }, [inputValue, suggestions]);

  useEffect(() => {
    if (canFrom && suggestionsTo.includes(inputValue2)) {
      FromCall();
      console.log("The from useeffect ran");
    }
  }, [inputValue2, suggestionsTo]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  ///////////////////////////////////////
  const searchFlights = async (e) => {
    e.preventDefault();

    const queryParams = new URLSearchParams({
      Saabumiskoht: inputValue || "",
      Sihtkoht: inputValue2 || "",
      LahkumiseAeg: date.toISOString().split("T")[0] || "",
      Arv: formData.persons || 1,
    }).toString();

    try {
      console.log(queryParams);
      const response = await fetch(`${apiUrl}flights/filtered?${queryParams}`);
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

  // useEffect(() => {
  //   async function loadLocations() {
  //     try {
  //       const response = await fetch("${apiUrl}cities/all");
  //       const data = await response.json();

  //       setLocations2(data.map((item) => item.linnNimi));
  //       setShowdown(true);
  //       console.log("What is up guys");
  //     } catch (error) {
  //       console.error("Failed to load locations:", error);
  //     }
  //   }
  //   loadLocations();
  // }, []);
  // -----------------------------------------------------------

  return (
    <div id="page">
      {openFlight ? (
        <Flight flight={selectedFlightId} persons={formData.persons} />
      ) : (
        <div>
          <div id="main-page">
            <div className="form-container">
              <form onSubmit={searchFlights}>
                {/* <div className="form-group" style={{ position: "relative" }}>
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
                </div> */}

                {/* <label htmlFor="destination">To:</label>
                <input
                  id="destination"
                  type="text"
                  value={formData.destination}
                  onChange={handleChange}
                /> */}

                {/* Part for implementing the suggestive input field: https://github.com/Chensokheng/react-auto-suggestion/blob/master/src/App.js */}
                <div className="form-group">
                  <label htmlFor="haventdecided_yet">From: </label>
                  <input
                    className="w-full focus:outline-none border-2 p-5"
                    placeholder="Enter your desired location"
                    onFocus={() => setIsFocus(true)}
                    onBlur={() => {
                      if (!isHovered) {
                        setIsFocus(false);
                      }
                    }}
                    value={inputValue}
                    onChange={(e) => {
                      setInputValue(e.target.value);
                    }}
                    ref={inputRef}
                  />
                  {isFocus && (
                    <div
                      className="shadow-lg absolute w-full"
                      onMouseEnter={() => {
                        setIsHovered(true);
                      }}
                      onMouseLeave={() => {
                        setIsHovered(false);
                      }}
                    >
                      {suggestions.map((suggestion, index) => {
                        const isMatch =
                          suggestion
                            .toLowerCase()
                            .indexOf(inputValue.toLowerCase()) > -1;
                        return (
                          <div key={index}>
                            {isMatch && (
                              <div
                                className="p-5 hover:bg-gray-200 cursor-pointer"
                                onClick={() => {
                                  setInputValue(suggestion);
                                  inputRef.current.focus();
                                }}
                              >
                                {suggestion}
                              </div>
                            )}
                          </div>
                        );
                      })}
                    </div>
                  )}
                </div>

                {/* Other input field should go here*/}
                <div className="form-group">
                  <label>To: </label>
                  <input
                    className="w-full focus:outline-none border-2 p-5"
                    placeholder="Enter your desired destination"
                    onFocus={() => setIsFocus2(true)}
                    onBlur={() => {
                      if (!isHovered2) {
                        setIsFocus2(false);
                      }
                    }}
                    value={inputValue2}
                    onChange={(e) => {
                      setInputValue2(e.target.value);
                    }}
                    ref={inputRef2}
                  />
                  {isFocus2 && (
                    <div
                      className="shadow-lg absolute w-full"
                      onMouseEnter={() => {
                        setIsHovered2(true);
                      }}
                      onMouseLeave={() => {
                        setIsHovered2(false);
                      }}
                    >
                      {suggestionsTo.map((suggestionTo, index) => {
                        const isMatch =
                          suggestionTo
                            .toLowerCase()
                            .indexOf(inputValue2.toLowerCase()) > -1;
                        return (
                          <div key={index}>
                            {isMatch && (
                              <div
                                className="p-5 hover:bg-gray-200 cursor-pointer"
                                onClick={() => {
                                  setInputValue2(suggestionTo);
                                  inputRef2.current.focus();
                                }}
                              >
                                {suggestionTo}
                              </div>
                            )}
                          </div>
                        );
                      })}
                    </div>
                  )}
                </div>

                <div className="form-group">
                  <label htmlFor="date">Date:</label>

                  <DatePicker
                    selected={date}
                    includeDates={availableDates}
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
              {console.log(formData.persons)}
              {flights.map((flight, index) => (
                <FlightSearch
                  key={index}
                  flight={flight}
                  persons={formData.persons}
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
