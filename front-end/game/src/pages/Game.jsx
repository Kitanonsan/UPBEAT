import BaseLayout from "@/components/BaseLayout";
import Hexgrid from "@/components/Hex";
import InputPlan from "../components/Input";
import Timer from "../components/Timer";
import Frame from "../components/Frame";
import CodeEditor from "../components/editor";

export default function Home() {
  return (
    <div className="backofgame">
      <BaseLayout>
        <Frame />
        <Timer />
        {/* <Hexgrid /> */}
        <InputPlan />
        {/* <CodeEditor /> */}
      </BaseLayout>

      <style jsx>{`
        .backofgame {
          // background-image: url("/images/test wallpaper.png");
          background-image: url("/images/flower.png");
          background-size: cover;
          background-position: center;
        }
      `}</style>
    </div>
  );
}
