// src/pages/member/ClassList.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import {
  getAvailableClasses,
  registerForClass,
} from "../../api/memberApi";
import { useAuth } from "../../auth/AuthContext";

export default function ClassList() {
  const { user } = useAuth();
  const [classes, setClasses] = useState([]);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    getAvailableClasses()
      .then(setClasses)
      .catch((e) => setError(e.message));
  }, []);

  if (!user) return null;

  const handleRegister = async (classId) => {
    setError("");
    setSuccess("");
    try {
      await registerForClass(classId, user.userId);
      setSuccess("Successfully registered!");
      // Optionally: refetch classes for remaining capacity
    } catch (err) {
      setError("Registration failed: " + err.message);
    }
  };

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Available Classes
      </h1>
      {error && <p className="text-red-600 mb-2">{error}</p>}
      {success && <p className="text-green-600 mb-2">{success}</p>}

      <Card>
        {classes.length === 0 ? (
          <p>No upcoming classes.</p>
        ) : (
          <ul className="divide-y">
            {classes.map((c) => (
              <li
                key={c.id}
                className="py-2 flex items-center justify-between gap-3"
              >
                <div className="text-sm">
                  <p className="font-medium">
                    {c.className} with {c.trainerName}
                  </p>
                  <p className="opacity-80">
                    {c.startTime} – {c.endTime} @ {c.roomName}
                  </p>
                  <p className="opacity-80">
                    Remaining capacity: {c.remainingCapacity}
                  </p>
                </div>
                <button
                  onClick={() => handleRegister(c.id)}
                  className="px-3 py-1 rounded-md bg-slate-900 text-white text-sm hover:bg-slate-800"
                  disabled={c.remainingCapacity <= 0}
                >
                  Register
                </button>
              </li>
            ))}
          </ul>
        )}
      </Card>
    </Layout>
  );
}
