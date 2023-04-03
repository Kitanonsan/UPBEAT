import { useState, useEffect } from "react";
import axios from "axios";
import Link from "next/link";
import { useRouter } from "next/router";

export default function Start() {
  const router = useRouter();
  const { player } = router.query;

  const [inputValue, setInputValue] = useState("");

  useEffect(() => {
    const fetchConstructionPlan = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/construction-plan"
        );
        setInputValue(response.data.plan);
      } catch (error) {
        console.error(error);
      }
    };

    fetchConstructionPlan();
  }, []);

  const handleClick = async () => {
    try {
      const payload = {
        name: `Player${player}`,
        plan: inputValue,
      };

      const config = {
        headers: {
          "Content-Type": "application/json",
        },
      };

      await axios.post("http://localhost:8080/game/set", payload, config);
      console.log("Construction plan saved!");
      alert("Construction plan saved!");
    } catch (error) {
      console.error(error);
      alert("Failed to save construction plan.");
    }
  };

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  return (
    <div className="background-image">
      <div className="ConstructionPlan_Container">
        <div>
          <h1 className="Text_ConstructionPLan">
            Construction Plan for Player {player}
          </h1>
        </div>
        <div className="ConstructionPlanHelp">{/* <img src="/U.png" /> */}</div>
        <div style={{ position: "relative" }}>
          <textarea
            className="TextAreaConstructionPlan"
            placeholder="Enter your construction plan here..."
            value={inputValue}
            onChange={handleInputChange}
          ></textarea>
          <button
            className="Done_button_Con"
            style={{ position: "absolute", bottom: 5, right: 5 }}
            onClick={handleClick}
          >
            Done
          </button>
        </div>
        <div className="ButtonGroup">
          <Link href="Player">
            <button className="Back_button_Con">BACK</button>
          </Link>
          <Link
            href={{
              pathname: "/Game",
              query: { player, constructionPlan: inputValue },
            }}
            as={`/Game?player=${player}&constructionPlan=${encodeURIComponent(
              inputValue
            )}`}
          >
            <button className="Go_button_Con">GO</button>
          </Link>
        </div>
      </div>
      <style jsx>{`
        .background-image {
          background-image: url("/images/slide.gif");
          background-size: cover;
          background-position: center;
          height: 100%;
          width: 100%;
        }
      `}</style>
    </div>
  );
}
