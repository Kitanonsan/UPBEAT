import { createContext, useState, ReactNode } from "react";

const initialValue = {
  iscollapsedSidebar: false,
  toggleSidebarCollapseHandler: () => {},
};

export const SidebarContext = createContext(initialValue);

interface Props {
  children: ReactNode | ReactNode[];
}

const SidebarProvider = ({ children }: Props) => {
  const [iscollapsedSidebar, setiscollapsedSidebar] = useState<boolean>(false);

  const toggleSidebarCollapseHandler = () => {
    setiscollapsedSidebar((prev) => !prev);
  };

  return (
    <SidebarContext.Provider
      value={{ iscollapsedSidebar, toggleSidebarCollapseHandler }}
    >
      {children}
    </SidebarContext.Provider>
  );
};

export default SidebarProvider;
