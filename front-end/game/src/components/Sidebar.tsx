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
import { GrHomeRounded, GrContactInfo } from "react-icons/gr";
import { BiTimeFive, BiCoinStack } from "react-icons/bi";
import { MdLocationPin, MdShareLocation } from "react-icons/md";
import { RiFindReplaceLine } from "react-icons/ri";
import { GiAntiAircraftGun, GiReceiveMoney, GiPayMoney } from "react-icons/gi";
import { useContext, useState, useEffect } from "react";
import { SidebarContext } from "./SidebarContext";

const sidebarItem = [
  {
    name: "Time",
    href: "/",
    icon: BiTimeFive,
    time: 60,
    text: "Hello",
  },
  {
    name: "Gold",
    href: "/",
    icon: BiCoinStack,
  },

  {
    name: "Center City",
    href: "/",
    icon: GrHomeRounded,
  },

  {
    name: "City",
    href: "/",
    icon: MdLocationPin,
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
  {
    name: "Nearby",
    href: "/",
    icon: RiFindReplaceLine,
  },
  {
    name: "Collect",
    href: "/",
    icon: GiReceiveMoney,
  },
  {
    name: "Invest",
    href: "/",
    icon: GiPayMoney,
  },
  {
    name: "Move",
    href: "/",
    icon: BsArrowsMove,
  },
  {
    name: "Relocate",
    href: "/",
    icon: MdShareLocation,
  },
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
