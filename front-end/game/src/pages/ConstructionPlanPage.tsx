import Link from "next/link";

export default function Start() {
  return (
    <div className="ConstructionPlan_Container">
      <div>
        <h1 className="Text_ConstructionPLan">Construction Plan</h1>
      </div>
      <div className="ConstructionPlanHelp">{/* <img src="/U.png" /> */}</div>
      <input className="InputConstructionPlan" />
      <div className="ButtonGroup">
        <Link href="/StartGamePage">
          <button className="Back_button_Con">BACK</button>
        </Link>
        <Link href="/">
          <button className="Go_button_Con">GO</button>
        </Link>
      </div>
    </div>
  );
}
