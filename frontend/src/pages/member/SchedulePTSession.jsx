// src/pages/member/SchedulePTSession.jsx
import { useEffect, useState } from "react";
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { schedulePTSession } from "../../api/memberApi";
import { apiGet } from "../../api/client";
import { useAuth } from "../../auth/AuthContext";

export default function SchedulePTSession() {
  const { user } = useAuth();
  const [trainers, setTrainers] = useState([]);
  const [rooms, setRooms] = useState([]);
  const [form, setForm] = useState({
    trainerId: "",
    roomId: "",
    startTime: "",
    endTime: "",
  });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  useEffect(() => {
    apiGet("/trainers") // create this endpoint on backend
      .then(setTrainers)
      .catch(console.error);

    apiGet("/admin/rooms") // or /rooms if you expose it
      .then(setRooms)
      .catch(console.error);
  }, []);

  if (!user) return null;

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");

    try {
      const payload = {
        memberId: user.userId,
        trainerId: parseInt(form.trainerId, 10),
        roomId: parseInt(form.roomId, 10),
        startTime: form.startTime,
        endTime: form.endTime,
      };
      await schedulePTSession(payload);
      setSuccess("Session scheduled successfully!");
      setForm({ trainerId: "", roomId: "", startTime: "", endTime: "" });
    } catch (err) {
      setError("Could not schedule session: " + err.message);
    }
  };

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Schedule Personal Training
      </h1>
      <Card>
        {error && <p className="text-red-600 mb-2">{error}</p>}
        {success && <p className="text-green-600 mb-2">{success}</p>}

        <form className="space-y-3" onSubmit={handleSubmit}>
          <div>
            <label className="block text-sm mb-1">
              Trainer
            </label>
            <select
              name="trainerId"
              value={form.trainerId}
              onChange={handleChange}
              className="w-full border rounded-md px-2 py-1 text-sm"
              required
            >
              <option value="">Select trainer</option>
              {trainers.map((t) => (
                <option key={t.id} value={t.id}>
                  {t.fullName} ({t.specialization})
                </option>
              ))}
            </select>
          </div>
          <div>
            <label className="block text-sm mb-1">Room</label>
            <select
              name="roomId"
              value={form.roomId}
              onChange={handleChange}
              className="w-full border rounded-md px-2 py-1 text-sm"
              required
            >
              <option value="">Select room</option>
              {rooms.map((r) => (
                <option key={r.id} value={r.id}>
                  {r.name} (capacity {r.capacity})
                </option>
              ))}
            </select>
          </div>
          <div>
            <label className="block text-sm mb-1">
              Start time
            </label>
            <input
              type="datetime-local"
              name="startTime"
              value={form.startTime}
              onChange={handleChange}
              className="w-full border rounded-md px-2 py-1 text-sm"
              required
            />
          </div>
          <div>
            <label className="block text-sm mb-1">
              End time
            </label>
            <input
              type="datetime-local"
              name="endTime"
              value={form.endTime}
              onChange={handleChange}
              className="w-full border rounded-md px-2 py-1 text-sm"
              required
            />
          </div>
          <button
            type="submit"
            className="bg-slate-900 text-white px-3 py-1 rounded-md text-sm hover:bg-slate-800"
          >
            Schedule
          </button>
        </form>
      </Card>
    </Layout>
  );
}
