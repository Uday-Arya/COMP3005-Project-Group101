// src/App.jsx
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import ProtectedRoute from "./components/ProtectedRoute";

import LoginPage from "./pages/LoginPage";

// Member pages
import MemberDashboard from "./pages/member/MemberDashboard";
import HealthHistory from "./pages/member/HealthHistory";
import ClassList from "./pages/member/ClassList";
import SchedulePTSession from "./pages/member/SchedulePTSession";

// Trainer pages
import TrainerDashboard from "./pages/trainer/TrainerDashboard";
import TrainerSchedule from "./pages/trainer/TrainerSchedule";
import TrainerMemberLookup from "./pages/trainer/TrainerMemberLookup";

// Admin pages
import AdminDashboard from "./pages/admin/AdminDashboard";
import RoomManagement from "./pages/admin/RoomManagement";
import EquipmentMaintenance from "./pages/admin/EquipmentMaintenance";
import ClassManagement from "./pages/admin/ClassManagement";
import BillingPage from "./pages/admin/BillingPage";

import { useAuth } from "./auth/AuthContext";

function Home() {
  const { user } = useAuth();

  if (!user) {
    return (
      <Layout>
        <h1 className="text-2xl font-semibold mb-2">Welcome to FitClub</h1>
        <p>Please log in to access your dashboard.</p>
      </Layout>
    );
  }

  if (user.role === "MEMBER") {
    return <MemberDashboard />;
  }
  if (user.role === "TRAINER") {
    return <TrainerDashboard />;
  }
  if (user.role === "ADMIN") {
    return <AdminDashboard />;
  }

  return (
    <Layout>
      <p>Unknown role.</p>
    </Layout>
  );
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Public */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/" element={<Home />} />

        {/* Member routes */}
        <Route
          element={<ProtectedRoute allowedRoles={["MEMBER"]} />}
        >
          <Route
            path="/member/dashboard"
            element={<MemberDashboard />}
          />
          <Route
            path="/member/metrics"
            element={<HealthHistory />}
          />
          <Route
            path="/member/classes"
            element={<ClassList />}
          />
          <Route
            path="/member/pt-sessions"
            element={<SchedulePTSession />}
          />
        </Route>

        {/* Trainer routes */}
        <Route
          element={<ProtectedRoute allowedRoles={["TRAINER"]} />}
        >
          <Route
            path="/trainer/dashboard"
            element={<TrainerDashboard />}
          />
          <Route
            path="/trainer/schedule"
            element={<TrainerSchedule />}
          />
          <Route
            path="/trainer/members"
            element={<TrainerMemberLookup />}
          />
        </Route>

        {/* Admin routes */}
        <Route
          element={<ProtectedRoute allowedRoles={["ADMIN"]} />}
        >
          <Route
            path="/admin/dashboard"
            element={<AdminDashboard />}
          />
          <Route
            path="/admin/rooms"
            element={<RoomManagement />}
          />
          <Route
            path="/admin/equipment"
            element={<EquipmentMaintenance />}
          />
          <Route
            path="/admin/classes"
            element={<ClassManagement />}
          />
          <Route
            path="/admin/billing"
            element={<BillingPage />}
          />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}
