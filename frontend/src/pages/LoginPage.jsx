// src/pages/LoginPage.jsx
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthContext";
import Layout from "../components/Layout";

export default function LoginPage() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await login(email, password);
      navigate("/");
    } catch (err) {
      setError("Invalid credentials or server error.");
    }
  };

  return (
    <Layout>
      <div className="max-w-md mx-auto mt-10 bg-white rounded-xl shadow-md p-6">
        <h1 className="text-2xl font-semibold mb-4 text-center">
          Login
        </h1>
        {error && (
          <div className="mb-3 text-red-600 text-sm">{error}</div>
        )}
        <form className="space-y-4" onSubmit={handleSubmit}>
          <div>
            <label className="block text-sm mb-1">Email</label>
            <input
              type="email"
              className="w-full border rounded-md px-3 py-2 text-sm"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div>
            <label className="block text-sm mb-1">Password</label>
            <input
              type="password"
              className="w-full border rounded-md px-3 py-2 text-sm"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button
            type="submit"
            className="w-full bg-slate-900 text-white py-2 rounded-md text-sm font-medium hover:bg-slate-800"
          >
            Sign in
          </button>
        </form>
      </div>
    </Layout>
  );
}
