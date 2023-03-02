import Image from "next/image";
import { useState } from "react";

export default function Hexgrid() {
  //-------------------------------------------------
  const rows = 12;
  const columns = 10;
  const H = 82;
  const W = 95.15;
  //-------------------------------------------------

  const matrix = new Array(rows);
  for (let i = 0; i < rows; i++) {
    matrix[i] = new Array(columns).fill(null);
  }

  // Define an array of image names
  const imageNames = Array.from({ length: rows * columns }, (_, i) => {
    const imageNumber = (i % 17) + 1;
    return `l${imageNumber}.png`;
  });

  // Shuffle the array to get a random order
  const shuffledImageNames = shuffleArray(imageNames);

  // Set the image values using the shuffled array
  let imageIndex = 0;
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < columns; j++) {
      const imageName = shuffledImageNames[imageIndex];
      matrix[i][j] = (
        <Image
          src={`/images/${imageName}`}
          alt=""
          width={W}
          height={H}
          onError={() => console.log(`Error loading ${imageName}`)}
        />
      );
      imageIndex++;
    }
  }
  matrix[6][4] = (
    <Image
      src={"/images/CT.png"}
      alt=""
      width={W}
      height={H}
      onError={() => console.log("Error loading CenterCity")}
    />
  );

  return (
    <div>
      {matrix.map((row, rowIndex) => (
        <span
          className={rowIndex % 2 === 0 ? "odd" : "even"}
          style={{ width: W - W / 4 + 3 }}
        >
          <div key={rowIndex}>
            {row.map((cell: JSX.Element | null, cellIndex: number) => (
              <div key={cellIndex}>{cell} </div>
            ))}
          </div>
        </span>
      ))}
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
