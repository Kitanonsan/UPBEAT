import React from "react";
import HexagonGrid from "./hexagongrid.js";
import times from "lodash/times";

const HexGridDemo = () => {
  const getHexProps = (hexagon) => {
    return {
      style: {
        fill: "white",
        stroke: "black",
      },
      onClick: () => alert(`Hexagon n.${hexagon} has been clicked`),
    };
  };

  const renderHexagonContent = (hexagon) => {
    return (
      <div>
        <text
          x="50%"
          y="50%"
          fontSize={100}
          fontWeight="lighter"
          style={{ fill: "black" }}
          textAnchor="middle"
        >
          {hexagon}
        </text>
      </div>
    );
  };

  let hexagons = times(102, (id) => id);

  return (
    <HexagonGrid
      gridWidth={500}
      gridHeight={500}
      hexagons={hexagons}
      hexProps={getHexProps}
      renderHexagonContent={renderHexagonContent}
    />
  );
};

export default HexGridDemo;
