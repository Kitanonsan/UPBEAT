export default function datapic() {
  const [matrix, setMatrix] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/game/message")
      .then((response) => response.json())
      .then((data) => {
        const newMatrix = [];

        for (let i = 0; i < data.row; i++) {
          const row = [];

          for (let j = 0; j < data.column; j++) {
            let owner = null;
            for (let k = 0; k < data.territory.length; k++) {
              const territory = data.territory[k];
              if (territory.position[0] === i && territory.position[1] === j) {
                owner = territory.owner;
                break;
              }
            }

            if (owner === "Player1") {
              row.push("/images/P3.png");
            } else if (owner === "Player2") {
              row.push("/images/P2.png");
            } else {
              row.push(null);
            }
          }

          newMatrix.push(row);
        }

        setMatrix(newMatrix);
      })
      .catch((error) => console.error(error));
  }, []);

  return (
    <div>
      <h1>Game Grid</h1>
      {matrix &&
        matrix.map((row, i) => (
          <div key={i}>
            {row.map((cell, j) => (
              <div key={`${i}-${j}`}>
                {cell && <img src={cell} alt={`Cell ${i}-${j}`} />}
              </div>
            ))}
          </div>
        ))}
    </div>
  );
}
