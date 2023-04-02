import Link from "next/link";
import { useEffect } from "react";
import { Client } from "@stomp/stompjs";

let client;
export default function Start() {
  useEffect(() => {
    if (!client) {
      client = new Client({
        brokerURL: "ws://10.10.186.240:8080/group19",
        onConnect: () => {
          client.subscribe("/app/start", (message) => {
            const body = JSON.parse(message.body);
            // do something with the message body
          });
        },
      });

      client.activate();
    }
  }, []);

  const start = () => {
    if (client) {
      if (client.connected) {
        client.publish({
          destination: "/app/start",
          body: JSON.stringify({}),
        });
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
