import { useState, useEffect } from "react";
import axios from "axios";
import Link from "next/link";

export default function Setting() {
  const [inputValue, setInputValue] = useState("");
  const [constructionPlan, setConstructionPlan] = useState("");

  useEffect(() => {
    // Fetch the data from the JSON file
    const fetchData = async () => {
      try {
        const response = await axios.get("/api/read-file-IP");
        setInputValue(response.data.ipAddress); // Set the initial value here
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);

  const handleClick = async () => {
    try {
      await axios.post("/api/write-file-IP", { inputValue });
      console.log("Input saved!");
    } catch (error) {
      console.error(error);
    }
  };

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  return (
    <div className="ConstructionPlan_Container">
      <div>
        <h1 className="Text_IP"> ENTER IP ADDRESS</h1>
      </div>

      <div style={{ position: "relative" }}>
        <textarea
          className="InputIP"
          defaultValue={inputValue}
          onChange={handleInputChange}
        />
        <button
          className="Done_button_IP"
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
        <Link href="/Player">
          <button className="Go_button_Con">GO</button>
        </Link>
      </div>
    </div>
  );
}
