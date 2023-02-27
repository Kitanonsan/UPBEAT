// MyComponent.tsx

import Image from "next/image";

export default function Hexgrid() {
  const rows = 10;
  const columns = 16;

  // Initialize the matrix with null values
  const matrix = new Array(rows);
  for (let i = 0; i < rows; i++) {
    matrix[i] = new Array(columns).fill(null);
  }

  // Set the image values using a loop
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < columns; j++) {
      const imageName = `GM${i * columns + j + 1}.png`;
      // const imageName = "GM1.png";
      matrix[i][j] = (
        <Image
          src={`/images/${imageName}`}
          alt=""
          width={61.5}
          height={53}
          onError={() => console.log(`Error loading ${imageName}`)}
        />
      );
    }
  }

  return (
    <div>
      {matrix.map((row, rowIndex) => (
        <div key={rowIndex}>
          {row.map((cell: JSX.Element | null, cellIndex: number) => (
            <span key={cellIndex}>{cell} </span>
          ))}
        </div>
      ))}
    </div>
  );
}
