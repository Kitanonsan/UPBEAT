import BaseLayout from "@/components/BaseLayout";
import Hexgrid from "@/components/Hex";
import InputPlan from "../components/Input";
import Timer from "../components/Timer";

export default function Home() {
  return (
    <div className="backofgame">
      <BaseLayout>
        <Timer />

        <Hexgrid />
        <InputPlan />
      </BaseLayout>
      <style jsx>{`
        .backofgame {
          background-image: url("/images/flower.png");
          background-size: cover;
          background-position: center;
        }
      `}</style>
    </div>
  );
}
