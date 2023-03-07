import Link from "next/link";

export default function Start() {
  return (
    <div className="StartGame_Container">
      <div className="Center_startGame">
        <img src="/U.png" />
      </div>
      <Link href="Setting">
        <button className="StartGame_button">Start Game</button>
      </Link>
    </div>
  );
}
