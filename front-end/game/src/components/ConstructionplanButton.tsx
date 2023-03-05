import { useState } from "react";
import axios from "axios";
import Inputform from "./inputform";

export default function ConstructionButton() {
  const [isClicked, setIsClicked] = useState(false);
  const [constructionPlan, setConstructionPlan] = useState("");

  const handleButtonClick = () => {
    setIsClicked(true);
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const newConstructionPlan = event.target.value;
    setConstructionPlan(newConstructionPlan);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // Send the construction plan to the server to write it to a file
    axios
      .post("/api/write-construction-plan", { inputValue: constructionPlan })
      .then((response) => {
        console.log(response.data);
        setIsClicked(false);
        setConstructionPlan("");
      })
      .catch((error) => {
        console.error(error);
      });
  };

  return (
    <div className="form-wrapper">
      {isClicked ? (
        <form onSubmit={handleSubmit} className="form">
          <label className="label">
            <div>
              <h3>Enter Construction Plan : </h3>
              <input
                type="text"
                value={constructionPlan}
                onChange={handleInputChange}
                className="input"
              />

              {/* <Inputform /> */}
            </div>
          </label>
          <button type="submit" className="submit-button">
            Submit
          </button>
        </form>
      ) : (
        <button className="Construction-button" onClick={handleButtonClick}>
          Change Construction Plan
        </button>
      )}
    </div>
  );
}
