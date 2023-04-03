/* eslint-disable @next/next/no-img-element */
import Image from "next/image";
import React from "react";
import Link from "next/link";
import {
  MdDashboard,
  MdOutlinePersonSearch,
  MdOutlineKeyboardArrowLeft,
} from "react-icons/md";
import { BsList, BsArrowsMove } from "react-icons/bs";
import {
  AiOutlineZoomIn,
  AiOutlineZoomOut,
  AiOutlineUser,
} from "react-icons/ai";
import { GrHomeRounded, GrContactInfo } from "react-icons/gr";
import { BiTimeFive, BiCoinStack } from "react-icons/bi";
import { MdLocationPin, MdShareLocation } from "react-icons/md";
import { RiFindReplaceLine, RiNumber1 } from "react-icons/ri";
import { GiAntiAircraftGun, GiReceiveMoney, GiPayMoney } from "react-icons/gi";
import { useContext, useState, useEffect } from "react";
import { SidebarContext } from "./SidebarContext";

const sidebarItem = [
  {
    name: "Player1",
    href: "/",
    icon: AiOutlineUser,
  },
  {
    name: "Gold 10000",
    href: "/",
    icon: BiCoinStack,
  },
  // {
  //   name: "Player",
  //   href: "/",
  //   icon: RiNumber1,
  // },
  // {
  //   name: "Zoom In",
  //   href: "/",
  //   icon: AiOutlineZoomIn,
  // },
  // {
  //   name: "Zoom Out",
  //   href: "/",
  //   icon: AiOutlineZoomOut,
  //   time: 60,
  // },
  // {
  //   name: "Logout",
  //   href: "/",
  //   icon: MdOutlineKeyboardArrowLeft,
  //   className: "logout-button",
  // },
];

export default function Sidebar() {
  const { iscollapsedSidebar, toggleSidebarCollapseHandler } =
    useContext(SidebarContext);

  return (
    <div className="sidebar_wrapper">
      <button className="btn" onClick={toggleSidebarCollapseHandler}>
        <BsList />
      </button>
      <aside className="sidebar" data-collapse={iscollapsedSidebar}>
        <div className="sidebar_top">
          <img
            src="/images/U.png"
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
