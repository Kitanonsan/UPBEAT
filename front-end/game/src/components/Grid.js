import Image from "next/image";

function Grid({ matrix, W, H }) {
  return (
    <>
      {matrix.map((row, rowIndex) => (
        <span
          className={rowIndex % 2 === 0 ? "odd" : "even"}
          style={{ width: W - W / 4 + 3 }}
        >
          <div key={rowIndex}>
            {row.map((cell, cellIndex) => (
              <div key={cellIndex}>
                {cell ? (
                  <Image
                    src={`/images/${cell}`}
                    alt=""
                    width={W}
                    height={H}
                    onError={() => console.log(`Error loading ${cell}`)}
                  />
                ) : null}
              </div>
            ))}
          </div>
        </span>
      ))}
    </>
  );
}

export default Grid;
