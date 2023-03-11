import { ReactNode } from "react";
import ConstructionButton from "./ConstructionplanButton.jsx";
import DoneButton from "./DoneButton";
import Sidebar from "./Sidebar";
import Timer from "./Timer";

interface Props {
  children: ReactNode | ReactNode[];
}

export default function BaseLayout({ children }: Props) {
  return (
    <div className="Layout">
      <Sidebar />
      {children}
      {/* <Timer /> */}
      {/* <DoneButton /> */}
      {/* <ConstructionButton /> */}
    </div>
  );
}
