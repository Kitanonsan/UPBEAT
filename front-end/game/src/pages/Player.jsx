import Link from "next/link";

export default function Player() {
  return (
    <div className="Player_container">
      <div className="PlayerSelect_buttons">
        <button className="PlayerSelect_button_left">Player 1</button>
        <div />
        <button className="PlayerSelect_button_right">Player 2</button>
      </div>
      <Link href="ConstructionPlanPage">
        <button className="Go_set_constructionplan_button">
          Set Construction Plan
        </button>
      </Link>
      <Link href="/">
        <button className="BacktoIPpage">Back</button>
      </Link>
    </div>
  );
}
