import { useState, useEffect } from "react";
import "./App.css";
import Preloader from "./components/preloader/Preloader";
import Home from "./components/home/Home";
import Header from "./components/header/Header";
import Flight from "./components/home/Flight";
import { Route, Routes } from "react-router-dom";

function App() {
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 5300);
  }, []);
  const selectedFlight = {
    id: 6,
    flightnumber: "QXX337",
    saabumiskoht: "Tallinn",
    sihtkoht: "Paris",
    saabumiskohtcode: "TLL",
    sihtkohtcode: "CDG",
    lahkumiseaeg: "2026-02-10 09:00:00",
    saabumiseaeg: "2026-02-10 10:30:00",
    hind: 32.29,
    istmed: new Array(30).fill(null),
  };

  return (
    <div className="main">
      <Header />

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/flight" element={<Flight flightId={1} persons={6} />} />
      </Routes>
      {loading && <Preloader></Preloader>}
    </div>
  );
}

export default App;
