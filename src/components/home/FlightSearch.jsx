import "./FlightSearch.css";

const FlightSearch = ({ flight }) => {
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
        <h1>{flight.from.code}</h1>
        <p className="bold">{flight.from.city}</p>
        <small>{formatDateTime(flight.from.dateTime)}</small>
      </div>
      <div className="form-group">
        <small>{flight.flightNumber}</small>
        <i className="fa fa-plane"></i>
        <span className="arrow">&#x2192;</span>
        <small>
          {calculateDuration(flight.from.dateTime, flight.to.dateTime)}
        </small>
      </div>
      <div className="form-group last-item">
        <p>To:</p>
        <h1>{flight.to.code}</h1>
        <p className="bold">{flight.to.city}</p>
        <small>{formatDateTime(flight.to.dateTime)}</small>
      </div>
      <div className="form-group">
        <p className="bold">Price from:</p>
        <p>{flight.price}</p>
        <button className="bold">Select</button>
      </div>
    </div>
  );
};

export default FlightSearch;
