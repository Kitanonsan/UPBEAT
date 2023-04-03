import { useState } from "react";
import axios from "axios";
import { useRouter } from "next/router";

export default function InputPlan() {
  const router = useRouter();
  const { constructionPlan } = router.query;
  const decodedConstructionPlan = decodeURIComponent(constructionPlan || "");

  const [inputValue, setInputValue] = useState(decodedConstructionPlan);
  const [CP, setCP] = useState(false);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
    setCP(true);
  };

  const handleClick = async () => {
    try {
      await axios.post("/api/write-file", { inputValue });
      console.log("Input saved!");
      alert("Construction plan saved!");
      setCP(false);
    } catch (error) {
      console.error(error);
      alert("Failed to save construction plan.");
    }
  };

  return (
    <div>
      <h1 style={{ color: "black" }} className="textplan">
        Construction Plan
      </h1>
      <textarea
        value={inputValue}
        onChange={handleInputChange}
        placeholder="Enter your construction plan here.. ."
        className="inputplan"
      />
      <button onClick={handleClick} className="saveplan">
        Save
      </button>
      <button onClick={handleClick} className="endturn">
        End turn
      </button>
      <div>
        {/* <img src="/images/Elfscale.gif" className="elfgame"></img> */}
        {/* <img src="/images/dwarfscale.gif" className="dwarfgame"></img> */}
      </div>
    </div>
  );
}
