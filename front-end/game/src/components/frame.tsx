import React, { ReactNode } from "react";

interface FrameProps {
  children?: ReactNode;
  width?: string;
  height?: string;
  borderColor?: string;
  borderWidth?: string;
  padding?: string;
}

const Frame: React.FC<FrameProps> = ({
  children,
  width = "1070px",
  height = "960px",
  borderColor = "black",
  borderWidth = "2px",
  padding = "10px",
}) => {
  const frameStyle = {
    border: `${borderWidth} solid ${borderColor}`,
    padding,
    width,
    height,
  };

  return <div style={frameStyle}>{children}</div>;
};

export default Frame;
