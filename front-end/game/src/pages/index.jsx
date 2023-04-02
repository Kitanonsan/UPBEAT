import Link from "next/link";
import { useEffect } from "react";
import { Client } from "@stomp/stompjs";

let client;
let a = 0;
export default function Start() {
  useEffect(() => {
    if (!client) {
      client = new Client({
        brokerURL: "ws://localhost:8080/group19",
        onConnect: () => {
          client.subscribe("/app/text", (message) => {
            const body = JSON.parse(message.body);
            console.log(body);
            // do something with the message body
          });
          client.subscribe("/topic/text", (message) => {
            const body = JSON.parse(message.body);
            // do something with the message body
            console.log(body);
          });
        },
      });

      client.activate();
    }
  }, [a]);

  const start = () => {
    if (client) {
      console.log("Click");
      if (client.connected) {
        client.publish({
          destination: "/app/text",
          body: JSON.stringify({ text: "Hello World!" }),
        });
        a = a + 1;
      }
    }
  };

  return (
    <div className="StartGame_Container">
      <Link href="/Player">
        <button className="StartGame_button" onClick={start}>
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
