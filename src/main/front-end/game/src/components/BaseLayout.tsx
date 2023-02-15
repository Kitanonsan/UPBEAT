import { ReactNode } from "react";
import DoneButton from "./Done";
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
      <Timer />
      <DoneButton />
    </div>
  );
}
