/* eslint-disable @next/next/no-img-element */
import Image from "next/image";
import Link from "next/link";
import {
  MdDashboard,
  MdOutlinePersonSearch,
  MdOutlineKeyboardArrowLeft,
} from "react-icons/md";
import { GrHomeRounded, GrContactInfo } from "react-icons/gr";
import { GiAntiAircraftGun } from "react-icons/gi";
import { useContext, useState } from "react";
import { SidebarContext } from "./SidebarContext";

const sidebarItem = [
  {
    name: "Center City",
    href: "/",
    icon: GrHomeRounded,
  },
  {
    name: "Opponent",
    href: "/Opponent",
    icon: MdOutlinePersonSearch,
  },
  {
    name: "Shoot",
    href: "/Shoot",
    icon: GiAntiAircraftGun,
  },
  {
    name: "Info",
    href: "/Info",
    icon: GrContactInfo,
  },
];

export default function Sidebar() {
  const { iscollapsedSidebar, toggleSidebarCollapseHandler } =
    useContext(SidebarContext);

  return (
    <div className="sidebar_wrapper">
      <button className="btn" onClick={toggleSidebarCollapseHandler}>
        <MdOutlineKeyboardArrowLeft />
      </button>
      <aside className="sidebar" data-collapse={iscollapsedSidebar}>
        <div className="sidebar_top">
          <img
            src="/U.png"
            width={80}
            height={80}
            className="sidebar_logo"
            alt="logo"
          />

          <p className="sidebar_logo-name">UPbeat</p>
        </div>
        <ul className="sidebar_list">
          {sidebarItem.map(({ name, href, icon: Icon }) => (
            <li className="sidebar_item" key={name}>
              <Link href={href} className="sidebar_link">
                <span className="sidebar_icon">
                  <Icon />
                </span>
                <span className="sidebar_name">{name}</span>
              </Link>
            </li>
          ))}
        </ul>
      </aside>
    </div>
  );
}
