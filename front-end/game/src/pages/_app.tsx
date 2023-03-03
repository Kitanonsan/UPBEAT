import SidebarProvider from "@/components/SidebarContext";
import "@/styles/globals.css";
import { AppProps } from "next/app"; // import AppProps instead of using type import

export default function App({ Component, pageProps }: AppProps) {
  return (
    <SidebarProvider>
      <Component {...pageProps} />
    </SidebarProvider>
  );
}
