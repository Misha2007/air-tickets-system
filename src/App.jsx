import { useState, useEffect } from "react";
import "./App.css";
import Preloader from "./components/preloader/Preloader";
import Home from "./components/home/Home";
import Header from "./components/header/Header";

function App() {
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 5300);
  }, []);

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
