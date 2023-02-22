import { useState } from "react";

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
    // Do something with the constructionPlan value, e.g. submit it to a server
    console.log("Construction plan submitted:", constructionPlan);
    setIsClicked(false);
    setConstructionPlan("");
  };

  return (
    <div className="form-wrapper">
      {isClicked ? (
        <form onSubmit={handleSubmit} className="form">
          <label className="label">
            <h3>Enter Construction Plan : </h3>
            <input
              type="text"
              value={constructionPlan}
              onChange={handleInputChange}
              className="input"
            />
          </label>
          <button type="submit" className="button">
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
