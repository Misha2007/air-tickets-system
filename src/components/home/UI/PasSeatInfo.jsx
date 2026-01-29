import { useState, useEffect } from "react";

const PasSeatInfo = ({ pWSeat, passengerData, setPassengerData }) => {
  const [baggage, setBaggage] = useState([]);

  useEffect(() => {
    const fetchBaggage = async () => {
      try {
        const res = await fetch("http://192.168.41.206:8081/baggage/all");
        const data = await res.json();
        setBaggage(data);
      } catch (err) {
        console.error(err);
      }
    };
    fetchBaggage();
  }, []);

  const handleChange = (field, value) => {
    setPassengerData({ ...passengerData, [field]: value });
  };

  return (
    <div className="passengers">
      <h2>Passenger #{pWSeat.passId} Info</h2>
      <h3>Seat: {pWSeat.sPlace}</h3>
      <div className="passenger-form">
        <div className="form-row">
          <input
            type="text"
            placeholder="First Name"
            value={passengerData.reisijaENimi}
            onChange={(e) => handleChange("reisijaENimi", e.target.value)}
          />
          <input
            type="text"
            placeholder="Last Name"
            value={passengerData.reisijaPNimi}
            onChange={(e) => handleChange("reisijaPNimi", e.target.value)}
          />
        </div>
        <div className="form-row">
          <input
            type="email"
            placeholder="Email"
            value={passengerData.email}
            onChange={(e) => handleChange("email", e.target.value)}
          />
          <input
            type="tel"
            placeholder="Phone Number"
            value={passengerData.telefon}
            onChange={(e) => handleChange("telefon", e.target.value)}
          />
        </div>
        <div className="form-row">
          <select
            value={passengerData.sugu}
            onChange={(e) => handleChange("sugu", e.target.value)}
          >
            <option value="">Gender</option>
            <option>Male</option>
            <option>Female</option>
          </select>
          <input
            type="date"
            value={passengerData.synniP}
            onChange={(e) => handleChange("synniP", e.target.value)}
          />
        </div>
        <div className="form-row">
          <select
            value={passengerData.countryId}
            onChange={(e) => handleChange("countryId", e.target.value)}
          >
            <option value="">Country</option>
            <option value="US">United States</option>
            <option value="EE">Estonia</option>
            <option value="FI">Finland</option>
            <option value="DE">Germany</option>
          </select>
          <input
            type="number"
            placeholder="National ID"
            value={passengerData.nationalId}
            onChange={(e) => handleChange("nationalId", e.target.value)}
          />
        </div>
        <div className="baggage-options">
          {baggage.map((b) => (
            <label
              key={b.id}
              className={
                passengerData.baggageId === b.id
                  ? "baggage-card selected"
                  : "baggage-card"
              }
            >
              <input
                type="radio"
                name={`baggage-${pWSeat.seat.id}`}
                value={b.id}
                checked={passengerData.baggageId === b.id}
                onChange={() => handleChange("baggageId", b.id)}
              />
              <div className="baggage-info">
                <h4>{b.pnimi}</h4>
                <p>Price: €{b.phind}</p>
                <p>Weight: {b.pkaal} kg</p>
                <p>
                  Dimensions: {b.ppikkus}*{b.plaius}*{b.pkõrgus} cm
                </p>
              </div>
            </label>
          ))}
        </div>
      </div>
    </div>
  );
};

export default PasSeatInfo;
