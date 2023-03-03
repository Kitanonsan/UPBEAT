import BaseLayout from "@/components/BaseLayout";
import Hexgrid from "@/components/Hex";
import Frame from "../components/frame";

export default function Home() {
  return (
    <BaseLayout>
      <div>Center City Page</div>
      <Frame>
        <Hexgrid />
      </Frame>
    </BaseLayout>
  );
}
