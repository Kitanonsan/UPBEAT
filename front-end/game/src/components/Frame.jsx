import React from "react";
import Hexgrid from "./Hex";

function Frame() {
  const frameStyle = {
    width: "80%",
    height: "95%",
    border: "5px solid #000",
    borderRadius: "20px",
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
