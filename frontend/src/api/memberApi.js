// src/api/memberApi.js
import { apiGet, apiPost } from "./client";

export function getMemberDashboard(memberId) {
  return apiGet(`/members/${memberId}/dashboard`);
}

export function updateMemberProfile(memberId, payload) {
  return apiPost(`/members/${memberId}`, payload); // or PUT, match backend
}

export function addHealthMetric(memberId, metric) {
  return apiPost(`/members/${memberId}/metrics`, metric);
}

export function getHealthMetrics(memberId) {
  return apiGet(`/members/${memberId}/metrics`);
}

export function getAvailableClasses() {
  return apiGet(`/classes/available`);
}

export function registerForClass(classId, memberId) {
  return apiPost(`/classes/${classId}/register?memberId=${memberId}`, {});
}

export function schedulePTSession(payload) {
  // payload includes memberId, trainerId, roomId, startTime, endTime
  return apiPost(`/pt-sessions`, payload);
}
