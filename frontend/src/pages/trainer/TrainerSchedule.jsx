// src/pages/trainer/TrainerSchedule.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { useAuth } from "../../auth/AuthContext";
import { apiGet } from "../../api/client";

export default function TrainerSchedule() {
  const { user } = useAuth();
  const [items, setItems] = useState([]);
  const [err, setErr] = useState("");

  useEffect(() => {
    if (!user) return;
    apiGet(`/trainers/${user.userId}/schedule`)
      .then(setItems)
      .catch((e) => setErr(e.message));
  }, [user]);

  if (!user) return null;

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        My Schedule
      </h1>
      {err && <p className="text-red-600 mb-2">{err}</p>}
      <Card>
        {items.length === 0 ? (
          <p>No scheduled sessions.</p>
        ) : (
          <ul className="divide-y text-sm">
            {items.map((s) => (
              <li key={s.id} className="py-1">
                {s.type} with {s.memberName} on {s.startTime} in{" "}
                {s.roomName}
              </li>
            ))}
          </ul>
        )}
      </Card>
    </Layout>
  );
}
