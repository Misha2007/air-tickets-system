import { useState, useEffect } from "react";
import "./App.css";
import Preloader from "./components/preloader/Preloader";

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
      {!loading && <div className="main-page"></div>}
    </div>
  );
}

export default App;
