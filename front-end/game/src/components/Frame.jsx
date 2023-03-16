import React from "react";
import Hexgrid from "./Hex";

function Frame() {
  const frameStyle = {
    width: "95%",
    height: "95%",
    border: "2px solid #000",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    overflow: "auto",
    marginTop: "25px",
  };

  return (
    <div style={frameStyle}>
      <Hexgrid />
    </div>
  );
}

export default Frame;
