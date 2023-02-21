import { useState } from "react";
import axios from "axios";
import Link from "next/link";

export default function Start() {
  const [inputValue, setInputValue] = useState("");

  const handleClick = async () => {
    try {
      await axios.post("/api/write-file", { inputValue });
      console.log("Input saved!");
    } catch (error) {
      console.error(error);
    }
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setInputValue(event.target.value);
  };

  return (
    <div className="ConstructionPlan_Container">
      <div>
        <h1 className="Text_ConstructionPLan">Construction Plan</h1>
      </div>
      <div className="ConstructionPlanHelp">{/* <img src="/U.png" /> */}</div>
      <div style={{ position: "relative" }}>
        <input
          className="InputConstructionPlan"
          value={inputValue}
          onChange={handleInputChange}
        />
        <button
          className="Done_button_Con"
          style={{ position: "absolute", bottom: 5, right: 5 }}
          onClick={handleClick}
        >
          Done
        </button>
      </div>
      <div className="ButtonGroup">
        <Link href="/">
          <button className="Back_button_Con">BACK</button>
        </Link>
        <Link href="/Game">
          <button className="Go_button_Con">GO</button>
        </Link>
      </div>
    </div>
  );
}
