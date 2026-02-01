import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const { addDays } = "21.01.2026";

const IncludeDates = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const includeDates = [new Date(), addDays(new Date(), 1)];
  return (
    <DatePicker
      selected={selectedDate}
      onChange={handleChange}
      includeDates={includeDates}
      placeholderText="Select date..."
    />
  );
};

export default IncludeDates;
