// src/components/Layout.jsx
import Navbar from "./Navbar";

export default function Layout({ children }) {
  return (
    <div className="min-h-screen bg-slate-100">
      <Navbar />
      <main className="max-w-5xl mx-auto p-4">{children}</main>
    </div>
  );
}
