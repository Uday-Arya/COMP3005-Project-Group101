// src/pages/member/MemberDashboard.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { getMemberDashboard } from "../../api/memberApi";
import { useAuth } from "../../auth/AuthContext";

export default function MemberDashboard() {
  const { user } = useAuth();
  const [data, setData] = useState(null);
  const [err, setErr] = useState("");

  useEffect(() => {
    if (!user) return;
    getMemberDashboard(user.userId)
      .then(setData)
      .catch((e) => setErr(e.message));
  }, [user]);

  if (!user) return null;

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Member Dashboard
      </h1>
      {err && <p className="text-red-600 mb-2">{err}</p>}
      {!data ? (
        <p>Loading data...</p>
      ) : (
        <div className="grid gap-4 md:grid-cols-2">
          <Card title="Profile">
            <p className="font-medium">{data.memberName}</p>
            <p className="text-sm opacity-70">{data.email}</p>
          </Card>

          <Card title="Latest Health Metrics">
            {data.latestMetric ? (
              <>
                <p>Weight: {data.latestMetric.weight} kg</p>
                <p>
                  Resting HR: {data.latestMetric.restingHeartRate} bpm
                </p>
                <p>
                  Body Fat: {data.latestMetric.bodyFatPercentage} %
                </p>
              </>
            ) : (
              <p>No metrics recorded yet.</p>
            )}
          </Card>

          <Card title="Active Goals" className="md:col-span-2">
            {data.activeGoals?.length ? (
              <ul className="list-disc ml-5 text-sm">
                {data.activeGoals.map((g) => (
                  <li key={g.id}>
                    {g.goalType} – target {g.targetValue}
                    {g.unit} by {g.targetDate}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No active goals.</p>
            )}
          </Card>

          <Card title="Upcoming Sessions" className="md:col-span-2">
            {data.upcomingSessions?.length ? (
              <ul className="divide-y text-sm">
                {data.upcomingSessions.map((s) => (
                  <li key={s.id} className="py-1">
                    {s.type} with {s.trainerName} on {s.startTime} in{" "}
                    {s.roomName}
                  </li>
                ))}
              </ul>
            ) : (
              <p>No upcoming sessions.</p>
            )}
          </Card>
        </div>
      )}
    </Layout>
  );
}
