import { useState, useEffect } from "react";
import "./App.css";
import Preloader from "./components/preloader/Preloader";
import Home from "./components/home/Home";
import Header from "./components/header/Header";
import Flight from "./components/home/Flight";

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
    lahkumiseaeg: "2025-04-09T09:00:00",
    saabumiseaeg: "2025-04-09T10:30:00",
    hind: 32.29,
    istmed: new Array(30).fill(null),
  };

  return (
    <div className="main">
      {loading && <Preloader></Preloader>}
      {!loading && (
        <div className="main-header">
          <Header />
          <Home></Home>
        </div>
      )}
    </div>
  );
}

export default App;
