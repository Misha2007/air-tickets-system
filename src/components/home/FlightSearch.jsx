import "./FlightSearch.css";
import Flight from "./Flight";

const FlightSearch = (props) => {
  const formatDateTime = (date) => {
    return new Intl.DateTimeFormat("en-GB", {
      year: "numeric",
      month: "short",
      day: "numeric",
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    }).format(new Date(date));
  };

  const calculateDuration = (departure, arrival) => {
    const diffMs = new Date(arrival) - new Date(departure);
    const hours = Math.floor(diffMs / (1000 * 60 * 60));
    const minutes = Math.floor((diffMs % (1000 * 60 * 60)) / (1000 * 60));
    return `${hours}h ${minutes}m`;
  };

  return (
    <div className="flight">
      <div className="form-group">
        <p>From:</p>
        <h1>{props.flight.saabumiskohtcode}</h1>
        <p className="bold">{props.flight.saabumiskoht}</p>
        <small>{formatDateTime(props.flight.lahkumiseaeg)}</small>
      </div>
      <div className="form-group">
        <small>{props.flight.flightnumber}</small>
        <i className="fa fa-plane"></i>
        <span className="arrow">&#x2192;</span>
        <small>
          {calculateDuration(
            props.flight.lahkumiseaeg,
            props.flight.saabumiseaeg
          )}
        </small>
      </div>
      <div className="form-group last-item">
        <p>To:</p>
        <h1>{props.flight.sihtkohtcode}</h1>
        <p className="bold">{props.flight.sihtkoht}</p>
        <small>{formatDateTime(props.flight.saabumiseaeg)}</small>
      </div>
      <div className="form-group">
        <p className="bold">Price from:</p>
        <p>{props.flight.hind}</p>
        <button className="bold" onClick={() => props.openFlightHandler()}>
          Select
        </button>
      </div>
    </div>
  );
};

export default FlightSearch;
