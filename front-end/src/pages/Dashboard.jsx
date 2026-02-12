import React, { useEffect, useState, useCallback } from 'react';
import TicketForm from '../components/TicketForm/TicketForm';
import TicketList from '../components/TicketList/TicketList';
import ticketService from '../services/ticketService';

const Dashboard = () => {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState('');
  const [filterStatus, setFilterStatus] = useState('');

  const fetchTickets = useCallback(async () => {
    setLoading(true);
    try {
      const data = await ticketService.getTickets(filterStatus, search);
      // Sort by new first
      const sorted = data.sort((a, b) => b.id - a.id); 
      setTickets(sorted);
    } catch (error) {
      console.error("Failed to fetch tickets", error);
    } finally {
      setLoading(false);
    }
  }, [filterStatus, search]);

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this ticket?")) {
      try {
        await ticketService.deleteTicket(id);
        fetchTickets();
      } catch (error) {
        console.error("Failed to delete ticket", error);
        alert("Failed to delete ticket.");
      }
    }
  };

  useEffect(() => {
    // Debounce search
    const timer = setTimeout(() => {
      fetchTickets();
    }, 500);
    return () => clearTimeout(timer);
  }, [fetchTickets]);

  return (
    <div className="min-h-screen bg-slate-50 relative overflow-hidden">
      {/* Decorative Background Blobs */}
      <div className="absolute top-0 -left-4 w-72 h-72 bg-blue-100 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob"></div>
      <div className="absolute top-0 -right-4 w-72 h-72 bg-purple-100 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob animation-delay-2000"></div>
      <div className="absolute -bottom-8 left-20 w-72 h-72 bg-pink-100 rounded-full mix-blend-multiply filter blur-3xl opacity-30 animate-blob animation-delay-4000"></div>
      
      <div className="container mx-auto px-2 sm:px-6 lg:px-8 py-8 max-w-[1600px] relative z-10">
        <header className="mb-10 text-center">
          <h1 className="text-4xl sm:text-5xl font-extrabold text-gray-900 tracking-tight mb-3">
            Support <span className="text-blue-600">Dashboard</span>
          </h1>
          <p className="text-base sm:text-lg text-gray-600 max-w-2xl mx-auto">
            Manage customer inquiries and support tickets efficiently with our high-performance tracking system.
          </p>
        </header>

        <div className="grid grid-cols-1 lg:grid-cols-12 gap-6 sm:gap-8 items-start">
          {/* Left Column: Create Form */}
          <div className="lg:col-span-4 xl:col-span-4 lg:sticky lg:top-8">
            <TicketForm onTicketCreated={fetchTickets} />
          </div>

          {/* Right Column: Ticket List */}
          <div className="lg:col-span-8 xl:col-span-8">
            <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center mb-6 gap-4">
              <div className="flex flex-col gap-1">
                <h2 className="text-2xl font-bold text-gray-800 flex items-center">
                  Recent Tickets
                  <span className="ml-3 px-2 py-1 bg-blue-100 text-blue-700 text-xs rounded-full">{tickets.length}</span>
                </h2>
              </div>
              
              <div className="flex flex-col sm:flex-row gap-3 w-full sm:w-auto">
                <input 
                  type="text" 
                  placeholder="Search tickets..." 
                  value={search}
                  onChange={(e) => setSearch(e.target.value)}
                  className="px-4 py-2 border border-gray-200 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 w-full sm:w-64"
                />
                
                <select
                  value={filterStatus}
                  onChange={(e) => setFilterStatus(e.target.value)}
                  className="px-4 py-2 border border-gray-200 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 bg-white cursor-pointer"
                >
                  <option value="">All Status</option>
                  <option value="OPEN">Open</option>
                  <option value="IN_PROGRESS">In Progress</option>
                  <option value="RESOLVED">Resolved</option>
                </select>

                <button 
                  onClick={fetchTickets}
                  className="px-4 py-2 text-sm font-semibold text-blue-700 bg-white border border-blue-100 rounded-lg shadow-sm hover:bg-blue-50 transition-all flex items-center justify-center whitespace-nowrap"
                >
                  <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
                  </svg>
                </button>
              </div>
            </div>
            <TicketList 
              tickets={tickets} 
              loading={loading} 
              onStatusUpdate={fetchTickets}
              onDelete={handleDelete} 
            />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
