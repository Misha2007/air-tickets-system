import { useState, useEffect, useRef } from "react";

const PasSeatInfo = ({ pWSeat, createPassengerBooking }) => {
  console.log(pWSeat);
  const [selectedBaggageId, setSelectedBaggageId] = useState(1);
  const [baggage, setBaggage] = useState([]);
  const firstNameRef = useRef();
  const lastNameRef = useRef();
  const emailRef = useRef();
  const phoneNumRef = useRef();
  const genderRef = useRef();
  const birthRef = useRef();
  const countryIdRef = useRef();
  const nationalIdRef = useRef();

  const onSubmit = (event) => {
    const enteredFirstName = firstNameRef.current.value;
    const enteredLastEmail = lastNameRef.current.value;
    const enteredEmail = emailRef.current.value;
    const enteredPhoneNum = phoneNumRef.current.value;
    const enteredBirth = birthRef.current.value;
    const enteredGender = genderRef.current.value;
    const enteredCountryId = countryIdRef.current.value;
    const enteredNationalId = nationalIdRef.current.value;
    event.preventDefault();

    const passenger = {
      reisijaENimi: enteredFirstName,
      reisijaPNimi: enteredLastEmail,
      synniP: enteredBirth,
      telefon: enteredPhoneNum,
      email: enteredEmail,
      sugu: enteredGender,
      countryId: enteredCountryId,
      nationalId: enteredNationalId,
    };

    createPassengerBooking(passenger);
  };

  useEffect(() => {
    const dataFetch = async () => {
      try {
        const response = await fetch(`http://192.168.41.206:8081/baggage/all`, {
          method: "GET",
          headers: {
            "Access-Control-Allow-Origin": "*",
          },
        });
        const data = await response.json();
        setBaggage(data);
      } catch (err) {
        console.error(err);
      }
    };
    dataFetch();
  }, []);

  return (
    <div className="seat-booking">
      <div className="passengers">
        <h2>Passenger #{pWSeat.passId} Information</h2>
        <h3>Seat number: {pWSeat.sPlace}</h3>
        <form className="passenger-form" onSubmit={onSubmit}>
          <div className="form-row">
            <input type="text" placeholder="First Name" ref={firstNameRef} />
            <input type="text" placeholder="Last Name" ref={lastNameRef} />
          </div>
          <div className="form-row">
            <input type="email" placeholder="Email Address" ref={emailRef} />
            <input type="tel" placeholder="Phone Number" ref={phoneNumRef} />
          </div>
          <div className="form-row">
            <select ref={genderRef}>
              <option value="">Gender</option>
              <option>Male</option>
              <option>Female</option>
            </select>

            <input type="date" ref={birthRef} />
          </div>
          <div className="form-row">
            <select ref={countryIdRef}>
              <option value="">ID Country</option>
              <option value="US">United States</option>
              <option value="EE">Estonia</option>
              <option value="FI">Finland</option>
              <option value="DE">Germany</option>
            </select>

            <input
              ref={nationalIdRef}
              type="number"
              placeholder="National ID / SSN / Isikukood"
            />
          </div>
          Baggage:
          <div className="baggage-options">
            {baggage.map((b) => (
              <label
                key={b.id}
                className={
                  selectedBaggageId === b.id
                    ? "baggage-card selected"
                    : "baggage-card"
                }
              >
                <input
                  type="radio"
                  name="baggage"
                  value={b.id}
                  checked={selectedBaggageId === b.id}
                  onChange={() => setSelectedBaggageId(b.id)}
                />

                <div className="baggage-info">
                  <h4>{b.pnimi}</h4>

                  <p>
                    <strong>Price:</strong> €{b.phind}
                  </p>
                  <p>
                    <strong>Weight:</strong> {b.pkaal} kg
                  </p>

                  <p>
                    <strong>Dimensions:</strong> {b.ppikkus} * {b.plaius} *{" "}
                    {b.pkõrgus} cm
                  </p>
                </div>
              </label>
            ))}
            <button type="submit">Submit</button>
          </div>
        </form>
      </div>

      <div className="seat-details">
        <h2>Seat Details</h2>

        <ul>
          <li>
            <strong>Seat Class:</strong> {pWSeat.seat.klass}
          </li>
          <li>
            <strong>Extra Legroom:</strong>{" "}
            {pWSeat.seat.jalaRuum ? "Yes" : "No"}
          </li>
          <li>
            <strong>Near Emergency Exit:</strong>{" "}
            {pWSeat.seat.lValjapaas ? "Yes" : "No"}
          </li>
          <li>
            <strong>Seat Position:</strong> Window
          </li>
        </ul>
      </div>
    </div>
  );
};

export default PasSeatInfo;
