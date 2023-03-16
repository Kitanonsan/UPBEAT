import Link from "next/link";

export default function Player() {
  return (
    <div className="background-image">
      <div>
        <h1 className="selectplayer">Select Player</h1>
        <div className="Player_container">
          <div className="PlayerSelect_buttons">
            <img src="/images/Elfscale.gif" className="Elf"></img>
            <Link href="ConstructionPlanPage?player=1">
              <button className="PlayerSelect_button_left">Player 1</button>
            </Link>
            {/* <img src="/images/Demon1.gif" className="Player1"></img> */}
            <div />
            <img src="/images/draftscale.gif" className="dwarf"></img>
            <Link href="ConstructionPlanPage?player=2">
              <button className="PlayerSelect_button_right">Player 2</button>
            </Link>
          </div>
          <Link href="/">
            <button className="Backtoindexpage">Back</button>
          </Link>
        </div>
      </div>
      <style jsx>{`
        .background-image {
          background-image: url("/images/Top_Down_Scene.gif");
          background-size: cover;
          background-position: center;
          height: 100%;
          width: 100%;
        }
      `}</style>
    </div>
  );
}
