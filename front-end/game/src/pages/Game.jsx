import React, { useEffect, useState } from "react";
import BaseLayout from "@/components/BaseLayout";
import Hexgrid from "@/components/Hex";
import InputPlan from "../components/Input";
import Timer from "../components/Timer";
import Frame from "../components/Frame";
import CodeEditor from "../components/editor";

export default function Home() {
  const [socket, setSocket] = useState(null);

  useEffect(() => {
    const newSocket = new WebSocket("ws://10.10.186.240:8080/group19");
    newSocket.addEventListener("open", () => {
      console.log("WebSocket connected");
      setSocket(newSocket);
    });

    return () => newSocket.close();
  }, []);

  const onSocketConnected = () => {
    console.log("Socket connected");
    // Add any logic you want to run when the socket is connected to the backend
  };

  const onSocketDisconnected = () => {
    console.log("Socket disconnected");
    // Add any logic you want to run when the socket is disconnected from the backend
  };

  return (
    <div className="backofgame">
      <BaseLayout>
        <Frame />
        <Timer />
        {/* <Hexgrid /> */}
        <InputPlan socket={socket} />
        {/* <CodeEditor /> */}
      </BaseLayout>

      <style jsx>{`
        .backofgame {
          // background-image: url("/images/test wallpaper.png");
          background-image: url("/images/flower.png");
          background-size: cover;
          background-position: center;
        }
      `}</style>
    </div>
  );
}
