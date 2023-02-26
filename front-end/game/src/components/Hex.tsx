import React from "react";
import Hexagon from "react-hexagon";

const imageArray = [
  { src: "GM1.png", alt: "Picture 1" },
  { src: "GM2.png", alt: "Picture 2" },
  { src: "GM3.png", alt: "Picture 3" },
  { src: "GM4.png", alt: "Picture 4" },
  { src: "GM5.png", alt: "Picture 5" },
];

export default function Hex() {
  return (
    <div>
      {imageArray.map((image, index) => (
        <img key={index} src={image.src} alt={image.alt} />
      ))}
    </div>
  );
}
