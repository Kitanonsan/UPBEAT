import BaseLayout from "@/components/BaseLayout";
import Hexgrid from "@/components/Hex";
import InputPlan from "../components/Input";
import Frame from "../components/frame";
import Timer from "../components/Timer";

export default function Home() {
  return (
    <BaseLayout>
      <Timer />
      <div>Game Page</div>
      <Hexgrid />
      <InputPlan />
    </BaseLayout>
  );
}
