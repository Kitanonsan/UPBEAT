export function initializeMatrix(rows, columns, shuffledImageNames) {
  const matrix = new Array(rows);
  for (let i = 0; i < rows; i++) {
    matrix[i] = new Array(columns).fill(null);
  }

  let imageIndex = 0;
  for (let i = 0; i < rows; i++) {
    for (let j = 0; j < columns; j++) {
      const imageName = shuffledImageNames[imageIndex];
      matrix[i][j] = imageName;
      imageIndex++;
    }
  }

  matrix[6][6] = "CT.png";
  matrix[1][0] = "P3.png";
  matrix[5][1] = "P2.png";
  matrix[3][1] = "P3.png";
  matrix[3][2] = "P3.png";
  matrix[3][7] = "P2.png";
  matrix[3][6] = "P2.png";
  matrix[3][5] = "P2.png";
  matrix[3][4] = "P3.png";

  return matrix;
}
