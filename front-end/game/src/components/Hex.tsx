import { useState } from "react";
import Grid from "./Grid";

export default function Hexgrid() {
  //-------------------------------------------------
  const rows = 12;
  const columns = 10;
  const H = 82;
  const W = (246 / 212) * H;
  //H=212   W=246
  //-------------------------------------------------

  const matrix = new Array(rows);
  for (let i = 0; i < rows; i++) {
    matrix[i] = new Array(columns).fill(null);
  }

  // Define an array of image names
  const imageNames = Array.from({ length: rows * columns }, (_, i) => {
    const imageNumber = (i % 4) + 1;
    // const imageNumber = 3;

    // const imageNumber = 4;
    // return "l1.png";
    return `t${imageNumber}.png`;
  });

  // Shuffle the array to get a random order
  const shuffledImageNames = shuffleArray(imageNames);

  // Set the image values using the shuffled array
  let imageIndex = 0;
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < columns; j++) {
      const imageName = shuffledImageNames[imageIndex];
      matrix[i][j] = imageName;
      imageIndex++;
    }
  }

  matrix[6][6] = "hq1.png";
  matrix[6][5] = "hq2.png";
  matrix[1][0] = "H1.png";
  matrix[5][1] = "H2.png";
  matrix[3][1] = "H1.png";
  matrix[3][2] = "H2.png";
  matrix[3][7] = "H1.png";
  matrix[3][6] = "H2.png";
  matrix[3][5] = "H1.png";
  matrix[3][4] = "H2.png";

  const [zoomLevel, setZoomLevel] = useState(1);

  const wrapperWidth = `${W * columns * zoomLevel}px`;
  const wrapperHeight = `${H * rows * zoomLevel}px`;

  const transformStyle = `scale(${zoomLevel})`;

  const zoomIn = () => {
    if (zoomLevel < 2) {
      setZoomLevel(zoomLevel + 0.1);
    }
  };

  const zoomOut = () => {
    if (zoomLevel > 0.5) {
      setZoomLevel(zoomLevel - 0.1);
    }
  };
  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          marginBottom: "10px",
        }}
      >
        <button
          onClick={zoomIn}
          style={{ marginRight: "10px" }}
          className="Zoomin"
        >
          Zoom In
        </button>
        <button onClick={zoomOut} className="Zoomout">
          Zoom Out
        </button>
      </div>
      <div
        style={{
          transform: transformStyle,
          transformOrigin: "top left",
        }}
      >
        <Grid matrix={matrix} W={W} H={H} />
      </div>
    </div>
  );
}

// Function to shuffle an array in place
function shuffleArray<T>(array: T[]): T[] {
  for (let i = array.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [array[i], array[j]] = [array[j], array[i]];
  }
  return array;
}
