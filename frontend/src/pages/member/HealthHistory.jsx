// src/pages/member/HealthHistory.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { getHealthMetrics, addHealthMetric } from "../../api/memberApi";
import { useAuth } from "../../auth/AuthContext";

export default function HealthHistory() {
  const { user } = useAuth();
  const [metrics, setMetrics] = useState([]);
  const [error, setError] = useState("");
  const [form, setForm] = useState({
    weight: "",
    height: "",
    restingHeartRate: "",
    bodyFatPercentage: "",
  });

  useEffect(() => {
    if (!user) return;
    getHealthMetrics(user.userId)
      .then(setMetrics)
      .catch((e) => setError(e.message));
  }, [user]);

  if (!user) return null;

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      const payload = {
        weight: parseFloat(form.weight),
        height: parseFloat(form.height),
        restingHeartRate: parseInt(form.restingHeartRate, 10),
        bodyFatPercentage: parseFloat(form.bodyFatPercentage),
      };
      const saved = await addHealthMetric(user.userId, payload);
      setMetrics((prev) => [saved, ...prev]);
      setForm({
        weight: "",
        height: "",
        restingHeartRate: "",
        bodyFatPercentage: "",
      });
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Health History
      </h1>
      {error && <p className="text-red-600 mb-2">{error}</p>}

      <div className="grid gap-4 md:grid-cols-2">
        <Card title="Record New Metric">
          <form className="space-y-3" onSubmit={handleSubmit}>
            <div>
              <label className="block text-sm mb-1">Weight (kg)</label>
              <input
                name="weight"
                value={form.weight}
                onChange={handleChange}
                type="number"
                step="0.1"
                className="w-full border rounded-md px-2 py-1 text-sm"
                required
              />
            </div>
            <div>
              <label className="block text-sm mb-1">Height (cm)</label>
              <input
                name="height"
                value={form.height}
                onChange={handleChange}
                type="number"
                step="0.1"
                className="w-full border rounded-md px-2 py-1 text-sm"
                required
              />
            </div>
            <div>
              <label className="block text-sm mb-1">
                Resting Heart Rate (bpm)
              </label>
              <input
                name="restingHeartRate"
                value={form.restingHeartRate}
                onChange={handleChange}
                type="number"
                className="w-full border rounded-md px-2 py-1 text-sm"
                required
              />
            </div>
            <div>
              <label className="block text-sm mb-1">
                Body Fat (%)
              </label>
              <input
                name="bodyFatPercentage"
                value={form.bodyFatPercentage}
                onChange={handleChange}
                type="number"
                step="0.1"
                className="w-full border rounded-md px-2 py-1 text-sm"
              />
            </div>
            <button
              type="submit"
              className="bg-slate-900 text-white px-3 py-1 rounded-md text-sm hover:bg-slate-800"
            >
              Save
            </button>
          </form>
        </Card>

        <Card title="Past Metrics">
          {metrics.length === 0 ? (
            <p>No metrics recorded yet.</p>
          ) : (
            <table className="w-full text-sm">
              <thead>
                <tr className="text-left border-b">
                  <th className="py-1">Date</th>
                  <th>Weight</th>
                  <th>HR</th>
                  <th>Body Fat</th>
                </tr>
              </thead>
              <tbody>
                {metrics.map((m) => (
                  <tr key={m.id} className="border-b last:border-0">
                    <td className="py-1">{m.recordedAt}</td>
                    <td>{m.weight}</td>
                    <td>{m.restingHeartRate}</td>
                    <td>{m.bodyFatPercentage}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </Card>
      </div>
    </Layout>
  );
}
