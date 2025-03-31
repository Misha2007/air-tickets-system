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
    }).format(date);
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
        <h1>{props.flight.from.code}</h1>
        <p className="bold">{props.flight.from.city}</p>
        <small>{formatDateTime(props.flight.from.dateTime)}</small>
      </div>
      <div className="form-group">
        <small>{props.flight.flightNumber}</small>
        <i className="fa fa-plane"></i>
        <span className="arrow">&#x2192;</span>
        <small>
          {calculateDuration(
            props.flight.from.dateTime,
            props.flight.to.dateTime
          )}
        </small>
      </div>
      <div className="form-group last-item">
        <p>To:</p>
        <h1>{props.flight.to.code}</h1>
        <p className="bold">{props.flight.to.city}</p>
        <small>{formatDateTime(props.flight.to.dateTime)}</small>
      </div>
      <div className="form-group">
        <p className="bold">Price from:</p>
        <p>{props.flight.price}</p>
        <button className="bold" onClick={() => props.openFlightHandler()}>
          Select
        </button>
      </div>
    </div>
  );
};

export default FlightSearch;
