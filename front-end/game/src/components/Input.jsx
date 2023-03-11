import { useState } from "react";
import axios from "axios";

export default function InputPlan() {
  const [inputValue, setInputValue] = useState("");

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const handleClick = async () => {
    try {
      await axios.post("/api/write-file", { inputValue });
      console.log("Input saved!");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Construction Plan</h1>
      <textarea
        value={inputValue}
        onChange={handleInputChange}
        placeholder="Enter your construction plan here..."
        className="inputplan"
      />
      <button onClick={handleClick} className="saveplan">
        Save
      </button>
    </div>
  );
}
