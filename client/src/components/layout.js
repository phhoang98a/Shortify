import React from "react"
import Navbar from "./navbar";
import Footer from "./footer";
// Pass the child props
export default function Layout({ children }) {
  return (
    <div >
      <Navbar/>
      {children}
      <Footer/>
    </div>
  );
}