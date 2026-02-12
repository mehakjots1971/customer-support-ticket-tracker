import axios from 'axios';

const API_URL = 'http://localhost:8080/api/tickets';

const createTicket = async (ticketData) => {
  const response = await axios.post(API_URL, ticketData);
  return response.data;
};

const getTickets = async (status, search) => {
  const params = {};
  if (status) params.status = status;
  if (search) params.search = search;
  const response = await axios.get(API_URL, { params });
  return response.data;
};

const updateTicketStatus = async (id, status) => {
  const response = await axios.patch(`${API_URL}/${id}`, { status });
  return response.data;
};

const deleteTicket = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`);
  return response.data;
};

export default {
  createTicket,
  getTickets,
  updateTicketStatus,
  deleteTicket
};
