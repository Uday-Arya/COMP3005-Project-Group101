// src/pages/trainer/TrainerMemberLookup.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { useAuth } from "../../auth/AuthContext";
import { apiGet } from "../../api/client";

export default function TrainerMemberLookup() {
  const { user } = useAuth();
  const [query, setQuery] = useState("");
  const [results, setResults] = useState([]);
  const [err, setErr] = useState("");

  useEffect(() => {
    if (!user || !query.trim()) {
      setResults([]);
      return;
    }
    const timeout = setTimeout(() => {
      apiGet(
        `/trainers/${user.userId}/members?name=${encodeURIComponent(
          query
        )}`
      )
        .then(setResults)
        .catch((e) => setErr(e.message));
    }, 300);

    return () => clearTimeout(timeout);
  }, [query, user]);

  if (!user) return null;

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Member Lookup
      </h1>
      {err && <p className="text-red-600 mb-2">{err}</p>}
      <Card>
        <input
          type="text"
          className="w-full border rounded-md px-2 py-1 text-sm mb-3"
          placeholder="Search by member name..."
          value={query}
          onChange={(e) => {
            setErr("");
            setQuery(e.target.value);
          }}
        />
        {results.length === 0 ? (
          <p className="text-sm opacity-80">
            No members found. Type to search.
          </p>
        ) : (
          <ul className="divide-y text-sm">
            {results.map((m) => (
              <li key={m.id} className="py-2">
                <p className="font-medium">{m.fullName}</p>
                <p className="opacity-80">{m.email}</p>
                {m.currentGoal && (
                  <p className="mt-1">
                    Current goal: {m.currentGoal.goalType} →{" "}
                    {m.currentGoal.targetValue}
                    {m.currentGoal.unit} by{" "}
                    {m.currentGoal.targetDate}
                  </p>
                )}
                {m.latestMetric && (
                  <p className="text-xs opacity-70 mt-1">
                    Latest metric: {m.latestMetric.weight} kg,{" "}
                    {m.latestMetric.restingHeartRate} bpm
                  </p>
                )}
              </li>
            ))}
          </ul>
        )}
      </Card>
    </Layout>
  );
}
