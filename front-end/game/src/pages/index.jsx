import Link from "next/link";

export default function Start() {
  return (
    <div className="StartGame_Container">
      {/* <div className="Center_startGame">
        <img src="/images/Upbeat.png" />
      </div> */}
      <Link href="Player">
        <h1 className="StartGame_button">UPbeat</h1>
      </Link>
      <style jsx>{`
        .StartGame_Container {
          background-image: url("/images/Side_View_Scene.gif");
          background-size: 100% 100%;
          background-position: center;
        }
      `}</style>
    </div>
  );
}
