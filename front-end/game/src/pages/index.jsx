import Link from "next/link";
import { useEffect, useState } from "react";
import { Client } from "@stomp/stompjs";

let client;
export default function Start() {
  const start = async () => {
    try {
      const response = await fetch("http://10.10.186.240:8080/game/start", {
        method: "POST",
      });

      if (!response.ok) {
        throw new Error("Failed to start the game");
      }
    } catch (error) {
      console.error("Error starting the game:", error);
    }
  };

  return (
    <div className="StartGame_Container">
      <Link href="/Player">
        <button
          style={{ backgroundColor: "transparent", border: "none" }}
          className="StartGame_button"
          onClick={start}
        >
          UPbeat
        </button>
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
