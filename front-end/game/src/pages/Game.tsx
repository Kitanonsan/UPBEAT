import BaseLayout from "@/components/BaseLayout";
import { motion } from "framer-motion";
import HexagonGrid from "@/components/HexagonGrid";
import HexGridDemo from "../components/HexagonGrid";
import Hex from "@/components/Hex";
import Hexagon from "react-hexagon";

export default function Home() {
  return (
    <BaseLayout>
      <div>Center City Page</div>
      <Hex />
      <Hexagon flatTop backgroundImage="GM3.png" />
    </BaseLayout>
  );
}
