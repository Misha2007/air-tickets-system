import "./Flight.css";
import { useState, useEffect } from "react";
import PasSeatInfo from "./UI/PasSeatInfo";

const SelectedSeats = (props) => {
  const columns = ["A", "B", "C", "D"];
  console.log(props.passengerWSeat);
  //   useEffect(() => {
  //     if (props.flightId) {
  //       fetch(`http://192.168.41.206:8081/seats/flight/${props.flightId}`, {
  //         method: "GET",
  //         headers: {
  //           "Access-Control-Allow-Origin": "*",
  //         },
  //       })
  //         .then((response) => response.json())
  //         .then((data) => {
  //           console.log(data);
  //           setSeats(data);
  //           setRows(Math.ceil(data.length / columns.length));
  //           const preselected = {};

  //           for (let index = 0; index < props.persons; index++) {
  //             const seat = data
  //               .filter((seat) => seat.vaba)
  //               .slice(index, index + 1)
  //               .map((seat) => {
  //                 const seatIndex = seat.number - 1;
  //                 const row = Math.floor(seatIndex / columns.length) + 1;
  //                 const col = columns[seatIndex % columns.length];
  //                 return `${row}${col}`;
  //               })[0];
  //             preselected[index + 1] = seat;
  //           }
  //           setSelectedSeats(preselected);
  //         })
  //         .catch((error) => console.error("Error fetching seats:", error));
  //     }
  //   }, [props.flightId]);

  return (
    <div>
      {props.passengerWSeat.map((pWSeat, index) => (
        <PasSeatInfo pWSeat={pWSeat} key={index} />
      ))}
    </div>
  );
};

export default SelectedSeats;
