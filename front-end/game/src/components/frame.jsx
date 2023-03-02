import React from "react";

const Frame = ({ children }) => {
  const frameStyle = {
    border: "10px solid ",
    // borderColor: "grayish blue",
    padding: "10px",
    width: "950px",
    height: "960px",
    backgroundColor: "#BBBFCA",
  };

  return <div style={frameStyle}>{children}</div>;
};

export default Frame;
