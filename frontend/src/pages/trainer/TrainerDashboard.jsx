// src/pages/trainer/TrainerDashboard.jsx
import Layout from "../../components/Layout";
import Card from "../../components/Card";
import { useAuth } from "../../auth/AuthContext";

export default function TrainerDashboard() {
  const { user } = useAuth();

  return (
    <Layout>
      <h1 className="text-2xl font-semibold mb-4">
        Trainer Dashboard
      </h1>
      <div className="grid gap-4 md:grid-cols-2">
        <Card title="Overview">
          <p>Welcome, Trainer #{user?.userId}.</p>
          <p className="text-sm opacity-80">
            Use the navigation to view your schedule and members.
          </p>
        </Card>
        {/* Later: add summary components for upcoming sessions, etc. */}
      </div>
    </Layout>
  );
}
